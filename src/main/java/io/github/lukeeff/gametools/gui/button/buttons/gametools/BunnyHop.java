package io.github.lukeeff.gametools.gui.button.buttons.gametools;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.buttons.ToggleableButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class BunnyHop extends ToggleableButton {

    private static final String BUTTON_TEXT = "Bunny Hop";

    public BunnyHop(GameTools gameTools) {
        super(BUTTON_TEXT, gameTools);
    }

    @Override
    public void onTick(Minecraft mc) {
        EntityPlayerSP p = mc.thePlayer;
        if(Sprint.isMoving(p) && p.onGround) {
           p.setSprinting(true);
           p.jump();
           p.motionY = p.motionY / 2;
           p.motionX = p.motionX * 2;
           p.motionZ = p.motionZ * 2;
        }
    }

}
