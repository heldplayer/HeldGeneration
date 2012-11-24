
package me.heldplayer.HeldGeneration;

import java.io.File;

import me.heldplayer.HeldGeneration.generator.ChunkProvider;
import me.heldplayer.HeldGeneration.profiler.Profiler;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HeldGeneration extends JavaPlugin {
    private PluginDescriptionFile pdf;

    @Override
    public void onDisable() {
        Profiler.startSection("disable");

        String disabledMessage = this.pdf.getFullName() + " is now disabled!";

        this.pdf = null;

        getLogger().info(disabledMessage);

        Profiler.endSection();

        Profiler.endAll();

        Profiler.saveResults(new File(this.getDataFolder(), "profiler.txt"));
    }

    @Override
    public void onEnable() {
        Profiler.startSection("enable");

        File dataFolder = this.getDataFolder();

        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        this.pdf = getDescription();

        getLogger().info(this.pdf.getFullName() + " is now enabled!");

        Profiler.endSection();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        Profiler.startSection("provider");
        Profiler.startSection("instantiate");

        ChunkProvider provider = new ChunkProvider();

        Profiler.endSection();
        Profiler.endSection();

        return provider;
    }
}
