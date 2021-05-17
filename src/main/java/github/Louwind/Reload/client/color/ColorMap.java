package github.Louwind.Reload.client.color;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface ColorMap {

    void setColorMap(int[] pixels);

}
