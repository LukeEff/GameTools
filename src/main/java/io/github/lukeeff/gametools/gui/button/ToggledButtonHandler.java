package io.github.lukeeff.gametools.gui.button;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.buttons.ToggleableButton;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/**
 * Handles toggled buttons on each tick. When a button is toggled, it will call its effect.
 *
 * @author lukeeff
 * @since 5/24/2020
 */
public class ToggledButtonHandler {

    private final Minecraft mc;
    private final List<ToggleableButton> toggleableButtonList;


    public ToggledButtonHandler(GameTools gameTools) {
        this.mc = gameTools.getMinecraft();
        this.toggleableButtonList = ButtonRegistry.getTOGGLEABLE_BUTTONS();
    }

    /**
     * Called every tick. If a button is toggled then it will do what the button does each tick.
     *
     * @param p the player.
     */
    public void onTick(EntityPlayer p) {
        for (ToggleableButton button: toggleableButtonList) {
            if(button.isToggled()) {
                button.onTick(mc);
            }
        }
    }

}
