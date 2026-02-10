package xyz.nucleoid.extras.lobby.item.tater;

import net.minecraft.world.item.Item.Properties;

public class CreativeTaterBoxItem extends TaterBoxItem {
    private static final int COLOR = 0xFF00FF;

    public CreativeTaterBoxItem(Properties settings) {
        super(settings);
    }

    @Override
    protected int getEmptyColor() {
        return COLOR;
    }

    @Override
    protected boolean isCreative() {
        return true;
    }
}
