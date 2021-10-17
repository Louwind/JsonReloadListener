package github.Louwind.Reload.client.texture;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class SpriteLoader implements SimpleSynchronousResourceReloadListener {

    protected final ImmutableList<Identifier> textures;
    protected final Identifier atlas;
    protected final Identifier id;
    @Nullable
    protected Sprite[] sprites;

    public SpriteLoader(Identifier id, Identifier atlas, ImmutableList<Identifier> textures) {
        this.id = id;
        this.atlas = atlas;
        this.textures = textures;
    }

    public Sprite[] getSprites() {
        return this.sprites;
    }

    @Override
    public Identifier getFabricId() {
        return this.id;
    }

    @Override
    public void reload(ResourceManager manager) {
        var atlas = MinecraftClient.getInstance().getSpriteAtlas(this.atlas);

        this.sprites = this.textures.stream().map(atlas).toArray(Sprite[]::new);
    }

}
