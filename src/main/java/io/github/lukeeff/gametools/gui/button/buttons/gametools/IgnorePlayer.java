package io.github.lukeeff.gametools.gui.button.buttons.gametools;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.buttons.GuiButtonWrapper;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class IgnorePlayer extends GuiButtonWrapper {

    private static final String BUTTON_TEXT = "Ignore Player";

    public IgnorePlayer(GameTools gameTools) {
        super(BUTTON_TEXT, gameTools);
    }

    @Override
    public void onPress(Minecraft mc, GuiScreenWrapper screen) {
        EntityPlayerSP p = mc.thePlayer;
    }
}
