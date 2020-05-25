package io.github.lukeeff.gametools.gui.screen.gametools;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.ButtonRegistry;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import lombok.Getter;

public class GameToolsScreen extends GuiScreenWrapper {

    @Getter public static final String SCREEN_KEY = "gametools";

    public GameToolsScreen(GameTools gameTools) {
        super(gameTools, SCREEN_KEY);
    }

    /**
     * Draws the Gui home screen.
     *
     * @param mouseX mouse x
     * @param mouseY mouse y
     * @param partialTicks not sure.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
