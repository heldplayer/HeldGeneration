package me.heldplayer.HeldGeneration.helpers;

import org.bukkit.entity.EntityType;

public class SpawnListEntry extends WeightedRandomChoice {
	/** Holds the class of the entity to be spawned. */
	public EntityType entityType;
	public int minGroupCount;
	public int maxGroupCount;

	public SpawnListEntry(EntityType type, int weight, int minCount, int maxCount) {
		super(weight);
		this.entityType = type;
		this.minGroupCount = minCount;
		this.maxGroupCount = maxCount;
	}
}