
package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenPumpkin extends WorldGenerator {
    public boolean generate(World world, Random rand, int x, int y, int z) {
        for (int attempts = 0; attempts < 64; ++attempts) {
            int posX = x + rand.nextInt(8) - rand.nextInt(8);
            int posY = y + rand.nextInt(4) - rand.nextInt(4);
            int posZ = z + rand.nextInt(8) - rand.nextInt(8);

            if (world.getBlockTypeIdAt(posX, posY, posZ) == 0 && world.getBlockTypeIdAt(posX, posY - 1, posZ) == Mat.Grass.id && BlockHelper.canBlockStay(world.getBlockAt(posX, posY, posZ), Mat.Pumpkin)) {
                setBlockAndMetadata(world, posX, posY, posZ, Mat.Pumpkin.id, rand.nextInt(4));
            }
        }

        return true;
    }
}
