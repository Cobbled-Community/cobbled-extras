package xyz.nucleoid.extras.mixin.lobby;

import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.LockCode;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.nucleoid.extras.lobby.block.ContainerLockAccess;

@Mixin(BaseContainerBlockEntity.class)
public abstract class BaseContainerBlockEntityMixin implements ContainerLockAccess {
    @Shadow
    private LockCode lockKey;

    @Shadow
    public abstract Component getDisplayName();

    @Override
    public LockCode getContainerLock() {
        return this.lockKey;
    }

    @Override
    public void setContainerLock(LockCode lock) {
        this.lockKey = lock;
    }

    @Override
    public Component getContainerLockName() {
        return this.getDisplayName();
    }
}
