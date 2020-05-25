package io.github.lukeeff.gametools.gui.screen.input;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import lombok.Getter;
import net.minecraft.client.gui.GuiTextField;

import java.awt.*;
import java.io.IOException;

public class InputScreen extends GuiScreenWrapper {

    @Getter private InputHandler inputHandler;
    @Getter private static final String SCREEN_KEY = "input";

    public InputScreen(GameTools gameTools) {
        super(gameTools, SCREEN_KEY);
        setXButtonPosition(getCenterWidth());
        setYButtonPosition(getScreenHeight() / 4);
        setButtonPositions();
    }

    @Override
    public void initGui() {
        super.initGui();
        //this.inputHandler = new InputHandler(this, GuiHandler.INPUT);
        this.inputHandler = new InputHandler(this);
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
