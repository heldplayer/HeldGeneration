package me.heldplayer.HeldGeneration;

import me.heldplayer.HeldGeneration.generator.ChunkProviderGenerate;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HeldGeneration extends JavaPlugin {
	private PluginDescriptionFile pdf;

	@Override
	public void onDisable() {
		String disabledMessage = this.pdf.getFullName() + " is now disabled!";

		this.pdf = null;

		getLogger().info(disabledMessage);
	}

	@Override
	public void onEnable() {
		this.pdf = getDescription();

		getLogger().info(this.pdf.getFullName() + " is now enabled!");
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return new ChunkProviderGenerate();
	}
}
