package xyz.nucleoid.extras.lobby.block;

import net.minecraft.world.LockCode;
import net.minecraft.network.chat.Component;

public interface ContainerLockAccess {
    LockCode getContainerLock();

    void setContainerLock(LockCode lock);

    Component getContainerLockName();
}
