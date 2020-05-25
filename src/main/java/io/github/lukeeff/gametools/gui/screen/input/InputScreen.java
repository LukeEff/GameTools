package io.github.lukeeff.gametools.gui.screen.input;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.screen.input.textfield.GuiTextFieldWrapper;
import io.github.lukeeff.gametools.gui.screen.input.textfield.TextFieldHandler;
import lombok.Getter;

import java.awt.*;
import java.io.IOException;

/**
 * Input screen that is designed for creating executable key bindings.
 *
 * @author lukeff
 * @since 5/24/2020
 */
public class InputScreen extends GuiScreenWrapper {

    @Getter private TextFieldHandler fieldHandler;
    @Getter private static final String SCREEN_KEY = "input";
    @Getter private static final String keyBindName = "keybind";
    @Getter private static final String shortCutName = "shortcut";

    public InputScreen(GameTools gameTools) {
        super(gameTools, SCREEN_KEY);
        modifyButtonPositions();
    }

    /**
     * Modifies the positions of the buttons on the screen.
     */
    private void modifyButtonPositions() {
        setXButtonPosition(getCenterWidth());
        setYButtonPosition((int) (getScreenHeight() * .75));
        setButtonPositions();
    }

    /**
     * Called when Gui is initialized.
     */
    @Override
    public void initGui() {
        super.initGui();
        int id = gameTools.getGuiHandler().getGuiId(SCREEN_KEY);
        fieldHandler = new TextFieldHandler(this, id, keyBindName, shortCutName);
        fieldHandler.getFieldByKey(keyBindName).setSingleChar(true);
    }


    /**
     * Called when a key it typed.
     *
     * @param character character typed.
     * @param keyValue value of the key typed.
     */
    @Override
    protected void keyTyped(char character, int keyValue) {
        fieldHandler.handleInput(character, keyValue, 256, Color.GREEN.getRGB());
    }

    /**
     * Called when screen is to be updated.
     */
    @Override
    public void updateScreen() {
        super.updateScreen();
        final GuiTextFieldWrapper field = fieldHandler.getFocusedTextField();
        if(field != null) {
            field.updateCursorCounter();
        }
    }

    /**
     * Called when screen will be drawn.
     *
     * @param mouseX position for mouse in x axis.
     * @param mouseZ position for mouse in y axis.
     * @param partialTicks partial ticks.
     */
    @Override
    public void drawScreen(int mouseX, int mouseZ, float partialTicks) {
        this.drawDefaultBackground();
        this.fieldHandler.drawTextBoxes();
        super.drawScreen(mouseX, mouseZ, partialTicks);
    }

    /**
     * Called on mouse click.
     *
     * @param x x position of cursor.
     * @param y y position of cursor.
     * @param btn button id.
     */
    @Override
    protected void mouseClicked(int x, int y, int btn) {
        try {
            super.mouseClicked(x, y, btn);
            fieldHandler.mouseClicked(x, y, btn);
        } catch (IOException e) {
            mc.thePlayer.sendChatMessage("Error: Something went wrong.");
        }
    }

}
