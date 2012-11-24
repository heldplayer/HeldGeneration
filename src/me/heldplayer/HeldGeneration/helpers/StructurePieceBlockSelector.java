package me.heldplayer.HeldGeneration.helpers;

import java.util.Random;

public abstract class StructurePieceBlockSelector {
	protected int selectedBlockId;
	protected int selectedBlockMetaData;

	/**
	 * picks Block Ids and Metadata (Silverfish)
	 */
	public abstract void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5);

	public int getSelectedBlockId() {
		return this.selectedBlockId;
	}

	public int getSelectedBlockMetaData() {
		return this.selectedBlockMetaData;
	}
}