package io.github.lukeeff.gametools.gui.screen.textfield;

import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
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
        for(GuiTextFieldWrapper field : textFieldsHandle) {
            if(field.isFocused()) {
                return field;
            }
        }
        return null;
    }

    private void prepareInputEntry(GuiTextFieldWrapper field, int maxLength, @Nullable Integer color) {
        final int newColor = (color == null) ? field.getColor() : color;
        if(!field.isModified()) {
            field.setMaxLength(maxLength);
            field.clearField();
        }
        field.setColor(newColor);
    }

    private void handleInputEntry(char character, int keyValue, int maxLength, Integer color) {
        GuiTextFieldWrapper field = this.getFocusedTextField();
        if(field != null) {
            prepareInputEntry(field, maxLength, color);
            field.textboxKeyTyped(character, keyValue);
        }
    }

    public void handleInput(char character, int keyValue, int maxLength, Integer color) {
        final int esc = Keyboard.KEY_ESCAPE;
        if(keyValue == esc) {
            screen.getGameTools().getGuiOpener().openHomeGui();
        } else {
            handleInputEntry(character, keyValue, maxLength, color);
        }
    }
}
