package io.github.lukeeff.gametools.gui.button.buttons;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.IButton;
import lombok.Getter;
import net.minecraft.client.gui.GuiButton;

public abstract class GuiButtonWrapper extends GuiButton implements IButton {

    @Getter protected final String buttonText;
    @Getter protected final GameTools gameTools;

    public GuiButtonWrapper(String buttonText, GameTools gameTools) {
        super(0, 0, 0, buttonText);
        this.buttonText = buttonText;
        this.gameTools = gameTools;
    }

    @Override
    public void setXPosition(int xPosition) {
        super.xPosition = xPosition;
    }

    @Override
    public void setYPosition(int yPosition) {
        super.yPosition = yPosition;
    }

    @Override
    public int getWidth() {
        return super.width;
    }

    @Override
    public int getHeight() {
        return super.height;
    }

    public void setId(int id) {
        super.id = id;
    }



}
