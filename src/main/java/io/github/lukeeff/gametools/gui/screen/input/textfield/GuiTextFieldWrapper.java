package io.github.lukeeff.gametools.gui.screen.input.textfield;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

import java.awt.*;

/**
 * Wrapper for GuiTextField. Allows for a prompt to be set that will erase once input is typed, single character
 * input, etc.
 *
 * @author lukeeff
 * @since 5/24/2020
 */
public class GuiTextFieldWrapper extends GuiTextField {

    @Getter private final String prompt;
    @Getter @Setter private int maxLength;
    @Getter @Setter private int color;
    @Getter @Setter private boolean singleChar;

    /**
     * Text field wrapper constructor. Just wraps around the gui text field and allows for some custom
     * variables and what not to make life a bit easier.
     *
     * @param key the key used to identify the field.
     * @param id the id of the field.
     * @param fontRenderer the font renderer of the screen.
     * @param xPos xPos of the field.
     * @param yPos yPos of the field.
     * @param width width of the field.
     * @param height height of the field.
     */
    public GuiTextFieldWrapper(String key, int id, FontRenderer fontRenderer, int xPos, int yPos, int width, int height) {
        super(id, fontRenderer, xPos, yPos, width, height);
        this.prompt = toPrompt(key);
        this.singleChar = false;
        setFieldProperties();
    }

    /**
     * Turns the text key into a prompt that the user will see.
     *
     * @param text the text.
     * @return the text as a prompt (Enter something here...).
     */
    public static String toPrompt(String text) {
        return "Enter " + text + " here...";
    }

    private void setFieldProperties() {
        this.color = Color.WHITE.getRGB();
        this.maxLength = this.prompt.length();
        super.setMaxStringLength(this.maxLength);
        super.setText(this.prompt);
    }

    /**
     * Checks if the fields text box is the same as before.
     *
     * @return true if it has changed.
     */
    public boolean isModified() {
        return !this.prompt.equals(super.getText());
    }

    /**
     * Clears the text field and sets the user input length.
     */
    public void clearField() {
        final int maxLength = singleChar ? 1 : this.maxLength;
        super.setText("");
        super.setMaxStringLength(maxLength);
        super.setTextColor(this.color);
    }

}
