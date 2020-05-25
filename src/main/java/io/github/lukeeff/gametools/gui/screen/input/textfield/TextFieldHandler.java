package io.github.lukeeff.gametools.gui.screen.input.textfield;

import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Handles most logic that needs to be processed when player inputs text into the
 * text field.
 *
 * @author lukeeff
 * @since 5/24/2020
 */
public class TextFieldHandler {

    private final List<GuiTextFieldWrapper> textFieldsHandle;
    private final GuiScreenWrapper screen;

    /**
     * Constructs keys.length amount of input boxes and keeps a reference to them in a global list.
     *
     * When no height or width get specified, they become a default set in this handler class.
     *
     * @param screen the screen the boxes will be in.
     * @param id the id of the screen.
     * @param keys the keys of the fields
     */
    public TextFieldHandler(GuiScreenWrapper screen, int id, String... keys) {
        this(screen, id, 137, 20, keys);
    }

    /**
     *
     * @param screen the screen the boxes will be in.
     * @param id the id of the screen.
     * @param keys the keys of the fields
     * @param width the width of the text fields.
     * @param height the height of the text fields.
     */
    public TextFieldHandler(GuiScreenWrapper screen, int id, int width, int height, String... keys) {
        TextFieldsRegistry textFieldsRegistry = new TextFieldsRegistry(screen, id, width, height, keys);
        this.textFieldsHandle = textFieldsRegistry.getTextFields();
        this.screen = screen;
    }

    /**
     * Gets the currently focused text field.
     *
     * @return the text field in focus.
     */
    public GuiTextFieldWrapper getFocusedTextField() {
        return textFieldsHandle.stream().filter(GuiTextField::isFocused).findAny().orElse(null);
    }

    /**
     * Prepares for input entry in text field box.
     *
     * @param field the text field to be inputted into.
     * @param maxLength max length for input.
     * @param color the color of the text.
     */
    private void prepareInputEntry(GuiTextFieldWrapper field, int maxLength, Integer color) {
        final int newColor = (color == null) ? field.getColor() : color;
        field.setColor(newColor);
        if(!field.isModified()) {
            field.setMaxLength(maxLength);
            field.clearField();
        }
    }

    /**
     * Handles some logic prior to preparing the input entry.
     *
     * @param character the character inputted by player.
     * @param keyValue the value of the key.
     * @param maxLength the max length for the input field.
     * @param color the color for the text.
     */
    private void handleInputEntry(char character, int keyValue, int maxLength, Integer color) {
        GuiTextFieldWrapper field = this.getFocusedTextField();
        if(field != null) {
            prepareInputEntry(field, maxLength, color);
            field.textboxKeyTyped(character, keyValue);
        }
    }

    /**
     * Called when key stroke is recognized. Processes and handles properties.
     *
     * @param character character inputted by player.
     * @param keyValue value of the key.
     * @param maxLength the max length for the input field.
     * @param color the color for the text.
     */
    public void handleInput(char character, int keyValue, int maxLength, Integer color) {
        if(pressedEscape(keyValue)) {
            screen.getGameTools().getGuiOpener().openHomeGui();
        } else {
            handleInputEntry(character, keyValue, maxLength, color);
        }
    }

    /**
     * Draws each text box.
     */
    public void drawTextBoxes() {
        textFieldsHandle.forEach(GuiTextField::drawTextBox);
    }

    /**
     * Calls the mouseClicked method on every text box.
     *
     * @param x mouse x position.
     * @param y mouse y position.
     * @param btn button.
     */
    public void mouseClicked(int x, int y, int btn) {
        textFieldsHandle.forEach(field -> field.mouseClicked(x, y, btn));
    }

    /**
     * Get a field by its key.
     *
     * @param key the key. Should be defined in the screens class.
     * @return the text field that corresponds to that key.
     */
    public GuiTextFieldWrapper getFieldByKey(String key) {
        final String prompt = GuiTextFieldWrapper.toPrompt(key);
        return textFieldsHandle.stream().filter(field -> field.getPrompt().equals(prompt)).findAny().orElse(null);
    }

    /**
     * Checks if the player typed the escape key.
     *
     * @param keyValue the value of the key pressed.
     * @return true if escape was pressed.
     */
    private boolean pressedEscape(int keyValue) {
        return keyValue == Keyboard.KEY_ESCAPE;
    }

}
