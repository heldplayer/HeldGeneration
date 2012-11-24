package me.heldplayer.HeldGeneration.helpers;

public class WeightedRandomChoice {
	/**
	 * The Weight is how often the item is chosen(higher number is higher
	 * chance(lower is lower))
	 */
	protected int itemWeight;

	public WeightedRandomChoice(int weight) {
		this.itemWeight = weight;
	}
}