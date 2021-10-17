package github.Louwind.Reload.client.color.resolver;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public interface FluidColorProvider {

    FluidColorProvider NONE = (view, pos, state) -> -1;

    int getColor(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state);

}
