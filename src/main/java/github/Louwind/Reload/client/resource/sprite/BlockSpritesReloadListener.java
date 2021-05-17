package github.Louwind.Reload.client.resource.sprite;

import github.Louwind.Reload.client.texture.SpriteLoader;

import static net.minecraft.screen.PlayerScreenHandler.BLOCK_ATLAS_TEXTURE;

public class BlockSpritesReloadListener extends SpritesReloadListener {

    public BlockSpritesReloadListener(String id, SpriteLoader sprites) {
        super(id, BLOCK_ATLAS_TEXTURE, sprites);
    }

}
