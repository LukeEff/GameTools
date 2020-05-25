package io.github.lukeeff.gametools.gui.screen.input;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.screen.textfield.GuiTextFieldWrapper;
import io.github.lukeeff.gametools.gui.screen.textfield.TextFieldHandler;
import io.github.lukeeff.gametools.gui.screen.textfield.TextFieldsRegistry;
import lombok.Getter;
import net.minecraft.client.gui.GuiTextField;

import java.awt.*;
import java.io.IOException;

public class InputScreen extends GuiScreenWrapper {

    @Getter private TextFieldHandler fieldHandler;
    @Getter private static final String SCREEN_KEY = "input";
    @Getter private static final String keyBindName = "keybind";
    @Getter private static final String shortCutName = "shortcut";

    public InputScreen(GameTools gameTools) {
        super(gameTools, SCREEN_KEY);
        modifyButtonPositions();
    }

    private void modifyButtonPositions() {
        setXButtonPosition(getCenterWidth());
        setYButtonPosition((int) (getScreenHeight() * .75));
        setButtonPositions();
    }

    @Override
    public void initGui() {
        super.initGui();
        int id = gameTools.getGuiHandler().getGuiId(SCREEN_KEY);
        fieldHandler = new TextFieldHandler(this, id, keyBindName, shortCutName);
        fieldHandler.getFieldByKey(keyBindName).setSingleChar(true);
    }


    @Override
    protected void keyTyped(char character, int keyValue) {
        fieldHandler.handleInput(character, keyValue, 256, Color.GREEN.getRGB());
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        final GuiTextFieldWrapper field = fieldHandler.getFocusedTextField();
        if(field != null) {
            field.updateCursorCounter();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseZ, float partialTicks) {
        this.drawDefaultBackground();
        this.fieldHandler.drawTextBoxes();
        super.drawScreen(mouseX, mouseZ, partialTicks);
    }

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
