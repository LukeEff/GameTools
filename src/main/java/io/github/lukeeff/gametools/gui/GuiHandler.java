package io.github.lukeeff.gametools.gui;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.ScreenRegistry.GuiFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.util.List;
import java.util.Map;

/**
 * Responsible for handling Gui data retrieval and holding references to them.
 *
 * @author lukeeff
 * @since 5/24/2020
 */
public class GuiHandler implements IGuiHandler {

    private final Map<String, Integer> guiIdHandle;
    private final List<GuiFactory> guiFactories;

    public GuiHandler(GameTools gameTools) {
        final ScreenRegistry screenRegistry = new ScreenRegistry(gameTools);
        guiFactories = screenRegistry.getFactoryHandle();
        guiIdHandle = screenRegistry.getGuiIdHandle();
    }

    /**
     * Gets the id of a Gui object from its name.
     *
     * @param key the name of the gui.
     * @return the id of the gui.
     */
    public Integer getGuiId(String key) {
        return guiIdHandle.get(key);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(guiFactories.size() >= ID) {
            return guiFactories.get(ID).init();
        }
        return null;
    }



}
