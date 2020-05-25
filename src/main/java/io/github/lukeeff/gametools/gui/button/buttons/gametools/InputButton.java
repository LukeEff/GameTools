package io.github.lukeeff.gametools.gui.button.buttons.gametools;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.button.buttons.GuiButtonWrapper;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.screen.input.InputScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class InputButton extends GuiButtonWrapper {

    private static final String BUTTON_TEXT = "Input Shortcut";

    public InputButton(GameTools gameTools) {
        super(BUTTON_TEXT, gameTools);
    }

    @Override
    public void onPress(Minecraft mc, GuiScreenWrapper screen) {
        gameTools.getGuiOpener().openInputGui();
    }



}
