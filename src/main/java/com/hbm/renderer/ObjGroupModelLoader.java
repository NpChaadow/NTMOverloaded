package com.hbm.renderer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
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
 * OBJ Model Loader with per-group dynamic rendering — designed for cables, pipes, and
 * any block whose visible parts depend on neighboring blocks or BlockState properties.
 *
 * ── HOW IT WORKS ──────────────────────────────────────────────────────────────────────
 *
 *  1. Your .obj file uses named groups (g directive) to separate mesh parts:
 *
 *       g core          ← always rendered
 *       ...vertices...
 *       g arm_north     ← only rendered when block state "north=true"
 *       ...vertices...
 *       g arm_south
 *       ...
 *
 *  2. Your model JSON maps BlockState property values → group names:
 *
 *       {
 *         "loader": "yourmodid:obj_group_loader",
 *         "model":  "yourmodid:models/block/cable.obj",
 *         "texture": "yourmodid:block/cable",
 *         "always_visible": ["core"],
 *         "group_conditions": {
 *           "north": { "true": "arm_north" },
 *           "south": { "true": "arm_south" },
 *           "east":  { "true": "arm_east"  },
 *           "west":  { "true": "arm_west"  },
 *           "up":    { "true": "arm_up"    },
 *           "down":  { "true": "arm_down"  }
 *         }
 *       }
 *
 *  3. Your block must have matching Boolean BlockState properties
 *     (north, south, east, west, up, down) as BooleanProperty fields.
 *
 * ── REGISTRATION ──────────────────────────────────────────────────────────────────────
 *
 *   @SubscribeEvent
 *   public static void onRegisterGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
 *       event.register("obj_group_loader", ObjGroupModelLoader.INSTANCE);
 *   }
 *
 * ── OBJ FILE TIPS ─────────────────────────────────────────────────────────────────────
 *   - Group names must exactly match the values in "always_visible" and "group_conditions".
 *   - Faces can be triangles or quads. Polygons are fan-triangulated into quads.
 *   - UVs are optional but recommended for textured rendering.
 *   - Model space: 0.0–1.0 maps to one full block (Minecraft's coordinate space).
 */
public class ObjGroupModelLoader implements IGeometryLoader<ObjGroupModelLoader.ObjGroupGeometry> {

    public static final ObjGroupModelLoader INSTANCE = new ObjGroupModelLoader();

    private ResourceLocation parseResourceLocation(String full) {
        return ResourceLocation.parse(full);
    }
    @Override
    public ObjGroupGeometry read(JsonObject json, JsonDeserializationContext context) {
        System.out.println("[ObjGroupModelLoader] read() called! JSON: " + json);
        ResourceLocation modelPath   = parseResourceLocation(json.get("model").getAsString());
        ResourceLocation texturePath = json.has("texture")
                ? parseResourceLocation(json.get("texture").getAsString())
                : ResourceLocation.parse("missingno");

        ItemTransforms transforms = ItemTransforms.NO_TRANSFORMS;
        if (json.has("display")) {
            transforms = context.deserialize(json.get("display"), ItemTransforms.class);
        }

        // Parse always_visible list
        List<String> alwaysVisible = new ArrayList<>();
        if (json.has("always_visible")) {
            json.getAsJsonArray("always_visible")
                .forEach(e -> alwaysVisible.add(e.getAsString()));
        }

        // Parse group_conditions: { "propertyName": { "value": "groupName", ... }, ... }
        // e.g. { "north": { "true": "arm_north" } }
        Map<String, Map<String, String>> groupConditions = new LinkedHashMap<>();
        if (json.has("group_conditions")) {
            JsonObject conditions = json.getAsJsonObject("group_conditions");
            conditions.entrySet().forEach(prop -> {
                Map<String, String> valueMap = new LinkedHashMap<>();
                prop.getValue().getAsJsonObject().entrySet()
                    .forEach(val -> valueMap.put(val.getKey(), val.getValue().getAsString()));
                groupConditions.put(prop.getKey(), valueMap);
            });
        }

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
            this.modelLocation  = modelLocation;
            this.textureLocation = textureLocation;
            this.alwaysVisible  = alwaysVisible;
            this.groupConditions = groupConditions;
            this.transforms = transforms;
        }

        @Override
        public BakedModel bake(IGeometryBakingContext context,
                               ModelBaker baker,
                               Function<Material, TextureAtlasSprite> spriteGetter,
                               ModelState modelState,
                               ItemOverrides overrides,
                               ResourceLocation modelLocation) {
            System.out.println("[ObjGroupModelLoader] bake() called for: " + this.modelLocation);

            Material material = new Material(
                    net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS,
                    this.textureLocation);
            TextureAtlasSprite sprite = spriteGetter.apply(material);

            // group name → list of BakedQuads
            Map<String, List<BakedQuad>> groupQuads = new LinkedHashMap<>();

            try {
                var resource = net.minecraft.client.Minecraft.getInstance()
                        .getResourceManager()
                        .getResource(this.modelLocation);
                System.out.println("[ObjGroupModelLoader] Resource present: " + resource.isPresent());
                if (resource.isPresent()) {
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(resource.get().open()))) {
                        parseObjIntoGroups(reader, sprite, modelState.getRotation(), groupQuads);
                    }
                }
            } catch (IOException e) {
                System.err.println("[ObjGroupModelLoader] Failed to load OBJ: " + this.modelLocation);
                e.printStackTrace();
            }

            return new ObjGroupBakedModel(
                    groupQuads, sprite,
                    alwaysVisible, groupConditions,
                    context.useAmbientOcclusion(), context.isGui3d(), context.useBlockLight(),
                    overrides,transforms);
        }

        /**
         * Parse the OBJ file, placing each face into the group it belongs to.
         * Groups switch when a "g" or "o" line is encountered.
         */
        private void parseObjIntoGroups(BufferedReader reader,
                                        TextureAtlasSprite sprite,
                                        Transformation transform,
                                        Map<String, List<BakedQuad>> out) throws IOException {
            List<Vector3f> positions = new ArrayList<>();
            List<Vector2f> uvs       = new ArrayList<>();
            List<Vector3f> normals   = new ArrayList<>();

            // Raw faces stored per group before baking
            String currentGroup = "default";
            // group → list of face vertex index arrays
            Map<String, List<int[][]>> rawFaces = new LinkedHashMap<>();
            rawFaces.put(currentGroup, new ArrayList<>());

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("\\s+");
                switch (parts[0]) {
                    case "v" -> positions.add(new Vector3f(
                            Float.parseFloat(parts[1]),
                            Float.parseFloat(parts[2]),
                            Float.parseFloat(parts[3])));
                    case "vt" -> uvs.add(new Vector2f(
                            Float.parseFloat(parts[1]),
                            1.0f - Float.parseFloat(parts[2]))); // flip V
                    case "vn" -> normals.add(new Vector3f(
                            Float.parseFloat(parts[1]),
                            Float.parseFloat(parts[2]),
                            Float.parseFloat(parts[3])));
                    case "g", "o" -> {
                        // Switch active group (use first token after directive)
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
                        // Emit as quads (fan triangulation for n>4)
                        List<int[][]> quadsForGroup = rawFaces.get(currentGroup);
                        if (vertCount == 4) {
                            quadsForGroup.add(faceVerts);
                        } else if (vertCount == 3) {
                            quadsForGroup.add(new int[][]{
                                faceVerts[0], faceVerts[1], faceVerts[2], faceVerts[2]});
                        } else if (vertCount > 4) {
                            for (int i = 1; i < vertCount - 1; i++) {
                                int last = Math.min(i + 1, vertCount - 1);
                                quadsForGroup.add(new int[][]{
                                    faceVerts[0], faceVerts[i], faceVerts[last], faceVerts[last]});
                            }
                        }
                    }
                }
            }

            // Bake each group independently
            for (var entry : rawFaces.entrySet()) {
                List<BakedQuad> baked = buildBakedQuads(
                        entry.getValue(), positions, uvs, normals, sprite, transform);
                out.put(entry.getKey(), baked);
            }
        }

        private List<BakedQuad> buildBakedQuads(List<int[][]> faces,
                                                 List<Vector3f> positions,
                                                 List<Vector2f> uvs,
                                                 List<Vector3f> normals,
                                                 TextureAtlasSprite sprite,
                                                 Transformation transform) {
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

                quads.add(new BakedQuad(vertexData, -1, facing, sprite, true));
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
    // Baked Model — selects groups at render time based on BlockState
    // ─────────────────────────────────────────────────────────────────────────

    public static class ObjGroupBakedModel implements IDynamicBakedModel {

        /** All group quads, keyed by OBJ group name. */
        private final Map<String, List<BakedQuad>> groupQuads;
        private final TextureAtlasSprite particle;
        private final ItemTransforms transforms;

        /** Groups that are always included regardless of block state. */
        private final List<String> alwaysVisible;

        /**
         * Maps BlockState property name → (property value string → group name).
         * e.g. "north" → { "true" → "arm_north" }
         */
        private final Map<String, Map<String, String>> groupConditions;

        private final boolean ao, gui3d, sidelit;
        private final ItemOverrides overrides;

        public ObjGroupBakedModel(Map<String, List<BakedQuad>> groupQuads,
                                  TextureAtlasSprite particle,
                                  List<String> alwaysVisible,
                                  Map<String, Map<String, String>> groupConditions,
                                  boolean ao, boolean gui3d, boolean sidelit,
                                  ItemOverrides overrides, ItemTransforms transforms) {
            this.groupQuads      = groupQuads;
            this.particle        = particle;
            this.transforms = transforms;
            this.alwaysVisible   = alwaysVisible;
            this.groupConditions = groupConditions;
            this.ao      = ao;
            this.gui3d   = gui3d;
            this.sidelit = sidelit;
            this.overrides = overrides;

        }



        /**
         * Core rendering method — evaluates the BlockState to decide which groups to show.
         *
         * Called by Minecraft's chunk batcher on every block in the world.
         * The result is cached per BlockState variant, so this is NOT called every frame.
         */
        @Override
        public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state,
                                                 @Nullable Direction side,
                                                 @NotNull RandomSource rand,
                                                 @NotNull ModelData modelData,
                                                 @NotNull RenderType renderType){
            List<BakedQuad> result = new ArrayList<>();

            // 1. Always-visible groups
            for (String group : alwaysVisible) {
                List<BakedQuad> q = groupQuads.get(group);
                if (q != null) result.addAll(q);
            }

            // 2. Conditional groups driven by BlockState
            if (state != null) {
                for (var propEntry : groupConditions.entrySet()) {
                    String propertyName = propEntry.getKey();
                    Map<String, String> valueToGroup = propEntry.getValue();

                    // Find the property in the BlockState
                    state.getProperties().stream()
                        .filter(p -> p.getName().equals(propertyName))
                        .findFirst()
                        .ifPresent(prop -> {
                            // Get current value as string
                            String valueStr = getPropertyValueString(prop, state); // ✅ no more type error
                            String groupName = valueToGroup.get(valueStr);
                            if (groupName != null) {
                                List<BakedQuad> q = groupQuads.get(groupName);
                                if (q != null) result.addAll(q);
                            }
                        });
                }
            }

            return result;
        }

        /**
         * Helper: safely casts a Comparable to the type expected by the property's getName().
         * This is needed because of Java's raw/generic type erasure with BlockState properties.
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private String castPropertyValue(Comparable value) {
            // Property.getName(T value) needs T; since we got the value from the same property,
            // this cast is always safe at runtime.
            return value.toString();
        }

        // ── Standard BakedModel boilerplate ───────────────────────────────────

        @Override public boolean useAmbientOcclusion() { return ao; }
        @Override public boolean isGui3d()             { return gui3d; }
        @Override public boolean usesBlockLight()      { return sidelit; }
        @Override public boolean isCustomRenderer()    { return false; }
        @Override public @NotNull TextureAtlasSprite getParticleIcon() { return particle; }
        @Override public @NotNull ItemOverrides getOverrides()         { return overrides; }
        @Override public @NotNull ItemTransforms getTransforms()       { return transforms; }
    }

    // Replace the castPropertyValue helper and the problematic block with this:

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> String getPropertyValueString(net.minecraft.world.level.block.state.properties.Property<?> prop, BlockState state) {
        Property<T> typedProp = (Property<T>) prop;
        return typedProp.getName(state.getValue(typedProp));
    }
}