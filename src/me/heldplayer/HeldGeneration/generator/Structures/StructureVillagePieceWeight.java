package me.heldplayer.HeldGeneration.generator.Structures;

import me.heldplayer.HeldGeneration.generator.Components.ComponentVillage;

public class StructureVillagePieceWeight {
	/** The Class object for the represantation of this village piece. */
	public Class<? extends ComponentVillage> villagePieceClass;
	public final int villagePieceWeight;
	public int villagePiecesSpawned;
	public int villagePiecesLimit;

	public StructureVillagePieceWeight(Class<? extends ComponentVillage> par1Class, int par2, int par3) {
		this.villagePieceClass = par1Class;
		this.villagePieceWeight = par2;
		this.villagePiecesLimit = par3;
	}

	public boolean canSpawnMoreVillagePiecesOfType(int par1) {
		return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
	}

	public boolean canSpawnMoreVillagePieces() {
		return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
	}
}