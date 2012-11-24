package me.heldplayer.HeldGeneration.helpers;

public enum Mat {
	// Air?
	Stone(1),
	Grass(2),
	Dirt(3),
	Cobblestone(4),
	Wood(5),
	Sapling(6),
	Bedrock(7),
	WaterMoving(8),
	WaterStill(9),
	LavaMoving(10),
	LavaStill(11),
	Sand(12),
	Gravel(13),
	GoldOre(14),
	IronOre(15),
	CoalOre(16),
	Log(17),
	Leaves(18),
	// Empty space
	LapisOre(21),
	// Empty space
	Sandstone(24),
	// Empty space
	Web(30),
	TallGrass(31),
	DeadShrub(32),
	Wool(33),
	// Empty space
	YellowFlower(37),
	RedRose(38),
	BrownMushroom(39),
	RedMushroom(40),
	// Empty space
	DoubleSlab(43),
	SingleSlab(44),
	// Empty space
	Bookshelf(47),
	MossyCobblestone(48),
	Torch(50),
	// Empty space
	MobSpawner(52),
	WoodenStair(53),
	Chest(54),
	// Empty space
	DiamondOre(56),
	// Empty space
	Crops(59),
	Farmland(60),
	FurnaceOff(61),
	// Empty space
	WoodDoor(64),
	Ladder(65),
	Rail(66),
	CobblestoneStair(67),
	// Empty space
	IronDoor(71),
	WoodenPressurePlate(72),
	RedstoneOre(73),
	// Empty space
	Button(77),
	Snow(78),
	Ice(79),
	// Empty space
	Cactus(81),
	Clay(82),
	SugarCane(83),
	// Empty space
	Fence(85),
	// Empty space
	Pumpkin(86),
	// Empty space
	SilverfishBlock(97),
	StoneBrick(98),
	GiantMushroomBrown(99),
	GiantMushroomRed(100),
	IronFence(101),
	// Empty space
	Vine(106),
	// Empty space
	BrickStairs(108),
	StoneBrickStairs(109),
	Mycelium(110),
	WaterLily(111),
	// Empty space
	NetherBrickStairs(114),
	// Empty space
	EndPortalFrame(120);

	public final short id;

	private Mat(int typeId) {
		id = (short) typeId;
	}

	public static Mat fromId(int id) {
		for (Mat mat : values()) {
			if (mat.id == id) {
				return mat;
			}
		}
		return null;
	}
}
