package io.github.lukeeff.gametools.gui.screen;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import lombok.Getter;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class InputScreen extends GuiScreenWrapper {

    @Getter private InputHandler inputHandler;

    public InputScreen(GameTools gameTools) {
        super(gameTools, "input");
        setXButtonPosition(getCenterWidth());
        setYButtonPosition(getScreenHeight() / 4);
        setButtonPositions();
    }






    @Override
    public void initGui() {
        super.initGui();
        this.inputHandler = new InputHandler(this, GuiHandler.INPUT);
    }

    @Override
    protected void keyTyped(char character, int keyValue) throws IOException {
        GuiTextField textField = inputHandler.getFocusedTextField();
        if(textField != null) {
            if(inputHandler.isPrompt(textField)) {
                textField.setText("");
            }
            textField.textboxKeyTyped(character, keyValue);
            //if (!(keyValue == Keyboard.KEY_E)) super.keyTyped(character, keyValue);
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
