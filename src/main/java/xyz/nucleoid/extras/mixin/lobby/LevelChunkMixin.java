package xyz.nucleoid.extras.mixin.lobby;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import xyz.nucleoid.extras.lobby.block.ContributorStatueBlockEntity;

@Mixin(LevelChunk.class)
public class LevelChunkMixin {
    @Inject(method = "addGameEventListener", at = @At("TAIL"))
    private void callMethod(BlockEntity blockEntity, ServerLevel world, CallbackInfo ci) {
        if (blockEntity instanceof ContributorStatueBlockEntity contributorStatue) {
            contributorStatue.attachElementHolder((LevelChunk) (Object) this);
        }
    }
}
