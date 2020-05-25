package io.github.lukeeff.gametools.gui.screen;

import io.github.lukeeff.gametools.GameTools;
import net.minecraft.client.gui.GuiTextField;

import java.util.HashMap;
import java.util.Map;

public class SimpleTextFields {

    private final Map<String, GuiTextField> textFields;
    private final GuiScreenWrapper screenWrapper;
    private final int Id;

    protected SimpleTextFields(GameTools gameTools, GuiScreenWrapper screenWrapper, String screenKey, int id, String... text) {
        this.Id = id;
        this.screenWrapper = screenWrapper;
        this.textFields = new HashMap<String, GuiTextField>();
        registerInputFields(text);
    }

    private void registerInputFields(String... text) {
        //new GuiTextField(Id, screen.getFontRendererObj(), xPos, yPos, 137, 20);
        for(int i = 0; i < text.length; i++) {

        }

    }

}
