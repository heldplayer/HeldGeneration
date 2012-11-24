
package me.heldplayer.HeldGeneration.generator.MapGenerators;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.ChunkProvider;
import me.heldplayer.HeldGeneration.generator.Structures.StructureStart;
import me.heldplayer.HeldGeneration.generator.Structures.StructureVillageStart;
import me.heldplayer.HeldGeneration.helpers.BiomeHelper;

import org.bukkit.block.Biome;

public class MapGenVillage extends MapGenStructure {
    /** A list of all the biomes villages can spawn in. */
    public static List<Biome> villageSpawnBiomes = Arrays.asList(new Biome[] { Biome.PLAINS, Biome.DESERT });

    /** World terrain type, 0 for normal, 1 for flat map */
    private final int terrainType;

    public MapGenVillage(int terrainType, BiomeHelper helper) {
        this.terrainType = terrainType;
        this.helper = helper;
    }

    private final BiomeHelper helper;

    @Override
    protected boolean canSpawnStructureAtCoords(int cx, int cz, ChunkProvider provider) {
        byte var3 = 32;
        byte var4 = 8;
        int var5 = cx;
        int var6 = cz;

        if (cx < 0) {
            cx -= var3 - 1;
        }

        if (cz < 0) {
            cz -= var3 - 1;
        }

        int var7 = cx / var3;
        int var8 = cz / var3;
        Random var9 = new Random(var7 * 341873128712L + var8 * 132897987541L + this.worldObj.getSeed() + 10387312);
        var7 *= var3;
        var8 *= var3;
        var7 += var9.nextInt(var3 - var4);
        var8 += var9.nextInt(var3 - var4);

        if (var5 == var7 && var6 == var8) {
            boolean var10 = provider.generator.helper.areBiomesViable(var5 * 16 + 8, var6 * 16 + 8, 0, villageSpawnBiomes);

            if (var10) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int cx, int cz) {
        return new StructureVillageStart(this.worldObj, this.rand, cx, cz, this.terrainType, this.helper);
    }
}
