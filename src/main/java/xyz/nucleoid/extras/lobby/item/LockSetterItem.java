package xyz.nucleoid.extras.lobby.item;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.LockCode;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Items;
import net.minecraft.advancements.criterion.MinMaxBounds.Ints;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import xyz.nucleoid.extras.lobby.block.ContainerLockAccess;

public class LockSetterItem extends SimplePolymerItem {
    public LockSetterItem(Properties settings) {
        super(settings, Items.TRIAL_KEY, false);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var world = context.getLevel();
        var user = context.getPlayer();

        if (!world.isClientSide() && user.canUseGameMasterBlocks()) {
            var stack = context.getItemInHand();
            var newLock = stack.get(DataComponents.LOCK);

            if (newLock != null) {
                var pos = context.getClickedPos();
                var blockEntity = world.getBlockEntity(pos);

                if (blockEntity instanceof ContainerLockAccess access) {
                    var currentLock = access.getContainerLock();

                    if (currentLock == LockCode.NO_LOCK) {
                        access.setContainerLock(newLock);
                        sendFeedback(user, access, "locked");
                    } else if (!newLock.equals(currentLock)) {
                        sendFeedback(user, access, "already_locked");
                        return InteractionResult.FAIL;
                    } else {
                        access.setContainerLock(LockCode.NO_LOCK);
                        sendFeedback(user, access, "unlocked");
                    }

                    return InteractionResult.SUCCESS_SERVER;
                }
            }
        }

        return InteractionResult.PASS;
    }

    private static void sendFeedback(Player player, ContainerLockAccess access, String suffix) {
        var text = Component.translatable("text.nucleoid_extras.lock_setter." + suffix, access.getContainerLockName());
        player.displayClientMessage(text, true);

        player.playSound(SoundEvents.CHEST_LOCKED, 1, 1);
    }

    public static LockCode createUnlockableLock() {
        var predicate = ItemPredicate.Builder.item()
                .withCount(Ints.exactly(-1))
                .build();

        return new LockCode(predicate);
    }
}
