package io.github.lukeeff.gametools.key;

import com.sun.media.jfxmedia.logging.Logger;
import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.button.ToggledButtonHandler;
import io.github.lukeeff.gametools.gui.button.buttons.ToggleableButton;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.screen.gametools.GameToolsScreen;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KeyHandler {

    public static KeyBinding key;
    private Minecraft mc;
    private GameTools gameTools;
    @Getter private final List<KeyBinding> keyBindings;
    private final ToggledButtonHandler toggledButtonHandler;

    public KeyHandler(Minecraft mc, GameTools gameTools) {
        this.mc = mc;
        this.gameTools = gameTools;
        this.keyBindings = new ArrayList<>();
        registerKey();
        ClientRegistry.registerKeyBinding(key);
        toggledButtonHandler = gameTools.getToggledButtonHandler();
    }

    private void registerKey() {
        key = new KeyBinding("Open Gui", Keyboard.KEY_V, "key.categories.hud");
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {
            EntityPlayerSP p = mc.thePlayer;
            if (key.isPressed()) {
               // p.openGui(gameTools, GuiHandler.GAME_TOOLS, mc.theWorld, (int) p.posX, (int) p.posY, (int) p.posZ);
                p.openGui(gameTools, /*GuiHandler.getScreenId(GameToolsScreen.getSCREEN_KEY())*/ 0, mc.theWorld, (int) p.posX, (int) p.posY, (int) p.posZ); //debug
            } else {
                handleShortCutKeys();
            }
            toggledButtonHandler.onTick(p);
        }
    }

    private void handleShortCutKeys() {
        for(KeyBinding binding : keyBindings) {
            if(binding.isPressed()) {
                mc.thePlayer.sendChatMessage(binding.getKeyDescription());
            }
        }
    }

    /*
    private void updatePlayer(EntityPlayerSP p) {
        List<GuiButton> buttonList = gameTools.getButtonRegistry().getButtons().get("gametools");
        List<ToggleableButton> toggleableButtons;
        toggleableButtons = buttonList.stream().filter(b -> (b instanceof ToggleableButton)).map(b -> (ToggleableButton) b).collect(Collectors.toList());

        for (ToggleableButton button: toggleableButtons) {
            if(button.isToggled()) {
                button.onTick(mc);
            }
        }
    }

     */



}
