package jp.axer.CocoaInput.mixin;

import jp.axer.CocoaInput.adapters.SignEditScreenInterface;
import jp.axer.CocoaInput.wrapper.SignEditScreenWrapper;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignEditScreen.class)
public abstract class SignEditScreenMixin extends Screen implements SignEditScreenInterface {
    public SignEditScreenWrapper wrapper;

    @Shadow
    private SignBlockEntity sign;

    @Shadow
    private int currentRow;

    private SignEditScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init()V", at=@At("HEAD"))
    private void init(CallbackInfo cb){
        wrapper = new SignEditScreenWrapper((SignEditScreen) (Object)this);
    }

    @Override
    public SignBlockEntity getBlockTile(){
        return sign;
    }

    @Override
    public TextRenderer getFont() {
        return font;
    }

    public int getRow(){
        return currentRow;
    }
}
