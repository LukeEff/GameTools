package io.github.lukeeff.gametools.gui.button.buttons.gametools;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.buttons.ToggleableButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;

public class Sprint extends ToggleableButton {

    private static final String BUTTON_TEXT = "Auto Sprint";

    public Sprint(GameTools gameTools) {
        super(BUTTON_TEXT, gameTools);
    }

    @Override
    public void onTick(Minecraft mc) {
        EntityPlayerSP p = mc.thePlayer;
        if(isMoving(p)) {
            p.setSprinting(true);
        }
    }

    public static boolean isMoving(EntityPlayer p) {
        return p.moveForward > 0 && !p.isSneaking()
                && !p.isEating() & !p.isCollidedHorizontally
                && !p.isBlocking() && !p.isUsingItem();
    }

}
