package io.github.lukeeff.gametools.gui.screen.textfield;

import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

public class TextFieldHandler {

    private final List<GuiTextFieldWrapper> textFieldsHandle;
    private final GuiScreenWrapper screen;

    public TextFieldHandler(GuiScreenWrapper screen, int id, String... types) {
        this(screen, id, 137, 20, types);
    }

    public TextFieldHandler(GuiScreenWrapper screen, int id, int width, int height, String... types) {
        TextFieldsRegistry textFieldsRegistry = new TextFieldsRegistry(screen, id, width, height, types);
        this.textFieldsHandle = textFieldsRegistry.getTextFields();
        this.screen = screen;
    }

    public GuiTextFieldWrapper getFocusedTextField() {
        return textFieldsHandle.stream().filter(GuiTextField::isFocused).findAny().orElse(null);
    }

    private void prepareInputEntry(GuiTextFieldWrapper field, int maxLength, Integer color) {
        final int newColor = (color == null) ? field.getColor() : color;
        field.setColor(newColor);
        if(!field.isModified()) {
            field.setMaxLength(maxLength);
            field.clearField();
        }
    }

    private void handleInputEntry(char character, int keyValue, int maxLength, Integer color) {
        GuiTextFieldWrapper field = this.getFocusedTextField();
        if(field != null) {
            prepareInputEntry(field, maxLength, color);
            field.textboxKeyTyped(character, keyValue);
        }
    }

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

    private void handleSingleCharField() {

    }

}
