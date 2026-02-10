package xyz.nucleoid.extras.mixin.dialog;

import com.mojang.serialization.Decoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.dialog.Dialog;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.packs.resources.ResourceManager;
import xyz.nucleoid.extras.dialog.NEDialogs;

import java.util.Map;

@Mixin(RegistryDataLoader.class)
public class RegistryDataLoaderMixin {
    @SuppressWarnings("unchecked")
    @Inject(method = "loadContentsFromManager(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps$RegistryInfoLookup;Lnet/minecraft/core/WritableRegistry;Lcom/mojang/serialization/Decoder;Ljava/util/Map;)V", at = @At("HEAD"))
    private static void registerNucleoidExtrasDialogs(ResourceManager resourceManager, RegistryOps.RegistryInfoLookup infoGetter, WritableRegistry<?> registry, Decoder<?> elementDecoder, Map<ResourceKey<?>, Exception> errors, CallbackInfo ci) {
        if (registry.key() == Registries.DIALOG) {
            NEDialogs.register((WritableRegistry<Dialog>) registry);
        }
    }
}
