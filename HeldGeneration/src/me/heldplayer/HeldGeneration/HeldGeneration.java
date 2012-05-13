package me.heldplayer.HeldGeneration;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HeldGeneration extends JavaPlugin {
	private PluginDescriptionFile pdf;

	@Override
	public void onDisable() {
		pdf = null;

		getLogger().info(pdf.getFullName() + " is now disabled!");
	}

	@Override
	public void onEnable() {
		pdf = getDescription();

		getLogger().info(pdf.getFullName() + " is now enabled!");
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return null;
	}
}
