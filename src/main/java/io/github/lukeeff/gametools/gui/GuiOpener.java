package io.github.lukeeff.gametools.gui;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.screen.gametools.GameToolsScreen;
import io.github.lukeeff.gametools.gui.screen.input.InputScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class GuiOpener {

    private final GameTools gameTools;
    private final GuiHandler guiHandler;
    private final Minecraft mc;
    private EntityPlayer p;

    public GuiOpener(GameTools gameTools) {
        this.gameTools = gameTools;
        this.guiHandler = gameTools.getGuiHandler();
        this.mc = gameTools.getMinecraft();
    }

    private void openGui(int id) {
        p = mc.thePlayer;
        final int posX = (int) p.posX;
        final int posY = (int) p.posY;
        final int posZ = (int) p.posZ;
        p.openGui(gameTools, id, mc.theWorld, posX, posY, posZ);
    }

    public void openHomeGui() {
        openGui(getGuiId(GameToolsScreen.getSCREEN_KEY()));
    }

    public void openInputGui() {
        openGui(getGuiId(InputScreen.getSCREEN_KEY()));
    }

    public int getGuiId(String key) {
        return guiHandler.getGuiId(key);
    }

}
