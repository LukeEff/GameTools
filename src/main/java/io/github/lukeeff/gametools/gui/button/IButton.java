package io.github.lukeeff.gametools.gui.button;

import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import net.minecraft.client.Minecraft;

public interface IButton {

    /**
     * Called when button gets pressed.
     *
     * @param mc instance of Minecraft.
     * @param screen the screen that it was pressed on.
     */
    void onPress(Minecraft mc, GuiScreenWrapper screen);

    /**
     * Gets the width of the button.
     *
     * @return the width of the button.
     */
    int getWidth();

    /**
     * Gets the height of the button.
     *
     * @return the height of the button.
     */
    int getHeight();

    /**
     * Gets the text that is displayed on the button.
     *
     * @return the text that is displayed on the button.
     */
    String getButtonText();

    /**
     * Sets the x position of the button on the screen.
     *
     * @param xPosition the x position the button is at on the screen.
     */
    void setXPosition(int xPosition);

    /**
     * Sets the y position of the button on the screen.
     *
     * @param yPosition the y position the button is at on the screen.
     */
    void setYPosition(int yPosition);
}
