package io.github.lukeeff.gametools;

import io.github.lukeeff.gametools.gui.GuiHandler;
import io.github.lukeeff.gametools.gui.button.ButtonRegistry;
import io.github.lukeeff.gametools.gui.button.ToggledButtonHandler;
import io.github.lukeeff.gametools.key.KeyHandler;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = GameTools.MODID, version = GameTools.VERSION)
public class GameTools {

        @Getter private Minecraft minecraft;
        @Getter private ButtonRegistry buttonRegistry;
        @Getter private KeyHandler keyHandler;
        @Getter private ToggledButtonHandler toggledButtonHandler;
        @Getter private GuiHandler guiHandler;

        public static final String MODID = "gametools";
        public static final String VERSION = "1.0";

        @Mod.EventHandler
        public void init(FMLInitializationEvent event)
        {
            this.minecraft = Minecraft.getMinecraft();
            this.toggledButtonHandler = new ToggledButtonHandler(this);
            this.keyHandler = new KeyHandler(minecraft, this);
            this.buttonRegistry = new ButtonRegistry(this);
            this.guiHandler = new GuiHandler(this);
            NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
            MinecraftForge.EVENT_BUS.register(keyHandler);

        }

}
