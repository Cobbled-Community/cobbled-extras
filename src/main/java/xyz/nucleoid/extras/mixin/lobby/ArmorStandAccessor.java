package xyz.nucleoid.extras.mixin.lobby;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.phys.Vec3;

@Mixin(ArmorStand.class)
public interface ArmorStandAccessor {
    @Invoker("getClickedSlot")
    EquipmentSlot callSlotFromPosition(Vec3 hitPos);

    @Invoker("setNoBasePlate")
    void callSetNoBasePlate(boolean hideBasePlate);

    @Invoker("setShowArms")
    void callSetShowArms(boolean showArms);
}
