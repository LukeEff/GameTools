package io.github.lukeeff.gametools.gui.screen;

import lombok.Getter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class InputHandler {

    @Getter public static final String INPUT_PROMPT = "Enter shortcut here...";
    @Getter public static final String BIND_PROMPT = "Enter keybind here...";
    @Getter private GuiTextField textField;
    @Getter private GuiTextField keyBindField;
    @Getter private final GuiScreenWrapper screen;
    @Getter private final int Id;

    public InputHandler(GuiScreenWrapper screen, int Id) {
        this.screen = screen;
        this.Id = Id;
        setTextField();
        setKeyBindField();
    }

    private void setInputField(GuiTextField field, int maxLength, String text) {
        field.setText(text);
        field.setMaxStringLength(maxLength);
        field.setFocused(true);

    }

    private void setTextField() {
        final int xPos = screen.getCenterWidth() - 68;
        final int yPos = screen.getCenterHeight() - 46;
        this.textField = new GuiTextField(Id, screen.getFontRendererObj(), xPos, yPos, 137, 20);
        setInputField(textField, 256, INPUT_PROMPT);
    }

    private void setKeyBindField() {
        final int xPos = screen.getCenterWidth() - 68;
        final int yPos = screen.getCenterHeight() - 66;
        this.keyBindField = new GuiTextField(Id, screen.getFontRendererObj(), xPos, yPos, 137, 20);
        setInputField(keyBindField, 30, BIND_PROMPT);
    }

    public GuiTextField getFocusedTextField() {
        if(textField.isFocused()) {
            return textField;
        } else if (keyBindField.isFocused()) {
            return keyBindField;
        }
        return null;
    }

    public boolean isPrompt(GuiTextField field) {
        return field.getText().equals(INPUT_PROMPT) || field.getText().equals(BIND_PROMPT);
    }

}
