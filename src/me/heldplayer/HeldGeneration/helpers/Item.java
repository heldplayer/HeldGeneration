
package me.heldplayer.HeldGeneration.helpers;

public enum Item {
    // Nothing below 256
    IronPickaxe(257),
    // Empty space
    Coal(263), Diamond(264), IronIngot(265), GoldIngot(266),
    // Empty space
    Bread(297),
    // Empty space
    Redstone(331),
    // Empty space
    Dye(351),
    // Empty space
    MelonSeeds(362), PumpkinSeeds(361);

    public final short id;

    private Item(int typeId) {
        id = (short) typeId;
    }

    public static Item fromId(int id) {
        for (Item item : values()) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }
}
