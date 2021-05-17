package github.Louwind.Reload.client.resource;

import github.Louwind.Reload.client.texture.SpriteLoader;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SpritesReloadListener implements SimpleSynchronousResourceReloadListener {

    private final Identifier atlas;
    protected final Identifier id;
    protected final SpriteLoader sprites;

    public SpritesReloadListener(String id, String atlas, SpriteLoader sprites) {
        this.atlas = Identifier.tryParse(atlas);
        this.id = Identifier.tryParse(id);
        this.sprites = sprites;
    }

    @Override
    public void apply(ResourceManager manager) {
        Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(this.atlas);

        this.sprites.load(atlas);
    }

    @Override
    public Identifier getFabricId() {
        return this.id;
    }

}
