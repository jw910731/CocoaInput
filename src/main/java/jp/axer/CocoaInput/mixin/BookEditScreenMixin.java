package jp.axer.CocoaInput.mixin;

import jp.axer.CocoaInput.adapters.BookEditScreenInterface;
import jp.axer.CocoaInput.wrapper.BookEditScreenWrapper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BookEditScreen.class)
public abstract class BookEditScreenMixin extends Screen implements BookEditScreenInterface {
    private BookEditScreenWrapper wrapper;

    @Shadow
    private boolean signing;

    @Shadow
    private int currentPage;

    @Shadow
    private String title;

    private BookEditScreenMixin(Text title) {
        super(title);
    }

    @Shadow
    public abstract String getCurrentPageContent();

    @Shadow
    public abstract void setPageContent(String newContent);

    @Inject(method = "init()V", at = @At("HEAD"))
    public void init(CallbackInfo cb){
        wrapper = new BookEditScreenWrapper((BookEditScreen)(Object)this);
    }

    @Override
    public boolean getSigning(){return signing;}

    @Override
    public int getCurrentPage(){return currentPage;}

    @Override
    public String getTitleTexts(){return title;}

    @Override
    public void setTitle(String new_str){
        title=new_str;
    }

    @Override
    public String getCurrentPageContent_(){
        return getCurrentPageContent();
    }

    @Override
    public void setCurrentPageContent(String newContent){
        setPageContent(newContent);
    }

    @Override
    public TextRenderer getFont(){
        return this.font;
    }
}
