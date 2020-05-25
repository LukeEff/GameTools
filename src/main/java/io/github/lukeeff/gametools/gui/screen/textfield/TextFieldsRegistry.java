package io.github.lukeeff.gametools.gui.screen.textfield;

import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import lombok.Getter;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

public class TextFieldsRegistry {

    @Getter private final List<GuiTextFieldWrapper> textFields;
    private final GuiScreenWrapper screenWrapper;
    private final int width;
    private final int height;
    private final int id;

    /**
     * Makes simple text fields of a default size.
     *
     * @param screen the screen that will have the fields.
     * @param id the id of the screen.
     * @param types the types of inputs to expect from the user.
     */
    public TextFieldsRegistry(GuiScreenWrapper screen, int id, String... types) {
        this(screen, id, 137, 20, types);
    }

    /**
     * Makes simple texts fields.
     *
     * @param screen the screen that will have the fields.
     * @param id the id of the screen.
     * @param width the width of the text fields.
     * @param height the height of the text fields.
     * @param types the types of text fields.
     */
    public TextFieldsRegistry(GuiScreenWrapper screen, int id, int width, int height, String... types) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.screenWrapper = screen;
        this.textFields = new ArrayList<>();
        registerInputFields(types);
    }

    /**
     * Registers all input fields specified in constructor.
     *
     * @param types the types of inputs to expect from the user.
     */
    private void registerInputFields(String... types) {
        final FontRenderer fr = screenWrapper.getFontRendererObj();
        final int xPos = screenWrapper.getCenterWidth();
        int yPos = screenWrapper.getCenterHeight();
        for (final String text : types) {
            registerNewField(text, fr, xPos, yPos);
            yPos += 20;
        }
    }

    /**
     * Registers a new input field.
     *
     * @param text the type of input to expect from the user (keybind, shortcut, etc)
     * @param fr the font renderer.
     * @param xPos x position of the field.
     * @param yPos y position of the field.
     */
    private void registerNewField(String text, FontRenderer fr, int xPos, int yPos) {
        final GuiTextFieldWrapper textField = new GuiTextFieldWrapper(text, this.id, fr, xPos, yPos, this.width, this.height);
        this.textFields.add(textField);
    }


}
