package io.github.lukeeff.gametools.gui.screen;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.ButtonRegistry;
import lombok.Getter;

public class GameToolsScreen extends GuiScreenWrapper {

    public GameToolsScreen(GameTools gameTools) {
        super(gameTools, "gametools");
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
