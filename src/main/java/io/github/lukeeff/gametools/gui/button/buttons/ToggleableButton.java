package io.github.lukeeff.gametools.gui.button.buttons;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.ButtonRegistry;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;

public abstract class ToggleableButton extends GuiButtonWrapper {

    @Getter @Setter public boolean toggled = false;

    protected ToggleableButton(String buttonText, GameTools gameTools) {
        super(buttonText, gameTools);
        ButtonRegistry.getTOGGLEABLE_BUTTONS().add(this);
    }

    public abstract void onTick(Minecraft mc);

    public void toggleButton() {
        if(isToggled()) {
            toggled = false;
            return;
        }
        toggled = true;
    }

    @Override
    public void onPress(Minecraft mc, GuiScreenWrapper screen) {
        toggleButton();
    }

}
