package io.github.lukeeff.gametools.gui.button.buttons.gametools;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.buttons.GuiButtonWrapper;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.button.IButton;
import net.minecraft.client.Minecraft;

public class DebugButton extends GuiButtonWrapper {

    private static final String BUTTON_TEXT = "Debug";

    public DebugButton(GameTools gameTools){
        super(BUTTON_TEXT, gameTools);
    }

    @Override
    public void onPress(Minecraft mc, GuiScreenWrapper screen){
        mc.thePlayer.sendChatMessage("Hello! This is a debug message!");
    }

}
