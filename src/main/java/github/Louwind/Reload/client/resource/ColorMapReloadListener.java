package github.Louwind.Reload.client.resource;

import github.Louwind.Reload.client.color.ColorMap;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.client.util.RawTextureDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ColorMapReloadListener implements SimpleResourceReloadListener<int[]> {

    protected final ColorMap colorMap;
    protected final Identifier id;

    public ColorMapReloadListener(String id, ColorMap colorMap) {
        this.colorMap = colorMap;
        this.id = Identifier.tryParse(id);
    }

    @Override
    public CompletableFuture<int[]> load(ResourceManager resourceManager, Profiler profiler, Executor executor) {

        try {
            return CompletableFuture.completedFuture(RawTextureDataLoader.loadRawTextureData(resourceManager, this.id));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load Color Map texture: " + this.id, e);
        }

    }

    @Override
    public CompletableFuture<Void> apply(int[] ints, ResourceManager resourceManager, Profiler profiler, Executor executor) {
        return CompletableFuture.runAsync(() -> this.colorMap.setColorMap(ints));
    }

    @Override
    public Identifier getFabricId() {
        return this.id;
    }

}