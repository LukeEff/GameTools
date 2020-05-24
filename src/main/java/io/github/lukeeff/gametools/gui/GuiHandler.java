package io.github.lukeeff.gametools.gui;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.screen.GameToolsScreen;
import io.github.lukeeff.gametools.gui.screen.InputScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int GAME_TOOLS = 0;
    public static final int INPUT = 1;
    private final GameTools gameTools;

    public GuiHandler(GameTools instance) {
        gameTools = instance;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GAME_TOOLS) {
            return new GameToolsScreen(gameTools);
        } else if (ID == INPUT) {
            return new InputScreen(gameTools);
        }
        return null;
    }
}
