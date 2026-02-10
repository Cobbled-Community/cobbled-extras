package xyz.nucleoid.extras.mixin.compat;


import net.minecraft.world.entity.Entity;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("MissingUnique")
@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @Shadow
    public abstract <T extends ParticleOptions> int sendParticles(T parameters, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double speed);

    @Shadow
    public abstract <T extends ParticleOptions> boolean sendParticles(ServerPlayer viewer, T parameters, boolean force, boolean important, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double speed);

    // spawnParticles from 1.21.3
    public <T extends ParticleOptions> int method_14199(T parameters, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double speed) {
        return this.sendParticles(parameters, x, y, z, count, offsetX, offsetY, offsetZ, speed);
    }

    // spawnParticles from 1.21.3
    public <T extends ParticleOptions> boolean method_14166(ServerPlayer viewer, T parameters, boolean force, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double speed) {
        return this.sendParticles(viewer, parameters, force, false, x, y , z, count, offsetX, offsetY, offsetZ, speed);
    }

}
