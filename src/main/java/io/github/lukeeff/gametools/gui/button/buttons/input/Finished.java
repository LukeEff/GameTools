package io.github.lukeeff.gametools.gui.button.buttons.input;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.button.buttons.GuiButtonWrapper;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.screen.gametools.GameToolsScreen;
import io.github.lukeeff.gametools.gui.screen.input.InputScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Finished extends GuiButtonWrapper {

    private static final String BUTTON_TEXT = "Apply Keybind";

    public Finished(GameTools gameTools) {
        super(BUTTON_TEXT, gameTools);
    }

    @Override
    public void onPress(Minecraft mc, GuiScreenWrapper screen) {
        InputScreen inputScreen = (InputScreen) screen;
        registerShortCut(inputScreen);
        EntityPlayerSP p = mc.thePlayer;
        int id = gameTools.getGuiHandler().getGuiId(GameToolsScreen.getSCREEN_KEY());

        p.openGui(gameTools, id, mc.theWorld, (int) p.posX, (int) p.posY, (int) p.posZ);
    }




























   private void registerShortCut(InputScreen inputScreen) {
       final String text = inputScreen.getInputHandler().getTextField().getText();
       final String bind = inputScreen.getInputHandler().getKeyBindField().getText();
       System.out.println("Text: " + text + " Keybind: " + bind);
       final int val = Keyboard.getKeyIndex(bind.toUpperCase());
       final KeyBinding keyBinding = new KeyBinding(text, val, "key.categories.shortcut");
       getGameTools().getKeyHandler().getKeyBindings().add(keyBinding);
       ClientRegistry.registerKeyBinding(keyBinding);
   }

}
