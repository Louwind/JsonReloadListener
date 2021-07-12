package github.Louwind.Reload.core.event;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.world.BiomeColorCache;
import net.minecraft.world.level.ColorResolver;

public class ClientWorldEvents {

    public static final Event<ColorCacheLoad> COLOR_CACHE_LOAD = EventFactory.createArrayBacked(ColorCacheLoad.class, callbacks -> colorCache -> {

        for (ColorCacheLoad callback : callbacks) {
            callback.onLoad(colorCache);
        }

    });

    @FunctionalInterface
    public interface ColorCacheLoad {

        void onLoad(Object2ObjectArrayMap<ColorResolver, BiomeColorCache> colorCache);

    }

}