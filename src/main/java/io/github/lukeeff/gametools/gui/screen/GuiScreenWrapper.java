package io.github.lukeeff.gametools.gui.screen;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.button.ButtonRegistry;
import io.github.lukeeff.gametools.gui.button.IButton;
import io.github.lukeeff.gametools.gui.button.buttons.GuiButtonWrapper;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public abstract class GuiScreenWrapper extends GuiScreen {

    @Getter protected final GameTools gameTools;
    @Getter protected final ButtonRegistry buttonRegistry;
    @Getter protected final String screenKey;
    @Getter protected final List<GuiButton> guiButtonList;
    @Getter @Setter protected int xButtonPosition = 0;
    @Getter @Setter protected int yButtonPosition = 0;

    protected GuiScreenWrapper(GameTools gameTools, String screenKey) {
        this(gameTools, screenKey, 0, 0);
    }

    protected GuiScreenWrapper(GameTools gameTools, String screenKey, int offSetX, int offSetY) {
        this.screenKey = screenKey;
        this.gameTools = gameTools;
        this.buttonRegistry = gameTools.getButtonRegistry();
        this.guiButtonList = buttonRegistry.getButtons().get(screenKey);
        this.xButtonPosition = offSetX;
        this.yButtonPosition = offSetY;
    }

    /**
     * Sets the positions of the buttons on the screen.
     */
    protected void setButtonPositions() {
        for(int i = 0; i < guiButtonList.size(); i++) {
            final GuiButtonWrapper button = (GuiButtonWrapper) guiButtonList.get(i);
            button.setId(i);
            button.setXPosition(this.xButtonPosition);
            button.setYPosition(this.yButtonPosition);
            this.yButtonPosition += button.getHeight() + 5;
        }
    }

    /**
     * Gets the width of the screen.
     *
     * @return the width of the screen.
     */
    public int getScreenWidth() {
        return this.width;
    }

    /**
     * Gets the height of the screen.
     *
     * @return the height of the screen.
     */
    public int getScreenHeight() {
        return this.height;
    }

    public int getCenterWidth() {
        return getScreenWidth() / 2;
    }

    public int getCenterHeight() {
        return getScreenHeight() / 2;
    }

    /**
     * Called when the Gui is initialized.
     */
    @Override
    public void initGui() {
        if(guiButtonList != null) {
            setButtonPositions();
            this.buttonList.addAll(buttonRegistry.getButtons().get(screenKey));
        }
    }

    /**
     * Called when a button gets pressed.
     *
     * @param button the button that was pressed.
     */
    @Override
    protected void actionPerformed(GuiButton button) {
        ((IButton) button).onPress(mc, this);
    }

    public FontRenderer getFontRendererObj() {
        return super.fontRendererObj;
    }

}
