package github.Louwind.Reload.core.mixin;

import net.minecraft.util.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DynamicRegistryManager.class)
public interface AccessorDynamicRegistryManager {

    @Accessor("BUILTIN")
    static DynamicRegistryManager.Impl getRegistryManager() {
        throw new AssertionError();
    }

}