package io.github.lukeeff.gametools.gui.button.buttons;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class InputButton extends GuiButtonWrapper {

    private static final String BUTTON_TEXT = "Input Shortcut";

    public InputButton(GameTools gameTools) {
        super(BUTTON_TEXT, gameTools);
    }

    @Override
    public void onPress(Minecraft mc, GuiScreenWrapper screen) {
        EntityPlayerSP p = mc.thePlayer;
        p.openGui(gameTools, GuiHandler.INPUT, mc.theWorld, (int) p.posX, (int) p.posY, (int) p.posZ);
    }



}