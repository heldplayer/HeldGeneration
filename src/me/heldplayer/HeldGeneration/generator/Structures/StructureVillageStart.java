package me.heldplayer.HeldGeneration.generator.Structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageRoadPiece;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageStartPiece;
import me.heldplayer.HeldGeneration.helpers.BiomeHelper;

import org.bukkit.World;

public class StructureVillageStart extends StructureStart {
	/** well ... thats what it does */
	private boolean hasMoreThanTwoComponents = false;

	public StructureVillageStart(World par1World, Random par2Random, int par3, int par4, int par5, BiomeHelper helper) {
		ArrayList pieces = StructureVillagePieces.getStructureVillageWeightedPieceList(par2Random, par5);
		ComponentVillageStartPiece startPiece = new ComponentVillageStartPiece(0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2, pieces, par5, helper);
		this.components.add(startPiece);
		startPiece.buildComponent(startPiece, this.components, par2Random);
		ArrayList var9 = startPiece.field_35106_f;
		ArrayList var10 = startPiece.field_35108_e;
		int var11;

		while (!var9.isEmpty() || !var10.isEmpty()) {
			StructureComponent var12;

			if (!var9.isEmpty()) {
				var11 = par2Random.nextInt(var9.size());
				var12 = (StructureComponent) var9.remove(var11);
				var12.buildComponent(startPiece, this.components, par2Random);
			} else {
				var11 = par2Random.nextInt(var10.size());
				var12 = (StructureComponent) var10.remove(var11);
				var12.buildComponent(startPiece, this.components, par2Random);
			}
		}

		updateBoundingBox();
		var11 = 0;
		Iterator var14 = this.components.iterator();

		while (var14.hasNext()) {
			StructureComponent var13 = (StructureComponent) var14.next();

			if (!(var13 instanceof ComponentVillageRoadPiece)) {
				++var11;
			}
		}

		this.hasMoreThanTwoComponents = var11 > 2;
	}

	/**
	 * currently only defined for Villages, returns true if Village has more
	 * than 2 non-road components
	 */
	@Override
	public boolean isSizeableStructure() {
		return this.hasMoreThanTwoComponents;
	}
}
