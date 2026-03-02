package com.hbm.renderer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

/**
 * OBJ Model Loader with per-group dynamic rendering and optional overlay tinting.
 *
 * ── MODEL JSON ────────────────────────────────────────────────────────────────────────
 *
 *   {
 *     "loader": "hbm:obj_group_loader",
 *     "model":  "hbm:models/block/cable_neo.obj",
 *     "texture": "hbm:block/cable_neo",
 *
 *     "textures": {
 *       "overlay": "hbm:block/cable_overlay"
 *     },
 *
 *     "always_visible": ["Core"],
 *
 *     "group_conditions": {
 *       "north": { "true": "posZ" },
 *       "south": { "true": "negZ" },
 *       "east":  { "true": "posX" },
 *       "west":  { "true": "negX" },
 *       "up":    { "true": "posY" },
 *       "down":  { "true": "negY" }
 *     },
 *
 *     "display": {
 *       "thirdperson_righthand": { "rotation": [75, 45, 0], "translation": [0, 2.5, 0], "scale": [0.375, 0.375, 0.375] },
 *       "thirdperson_lefthand":  { "rotation": [75, 45, 0], "translation": [0, 2.5, 0], "scale": [0.375, 0.375, 0.375] },
 *       "firstperson_righthand": { "rotation": [0, 45, 0],  "translation": [0, 0, 0],   "scale": [0.4, 0.4, 0.4] },
 *       "firstperson_lefthand":  { "rotation": [0, 225, 0], "translation": [0, 0, 0],   "scale": [0.4, 0.4, 0.4] },
 *       "ground":                { "translation": [0, 3, 0],                             "scale": [0.25, 0.25, 0.25] },
 *       "gui":                   { "rotation": [30, 225, 0],                             "scale": [0.625, 0.625, 0.625] },
 *       "fixed":                 {                                                        "scale": [0.5, 0.5, 0.5] }
 *     }
 *   }
 *
 * ── KEY POINTS ────────────────────────────────────────────────────────────────────────
 *   - "texture" (singular) = base texture, handled exactly as before (no change).
 *   - "textures" block = Forge auto-stitches these into the atlas.
 *     "overlay" key inside it is retrieved via context.getMaterial("overlay") in bake().
 *   - Overlay quads use tintIndex=0, multiplied by your BlockColor/ItemColor handler.
 *   - Base quads use tintIndex=-1, never tinted.
 *   - Omitting "textures"/"overlay" from the JSON disables overlay entirely.
 */
public class ObjGroupModelLoader implements IGeometryLoader<ObjGroupModelLoader.ObjGroupGeometry> {

    public static final ObjGroupModelLoader INSTANCE = new ObjGroupModelLoader();

    // ─────────────────────────────────────────────────────────────────────────
    // Loader — reads the model JSON
    // ─────────────────────────────────────────────────────────────────────────

    @Override
    public ObjGroupGeometry read(JsonObject json, JsonDeserializationContext context) {
        System.out.println("[ObjGroupModelLoader] read() called! JSON: " + json);

        ResourceLocation modelPath = ResourceLocation.parse(json.get("model").getAsString());

        // "texture" (singular) — base texture, same as before
        ResourceLocation texturePath = json.has("texture")
                ? ResourceLocation.parse(json.get("texture").getAsString())
                : ResourceLocation.parse("minecraft:missingno");

        // Item display transforms
        ItemTransforms transforms = ItemTransforms.NO_TRANSFORMS;
        if (json.has("display")) {
            transforms = context.deserialize(json.get("display"), ItemTransforms.class);
        }

        // always_visible list
        List<String> alwaysVisible = new ArrayList<>();
        if (json.has("always_visible")) {
            json.getAsJsonArray("always_visible")
                    .forEach(e -> alwaysVisible.add(e.getAsString()));
        }

        // group_conditions map
        Map<String, Map<String, String>> groupConditions = new LinkedHashMap<>();
        if (json.has("group_conditions")) {
            json.getAsJsonObject("group_conditions").entrySet().forEach(prop -> {
                Map<String, String> valueMap = new LinkedHashMap<>();
                prop.getValue().getAsJsonObject().entrySet()
                        .forEach(val -> valueMap.put(val.getKey(), val.getValue().getAsString()));
                groupConditions.put(prop.getKey(), valueMap);
            });
        }

        // NOTE: "textures": { "overlay": "..." } in the JSON is read automatically by Forge.
        // We do NOT parse it here. We retrieve it in bake() via context.getMaterial("overlay").

        return new ObjGroupGeometry(modelPath, texturePath, alwaysVisible, groupConditions, transforms);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Geometry (Unbaked)
    // ─────────────────────────────────────────────────────────────────────────

    public static class ObjGroupGeometry implements IUnbakedGeometry<ObjGroupGeometry> {

        private final ResourceLocation modelLocation;
        private final ResourceLocation textureLocation;
        private final List<String> alwaysVisible;
        private final Map<String, Map<String, String>> groupConditions;
        private final ItemTransforms transforms;

        public ObjGroupGeometry(ResourceLocation modelLocation,
                                ResourceLocation textureLocation,
                                List<String> alwaysVisible,
                                Map<String, Map<String, String>> groupConditions,
                                ItemTransforms transforms) {
            this.modelLocation   = modelLocation;
            this.textureLocation = textureLocation;
            this.alwaysVisible   = alwaysVisible;
            this.groupConditions = groupConditions;
            this.transforms      = transforms;
        }

        @Override
        public BakedModel bake(IGeometryBakingContext context,
                               ModelBaker baker,
                               Function<Material, TextureAtlasSprite> spriteGetter,
                               ModelState modelState,
                               ItemOverrides overrides,
                               ResourceLocation modelLocation) {

            System.out.println("[ObjGroupModelLoader] bake() called for: " + this.modelLocation);

            // Base sprite — same approach as before, known to work
            TextureAtlasSprite sprite = spriteGetter.apply(
                    new Material(TextureAtlas.LOCATION_BLOCKS, this.textureLocation));

            // Overlay sprite — retrieved via context since Forge stitched it from "textures" block
            // Returns null if "overlay" key was not present in the JSON "textures" block
            TextureAtlasSprite overlaySprite = null;
            if (context.hasMaterial("overlay")) {
                overlaySprite = spriteGetter.apply(context.getMaterial("overlay"));
                System.out.println("[ObjGroupModelLoader] Overlay sprite found: " + overlaySprite.toString());
            } else {
                System.out.println("[ObjGroupModelLoader] No overlay defined, skipping.");
            }

            Map<String, List<BakedQuad>> groupQuads        = new LinkedHashMap<>();
            Map<String, List<BakedQuad>> overlayGroupQuads = new LinkedHashMap<>();

            try {
                var resource = net.minecraft.client.Minecraft.getInstance()
                        .getResourceManager()
                        .getResource(this.modelLocation);

                System.out.println("[ObjGroupModelLoader] Resource present: " + resource.isPresent());

                if (resource.isPresent()) {
                    // Buffer lines — needed to parse twice if overlay is present
                    List<String> lines = new ArrayList<>();
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(resource.get().open()))) {
                        String line;
                        while ((line = reader.readLine()) != null) lines.add(line);
                    }

                    // Base pass — tintIndex -1 (never tinted)
                    parseObjIntoGroups(lines, sprite, modelState.getRotation(), groupQuads, -1);

                    // Overlay pass — tintIndex 0 (multiplied by BlockColor/ItemColor)
                    if (overlaySprite != null) {
                        parseObjIntoGroups(lines, overlaySprite, modelState.getRotation(), overlayGroupQuads, 0);
                    }

                    System.out.println("[ObjGroupModelLoader] Groups found: " + groupQuads.keySet());
                    groupQuads.forEach((k, v) ->
                            System.out.println("  group '" + k + "' -> " + v.size() + " quads"));
                }
            } catch (IOException e) {
                System.err.println("[ObjGroupModelLoader] Failed to load OBJ: " + this.modelLocation);
                e.printStackTrace();
            }

            return new ObjGroupBakedModel(
                    groupQuads, overlayGroupQuads, sprite,
                    alwaysVisible, groupConditions,
                    context.useAmbientOcclusion(), context.isGui3d(), context.useBlockLight(),
                    overrides, transforms);
        }



        /**
         * Parse OBJ lines into per-group BakedQuad lists.
         * tintIndex: -1 = base (never tinted), 0 = overlay (multiplied by color handler).
         */
        private void parseObjIntoGroups(List<String> lines,
                                        TextureAtlasSprite sprite,
                                        Transformation transform,
                                        Map<String, List<BakedQuad>> out,
                                        int tintIndex) {
            List<Vector3f> positions = new ArrayList<>();
            List<Vector2f> uvs       = new ArrayList<>();
            List<Vector3f> normals   = new ArrayList<>();

            String currentGroup = "default";
            Map<String, List<int[][]>> rawFaces = new LinkedHashMap<>();
            rawFaces.put(currentGroup, new ArrayList<>());

            for (String rawLine : lines) {
                String line = rawLine.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("\\s+");
                switch (parts[0]) {
                    case "v" -> positions.add(new Vector3f(
                            Float.parseFloat(parts[1]),
                            Float.parseFloat(parts[2]),
                            Float.parseFloat(parts[3])));

                    case "vt" -> uvs.add(new Vector2f(
                            Float.parseFloat(parts[1]),
                            1.0f - Float.parseFloat(parts[2]))); // flip V for OpenGL

                    case "vn" -> normals.add(new Vector3f(
                            Float.parseFloat(parts[1]),
                            Float.parseFloat(parts[2]),
                            Float.parseFloat(parts[3])));

                    case "g", "o" -> {
                        currentGroup = parts.length > 1 ? parts[1] : "default";
                        System.out.println("[ObjGroupModelLoader] Switched to group: " + currentGroup);
                        rawFaces.putIfAbsent(currentGroup, new ArrayList<>());
                    }

                    case "f" -> {
                        int vertCount = parts.length - 1;
                        int[][] faceVerts = new int[vertCount][3];
                        for (int i = 0; i < vertCount; i++) {
                            String[] idx = parts[i + 1].split("/");
                            faceVerts[i][0] = Integer.parseInt(idx[0]) - 1;
                            faceVerts[i][1] = (idx.length > 1 && !idx[1].isEmpty())
                                    ? Integer.parseInt(idx[1]) - 1 : -1;
                            faceVerts[i][2] = (idx.length > 2 && !idx[2].isEmpty())
                                    ? Integer.parseInt(idx[2]) - 1 : -1;
                        }

                        List<int[][]> groupFaces = rawFaces.get(currentGroup);
                        if (vertCount == 4) {
                            groupFaces.add(faceVerts);
                        } else if (vertCount == 3) {
                            groupFaces.add(new int[][]{
                                    faceVerts[0], faceVerts[1], faceVerts[2], faceVerts[2]});
                        } else if (vertCount > 4) {
                            for (int i = 1; i < vertCount - 1; i++) {
                                int last = Math.min(i + 1, vertCount - 1);
                                groupFaces.add(new int[][]{
                                        faceVerts[0], faceVerts[i], faceVerts[last], faceVerts[last]});
                            }
                        }
                    }
                }
            }

            for (var entry : rawFaces.entrySet()) {
                List<BakedQuad> baked = buildBakedQuads(
                        entry.getValue(), positions, uvs, normals, sprite, transform, tintIndex);
                out.put(entry.getKey(), baked);
            }
        }

        private List<BakedQuad> buildBakedQuads(List<int[][]> faces,
                                                List<Vector3f> positions,
                                                List<Vector2f> uvs,
                                                List<Vector3f> normals,
                                                TextureAtlasSprite sprite,
                                                Transformation transform,
                                                int tintIndex) {
            List<BakedQuad> quads = new ArrayList<>();
            for (int[][] face : faces) {
                int[] vertexData = new int[32];

                for (int v = 0; v < 4; v++) {
                    int[] vert = face[v];

                    Vector3f pos = (vert[0] >= 0 && vert[0] < positions.size())
                            ? new Vector3f(positions.get(vert[0])) : new Vector3f(0, 0, 0);

                    Vector4f pos4 = new Vector4f(pos.x, pos.y, pos.z, 1.0f);
                    transform.getMatrix().transform(pos4);
                    pos = new Vector3f(pos4.x + 0.5f, pos4.y + 0.5f, pos4.z + 0.5f);

                    float u, uv;
                    if (vert[1] >= 0 && vert[1] < uvs.size()) {
                        Vector2f tc = uvs.get(vert[1]);
                        u  = sprite.getU(tc.x * 16.0f);
                        uv = sprite.getV(tc.y * 16.0f);
                    } else {
                        u  = sprite.getU0();
                        uv = sprite.getV0();
                    }

                    Vector3f normal = new Vector3f(0, 1, 0);
                    if (vert[2] >= 0 && vert[2] < normals.size()) {
                        normal = normals.get(vert[2]);
                    }

                    int offset = v * 8;
                    vertexData[offset]     = Float.floatToRawIntBits(pos.x);
                    vertexData[offset + 1] = Float.floatToRawIntBits(pos.y);
                    vertexData[offset + 2] = Float.floatToRawIntBits(pos.z);
                    vertexData[offset + 3] = 0xFFFFFFFF;
                    vertexData[offset + 4] = Float.floatToRawIntBits(u);
                    vertexData[offset + 5] = Float.floatToRawIntBits(uv);
                    vertexData[offset + 6] = 0;
                    vertexData[offset + 7] = packNormal(normal);
                }

                Direction facing = Direction.UP;
                if (!normals.isEmpty() && face[0][2] >= 0 && face[0][2] < normals.size()) {
                    facing = Direction.getNearest(
                            normals.get(face[0][2]).x,
                            normals.get(face[0][2]).y,
                            normals.get(face[0][2]).z);
                }

                quads.add(new BakedQuad(vertexData, tintIndex, facing, sprite, true));
            }
            return quads;
        }

        private int packNormal(Vector3f n) {
            n.normalize();
            return (clampByte((int)(n.x * 127)) & 0xFF)
                    | ((clampByte((int)(n.y * 127)) & 0xFF) << 8)
                    | ((clampByte((int)(n.z * 127)) & 0xFF) << 16);
        }

        private int clampByte(int v) { return Math.max(-128, Math.min(127, v)); }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Baked Model
    // ─────────────────────────────────────────────────────────────────────────

    public static class ObjGroupBakedModel implements IDynamicBakedModel {

        private final Map<String, List<BakedQuad>> groupQuads;
        private final Map<String, List<BakedQuad>> overlayGroupQuads;
        private final TextureAtlasSprite particle;
        private final List<String> alwaysVisible;
        private final Map<String, Map<String, String>> groupConditions;
        private final boolean ao, gui3d, sidelit;
        private final ItemOverrides overrides;
        private final ItemTransforms transforms;

        public ObjGroupBakedModel(Map<String, List<BakedQuad>> groupQuads,
                                  Map<String, List<BakedQuad>> overlayGroupQuads,
                                  TextureAtlasSprite particle,
                                  List<String> alwaysVisible,
                                  Map<String, Map<String, String>> groupConditions,
                                  boolean ao, boolean gui3d, boolean sidelit,
                                  ItemOverrides overrides,
                                  ItemTransforms transforms) {
            this.groupQuads        = groupQuads;
            this.overlayGroupQuads = overlayGroupQuads;
            this.particle          = particle;
            this.alwaysVisible     = alwaysVisible;
            this.groupConditions   = groupConditions;
            this.ao      = ao;
            this.gui3d   = gui3d;
            this.sidelit = sidelit;
            this.overrides  = overrides;
            this.transforms = transforms;
        }

        @Override
        public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state,
                                                 @Nullable Direction side,
                                                 @NotNull RandomSource rand,
                                                 @NotNull ModelData modelData,
                                                 @Nullable RenderType renderType) {
            if (side != null) return Collections.emptyList();

            // Solid pass → base quads only
            // Cutout pass → overlay quads only
            // null renderType (item) → everything
            boolean wantBase    = renderType == null || renderType == RenderType.solid();
            boolean wantOverlay = renderType == null || renderType == RenderType.cutoutMipped();

            List<BakedQuad> result = new ArrayList<>();

            if (state == null) {
                if (wantBase)    groupQuads.values().forEach(result::addAll);
                if (wantOverlay) overlayGroupQuads.values().forEach(result::addAll);
                return result;
            }

            for (String group : alwaysVisible) {
                if (wantBase)    addGroup(result, groupQuads, group);
                if (wantOverlay) addGroup(result, overlayGroupQuads, group);
            }

            for (var propEntry : groupConditions.entrySet()) {
                String propertyName = propEntry.getKey();
                Map<String, String> valueToGroup = propEntry.getValue();
                state.getProperties().stream()
                        .filter(p -> p.getName().equals(propertyName))
                        .findFirst()
                        .ifPresent(prop -> {
                            String valueStr  = getPropertyValueString(prop, state);
                            String groupName = valueToGroup.get(valueStr);
                            if (groupName != null) {
                                if (wantBase)    addGroup(result, groupQuads, groupName);
                                if (wantOverlay) addGroup(result, overlayGroupQuads, groupName);
                            }
                        });
            }

            return result;
        }

        private void addGroup(List<BakedQuad> result,
                              Map<String, List<BakedQuad>> source,
                              String group) {
            List<BakedQuad> q = source.get(group);
            if (q != null) result.addAll(q);
        }

        @SuppressWarnings("unchecked")
        private <T extends Comparable<T>> String getPropertyValueString(Property<?> prop, BlockState state) {
            Property<T> typed = (Property<T>) prop;
            return typed.getName(state.getValue(typed));
        }

        @Override public boolean useAmbientOcclusion() { return ao; }
        @Override public boolean isGui3d()             { return gui3d; }
        @Override public boolean usesBlockLight()      { return sidelit; }
        @Override public boolean isCustomRenderer()    { return false; }
        @Override
        public @NotNull ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state,
                                                          @NotNull RandomSource rand,
                                                          @NotNull ModelData data) {
            return ChunkRenderTypeSet.of(RenderType.solid(), RenderType.cutoutMipped());
        }
        @Override public @NotNull TextureAtlasSprite getParticleIcon() { return particle; }
        @Override public @NotNull ItemOverrides getOverrides()         { return overrides; }
        @Override public @NotNull ItemTransforms getTransforms()       { return transforms; }
    }
}