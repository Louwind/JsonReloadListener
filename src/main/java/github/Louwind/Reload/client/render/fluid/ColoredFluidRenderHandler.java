package github.Louwind.Reload.client.render.fluid;

import github.Louwind.Reload.client.color.resolver.FluidColorProvider;
import github.Louwind.Reload.client.texture.SpriteLoader;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

public class ColoredFluidRenderHandler implements FluidRenderHandler {

    protected final FluidColorProvider colorProvider;
    protected final SpriteLoader spriteLoader;

    public ColoredFluidRenderHandler(FluidColorProvider colorProvider, SpriteLoader spriteLoader) {
        this.colorProvider = colorProvider;
        this.spriteLoader = spriteLoader;
    }

    @Override
    public int getFluidColor(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state) {
        return this.colorProvider.getColor(view, pos, state);
    }

    @Override
    public Sprite[] getFluidSprites(@Nullable BlockRenderView blockRenderView, @Nullable BlockPos blockPos, FluidState fluidState) {
        return this.spriteLoader.geSprites();
    }

}
