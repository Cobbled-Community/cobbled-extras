package xyz.nucleoid.extras.lobby.block.tater;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ElderGuardianParticleTater extends CubicPotatoBlock {
	public ElderGuardianParticleTater(Properties settings, String texture) {
		super(settings, ParticleTypes.ELDER_GUARDIAN, texture, 10000);
	}

	@Override
	public int getBlockParticleChance() {
		return 50;
	}
}
