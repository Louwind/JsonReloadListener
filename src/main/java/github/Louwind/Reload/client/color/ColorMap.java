package github.Louwind.Reload.client.color;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.client.util.RawTextureDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Environment(EnvType.CLIENT)
public interface ColorMap extends SimpleResourceReloadListener<int[]> {

    void setPixels(int[] pixels);

    Identifier getTexture();

    @Override
    default Identifier getFabricId() {
        return this.getTexture();
    }

    @Override
    default CompletableFuture<Void> apply(int[] pixels, ResourceManager resourceManager, Profiler profiler, Executor executor) {
        return CompletableFuture.runAsync(() -> this.setPixels(pixels));
    }

    @Override
    default CompletableFuture<int[]> load(ResourceManager resourceManager, Profiler profiler, Executor executor) {
        var texture = this.getTexture();

        try {
            return CompletableFuture.completedFuture(RawTextureDataLoader.loadRawTextureData(resourceManager, texture));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load Color Map: " + texture, e);
        }

    }

}
