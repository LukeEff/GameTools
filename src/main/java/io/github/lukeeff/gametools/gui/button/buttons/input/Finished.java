package io.github.lukeeff.gametools.gui.button.buttons.input;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.buttons.GuiButtonWrapper;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.screen.input.InputScreen;
import net.minecraft.client.Minecraft;
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
        registerShortcut((InputScreen) screen);
        gameTools.getGuiOpener().openHomeGui();
    }

    private void registerShortcut(InputScreen inputScreen) {
        final String input = getText(inputScreen, InputScreen.getShortCutName());
        final String bind = getText(inputScreen, InputScreen.getKeyBindName()).toUpperCase();
        final int keyBind = getKeyboardValue(bind);
        final KeyBinding keyBinding = new KeyBinding(input, keyBind, "Shortcuts");
        gameTools.getKeyHandler().getKeyBindings().add(keyBinding);
        ClientRegistry.registerKeyBinding(keyBinding);
    }

    private String getText(InputScreen screen, String key) {
        return screen.getFieldHandler().getFieldByKey(key).getText();
    }

    private int getKeyboardValue(String bind) {
        return getKeyValue(bind.charAt(0));
    }

    private int getKeyValue(char val) {
        String str = Character.toString(val);
        return Keyboard.getKeyIndex(str);
    }


}
