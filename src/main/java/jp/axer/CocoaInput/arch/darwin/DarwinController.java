package jp.axer.CocoaInput.arch.darwin;

import jp.axer.CocoaInput.CocoaInput;
import jp.axer.CocoaInput.plugin.CocoaInputController;
import jp.axer.CocoaInput.plugin.IMEOperator;
import jp.axer.CocoaInput.plugin.IMEReceiver;
import jp.axer.CocoaInput.util.ModLogger;

import java.io.IOException;

public class DarwinController implements CocoaInputController {
    public DarwinController() throws IOException {
        CocoaInput.copyLibrary("libcocoainput.dylib", "assets/darwin/libcocoainput.dylib");
        Handle.INSTANCE.initialize(CallbackFunction.Func_log, CallbackFunction.Func_error, CallbackFunction.Func_debug);
        ModLogger.log("DarwinController has been initialized.");
    }

    @Override
    public IMEOperator generateIMEOperator(IMEReceiver ime) {
        return new DarwinIMEOperator(ime);
    }

    // TODO: find alternative implementation for this forge event listener
    /*@SubscribeEvent
    public void didChangeGui(GuiOpenEvent event) {
        if (!(event.getGui() instanceof IMEReceiver)) {
            Handle.INSTANCE.refreshInstance();//GUIの切り替えでIMの使用をoffにする
        }
    }*/
}
