package io.github.lukeeff.gametools.key;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class KeyHandler {

    public static KeyBinding key;
    private Minecraft mc;
    private GameTools instance;
    @Getter private List<KeyBinding> keyBindings;

    public KeyHandler(Minecraft mc, GameTools instance) {
        this.mc = mc;
        this.instance = instance;
        this.keyBindings = new ArrayList<>();
        registerKey();
        ClientRegistry.registerKeyBinding(key);
    }

    private void registerKey() {
        key = new KeyBinding("Open Gui", Keyboard.KEY_V, "key.categories.hud");
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == Side.SERVER) {
            return;
        }
        if(event.phase == TickEvent.Phase.START) {
            EntityPlayerSP p = mc.thePlayer;
            if(key.isPressed()) {
                mc.thePlayer.openGui(instance, GuiHandler.GAME_TOOLS, mc.theWorld, (int) p.posX, (int) p.posY, (int) p.posZ);
            } else {
                handleShortCutKeys();
            }
        }
    }

    private void handleShortCutKeys() {
        for(KeyBinding binding : keyBindings) {
            if(binding.isPressed()) {
                mc.thePlayer.sendChatMessage(binding.getKeyDescription());
            }
        }

    }

    //@SubscribeEvent
    //public void playerTick(InputEvent.KeyInputEvent event) {
    //    System.out.println("Key event!");
    //    if(key.isPressed()) {
    //        mc.thePlayer.sendChatMessage("Hello!");
    //    }
    //}


}
