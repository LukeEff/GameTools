package io.github.lukeeff.gametools.gui.screen.textfield;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

import java.awt.*;

public class GuiTextFieldWrapper extends GuiTextField {

    @Getter private final String prompt;
    @Getter @Setter private int maxLength;
    @Getter @Setter private int color;

    //public GuiTextFieldWrapper(int id, FontRenderer fontRenderer, int xPos, int yPos, int width, int length) {
    //    super(id, fontRenderer, xPos, yPos, width, length);
    //}

    public GuiTextFieldWrapper(String text, int id, FontRenderer fontRenderer, int xPos, int yPos, int width, int length) {
        super(id, fontRenderer, xPos, yPos, width, length);
        this.prompt = toPrompt(text);
        setFieldProperties();
    }

    /**
     * Turns the text key into a prompt that the user will see.
     *
     * @param text the text.
     * @return the text as a prompt (Enter something here...).
     */
    private String toPrompt(String text) {
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

    public void clearField() {
        super.setText("");
        super.setMaxStringLength(this.maxLength);
        super.setTextColor(this.color);
    }

}
