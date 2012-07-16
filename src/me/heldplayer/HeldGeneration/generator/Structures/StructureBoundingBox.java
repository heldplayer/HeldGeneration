package me.heldplayer.HeldGeneration.generator.Structures;

public class StructureBoundingBox {
	/** The first x coordinate of a bounding box. */
	public int minX;

	/** The first y coordinate of a bounding box. */
	public int minY;

	/** The first z coordinate of a bounding box. */
	public int minZ;

	/** The second x coordinate of a bounding box. */
	public int maxX;

	/** The second y coordinate of a bounding box. */
	public int maxY;

	/** The second z coordinate of a bounding box. */
	public int maxZ;

	public StructureBoundingBox() {
	}

	/**
	 * returns a new StructureBoundingBox with MAX values
	 */
	public static StructureBoundingBox getNewBoundingBox() {
		return new StructureBoundingBox(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * used to project a possible new component Bounding Box - to check if it
	 * would cut anything already spawned
	 */
	public static StructureBoundingBox getComponentToAddBoundingBox(int par0, int par1, int par2, int par3, int par4, int par5, int par6, int par7, int par8, int componentType) {
		switch (componentType) {
		case 0:
			return new StructureBoundingBox(par0 + par3, par1 + par4, par2 + par5, par0 + par6 - 1 + par3, par1 + par7 - 1 + par4, par2 + par8 - 1 + par5);

		case 1:
			return new StructureBoundingBox(par0 - par8 + 1 + par5, par1 + par4, par2 + par3, par0 + par5, par1 + par7 - 1 + par4, par2 + par6 - 1 + par3);

		case 2:
			return new StructureBoundingBox(par0 + par3, par1 + par4, par2 - par8 + 1 + par5, par0 + par6 - 1 + par3, par1 + par7 - 1 + par4, par2 + par5);

		case 3:
			return new StructureBoundingBox(par0 + par5, par1 + par4, par2 + par3, par0 + par8 - 1 + par5, par1 + par7 - 1 + par4, par2 + par6 - 1 + par3);

		default:
			return new StructureBoundingBox(par0 + par3, par1 + par4, par2 + par5, par0 + par6 - 1 + par3, par1 + par7 - 1 + par4, par2 + par8 - 1 + par5);
		}
	}

	public StructureBoundingBox(StructureBoundingBox originalBox) {
		this.minX = originalBox.minX;
		this.minY = originalBox.minY;
		this.minZ = originalBox.minZ;
		this.maxX = originalBox.maxX;
		this.maxY = originalBox.maxY;
		this.maxZ = originalBox.maxZ;
	}

	public StructureBoundingBox(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}

	public StructureBoundingBox(int minX, int minZ, int maxX, int maxZ) {
		this.minX = minX;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxZ = maxZ;
		this.minY = 1;
		this.maxY = 512;
	}

	/**
	 * Returns whether the given bounding box intersects with this one. Args:
	 * structureboundingbox
	 */
	public boolean intersectsWith(StructureBoundingBox boundingBox) {
		return this.maxX >= boundingBox.minX && this.minX <= boundingBox.maxX && this.maxZ >= boundingBox.minZ && this.minZ <= boundingBox.maxZ && this.maxY >= boundingBox.minY && this.minY <= boundingBox.maxY;
	}

	/**
	 * Discover if a coordinate is inside the bounding box area.
	 */
	public boolean intersectsWith(int startX, int startZ, int endX, int endZ) {
		return this.maxX >= startX && this.minX <= endX && this.maxZ >= startZ && this.minZ <= endZ;
	}

	/**
	 * Expands a bounding box's dimensions to include the supplied bounding box.
	 */
	public void expandTo(StructureBoundingBox boundingBox) {
		this.minX = Math.min(this.minX, boundingBox.minX);
		this.minY = Math.min(this.minY, boundingBox.minY);
		this.minZ = Math.min(this.minZ, boundingBox.minZ);
		this.maxX = Math.max(this.maxX, boundingBox.maxX);
		this.maxY = Math.max(this.maxY, boundingBox.maxY);
		this.maxZ = Math.max(this.maxZ, boundingBox.maxZ);
	}

	/**
	 * Offsets the current bounding box by the specified coordinates. Args: x,
	 * y, z
	 */
	public void offset(int x, int y, int z) {
		this.minX += x;
		this.minY += y;
		this.minZ += z;
		this.maxX += x;
		this.maxY += y;
		this.maxZ += z;
	}

	/**
	 * Returns true if block is inside bounding box
	 */
	public boolean isVecInside(int x, int y, int z) {
		return x >= this.minX && x <= this.maxX && z >= this.minZ && z <= this.maxZ && y >= this.minY && y <= this.maxY;
	}

	/**
	 * Returns width of a bounding box
	 */
	public int getXSize() {
		return this.maxX - this.minX + 1;
	}

	/**
	 * Returns height of a bounding box
	 */
	public int getYSize() {
		return this.maxY - this.minY + 1;
	}

	/**
	 * Returns length of a bounding box
	 */
	public int getZSize() {
		return this.maxZ - this.minZ + 1;
	}

	public int getCenterX() {
		return this.minX + (this.maxX - this.minX + 1) / 2;
	}

	public int getCenterY() {
		return this.minY + (this.maxY - this.minY + 1) / 2;
	}

	public int getCenterZ() {
		return this.minZ + (this.maxZ - this.minZ + 1) / 2;
	}

	@Override
	public String toString() {
		return "(" + this.minX + ", " + this.minY + ", " + this.minZ + "; " + this.maxX + ", " + this.maxY + ", " + this.maxZ + ")";
	}
}