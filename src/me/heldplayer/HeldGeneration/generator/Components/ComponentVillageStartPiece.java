package me.heldplayer.HeldGeneration.generator.Components;

import java.util.ArrayList;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureVillagePieceWeight;
import me.heldplayer.HeldGeneration.helpers.BiomeHelper;

public class ComponentVillageStartPiece extends ComponentVillageWell {
	/** World terrain type, 0 for normal, 1 for flap map */
	public int terrainType;
	public StructureVillagePieceWeight structVillagePieceWeight;

	/**
	 * Contains List of all spawnable Structure Piece Weights. If no more Pieces
	 * of a type can be spawned, they are
	 * removed from this list
	 */
	public ArrayList<StructureVillagePieceWeight> structureVillageWeightedPieceList;
	public ArrayList field_35108_e = new ArrayList();
	public ArrayList field_35106_f = new ArrayList();

	public final BiomeHelper helper;

	public ComponentVillageStartPiece(int par2, Random par3Random, int par4, int par5, ArrayList<StructureVillagePieceWeight> par6ArrayList, int par7, BiomeHelper helper) {
		super(0, par3Random, par4, par5);
		this.structureVillageWeightedPieceList = par6ArrayList;
		this.terrainType = par7;
		this.helper = helper;
	}
}