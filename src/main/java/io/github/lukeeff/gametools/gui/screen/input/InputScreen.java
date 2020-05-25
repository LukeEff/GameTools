package io.github.lukeeff.gametools.gui.screen.input;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.screen.textfield.TextFieldHandler;
import io.github.lukeeff.gametools.gui.screen.textfield.TextFieldsRegistry;
import lombok.Getter;
import net.minecraft.client.gui.GuiTextField;

import java.awt.*;
import java.io.IOException;

public class InputScreen extends GuiScreenWrapper {

    @Getter private InputHandler inputHandler;
    @Getter private TextFieldsRegistry textRegistry;
    @Getter private TextFieldHandler fieldHandler;
    @Getter private static final String SCREEN_KEY = "input";
    private static final String keyBindName = "keybind";
    private static final String shortCutName = "shortcut";
/*
    public InputScreen(GameTools gameTools) {
        super(gameTools, SCREEN_KEY);
        setXButtonPosition(getCenterWidth());
        setYButtonPosition((int) (getScreenHeight() * .75));
        setButtonPositions();
    }
*/
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
        //textRegistry = new TextFieldsRegistry(this, id, keyBindName, shortCutName);
        fieldHandler = new TextFieldHandler(this, id, keyBindName, shortCutName);
        //this.inputHandler = new InputHandler(this);
    }


    @Override
    protected void keyTyped(char character, int keyValue) {
        GuiTextField textField = inputHandler.getFocusedTextField();
        if(textField != null) {
            prepareInput(textField);
            textField.textboxKeyTyped(character, keyValue);
            textField.setTextColor(Color.GREEN.getRGB());
        }
    }

    private void prepareInput(GuiTextField textField) {
        if(inputHandler.isPrompt(textField)) {
            textField.setText("");
            if(textField == inputHandler.getKeyBindField()) {
                textField.setMaxStringLength(1);
            }
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if(inputHandler.getFocusedTextField() != null) {
            inputHandler.getFocusedTextField().updateCursorCounter();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseZ, float partialTicks) {
        this.drawDefaultBackground();
        inputHandler.getKeyBindField().drawTextBox();
        inputHandler.getTextField().drawTextBox();
        super.drawScreen(mouseX, mouseZ, partialTicks);
    }

    @Override
    protected void mouseClicked(int x, int y, int btn) {
        try {
            super.mouseClicked(x, y, btn);
            inputHandler.getKeyBindField().mouseClicked(x,y,btn);
            inputHandler.getTextField().mouseClicked(x,y,btn);
        } catch (IOException e) {
            mc.thePlayer.sendChatMessage("Error: Something went wrong.");
        }
    }

}
