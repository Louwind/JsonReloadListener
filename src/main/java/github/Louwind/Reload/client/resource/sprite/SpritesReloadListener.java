package github.Louwind.Reload.client.resource.sprite;

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

    public SpritesReloadListener(String id, Identifier atlas, SpriteLoader sprites) {
        this.id = Identifier.tryParse(id);
        this.atlas = atlas;
        this.sprites = sprites;
    }

    @Override
    public void reload(ResourceManager manager) {
        Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(this.atlas);

        this.sprites.load(atlas);
    }

    @Override
    public Identifier getFabricId() {
        return this.id;
    }

}
