package jp.axer.CocoaInput;

import com.sun.jna.Platform;
import jp.axer.CocoaInput.arch.darwin.DarwinController;
import jp.axer.CocoaInput.arch.dummy.DummyController;
import jp.axer.CocoaInput.plugin.CocoaInputController;
import jp.axer.CocoaInput.util.ModLogger;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class CocoaInput implements ClientModInitializer {
    private static CocoaInputController controller;
    private static MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient(){
        try{
            if(Platform.isMac()) {
                CocoaInput.applyController(new DarwinController());
            }
            else{
                CocoaInput.applyController(new DummyController());
            }
        }
        catch(IOException e) {
            ModLogger.error("Error occurred during creating controller.");
        }
        //MinecraftForge.EVENT_BUS.register(this);
        ModLogger.log("CocoaInput has been initialized.");
    }

    public static double getScreenScaledFactor() {
        return MinecraftClient.getInstance().options.guiScale;
    }

    public static void applyController(CocoaInputController controller) throws IOException {
        CocoaInput.controller = controller;
        ModLogger.log("CocoaInput is now using controller:" + controller.getClass().toString());
    }

    public static CocoaInputController getController() {
        return CocoaInput.controller;
    }

    public static void copyLibrary(String libraryName, String libraryPath) throws IOException {
        InputStream libFile;
        libFile = CocoaInput.class.getResourceAsStream("/"+libraryPath);
        File nativeDir = new File(client.runDirectory.getAbsolutePath().concat("/native"));
        File copyLibFile = new File(client.runDirectory.getAbsolutePath().concat("/native/" + libraryName));
        try {
            nativeDir.mkdir();
            FileOutputStream fos = new FileOutputStream(copyLibFile);
            copyLibFile.createNewFile();
            IOUtils.copy(libFile, fos);
            fos.close();
        } catch (IOException e1) {
            ModLogger.error("Attempted to copy library to ./native/" + libraryName + " but failed.");
            throw e1;
        }
        System.setProperty("jna.library.path", nativeDir.getAbsolutePath()); // set jna path
        ModLogger.log("CocoaInput has copied library to native directory.");
    }


}
