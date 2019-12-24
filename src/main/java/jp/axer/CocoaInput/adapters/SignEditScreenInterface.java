package jp.axer.CocoaInput.adapters;

import net.minecraft.block.entity.SignBlockEntity;

public interface SignEditScreenInterface extends ScreenInterface {
    SignBlockEntity getBlockTile();
    int getRow();
}
