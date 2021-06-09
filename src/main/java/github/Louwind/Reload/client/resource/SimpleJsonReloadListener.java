package github.Louwind.Reload.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Lifecycle;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.SimpleRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.OptionalInt;

public abstract class SimpleJsonReloadListener<T> extends JsonReloadListener {

    protected static final Logger LOGGER = LogManager.getLogger();

    protected final Gson gson;
    protected final Type type;
    protected final SimpleRegistry<T> registry;

    public SimpleJsonReloadListener(Gson gson, Type clazz, SimpleRegistry<T> registry, String dataType) {
        super(gson, dataType);

        this.type = clazz;
        this.gson = gson;
        this.registry = registry;
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            try {
                T t = this.gson.fromJson(jsonElement, this.type);

                if(!this.registry.containsId(id))
                    BuiltinRegistries.add(this.registry, id, t);
                else {
                    Lifecycle lifecycle = this.registry.getLifecycle();
                    int rawId = this.registry.getRawId(t);

                    this.registry.getKey(t).ifPresent(key -> this.registry.replace(OptionalInt.of(rawId), key, t, lifecycle));
                }

            } catch (Exception exception) {
                LOGGER.error("Couldn't parse json {}", id, exception);
            }

        });

    }

}
