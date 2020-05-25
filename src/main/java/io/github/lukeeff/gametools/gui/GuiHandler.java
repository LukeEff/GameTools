package io.github.lukeeff.gametools.gui;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.GuiScreenRegistry.GuiFactory;
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

    /**
     * Constructor for GuiHandler. Initiates the GuiScreen registration process and holds a reference
     * to the factories and ids of each GuiScreen object.
     *
     * @param gameTools instance of the main class.
     */
    public GuiHandler(GameTools gameTools) {
        final GuiScreenRegistry screenRegistry = new GuiScreenRegistry(gameTools);
        guiFactories = screenRegistry.getFactoryHandles();
        guiIdHandle = screenRegistry.getGuiIdHandles();
    }

    /**
     * Gets the id of a Gui object from its name.
     *
     * @param key the name of the gui.
     * @return the id of the gui.
     */
    public int getGuiId(String key) {
        return guiIdHandle.get(key);
    }


    /**
     * Gets the client Gui element object reference.
     *
     * @param id the id of the gui.
     * @param player the player.
     * @param world the world.
     * @param x x position of the player.
     * @param y y position of the player.
     * @param z z position of the player.
     *
     * @return a new instance of a gui screen object relative to the specified id.
     */
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if(guiFactories.size() >= id) {
            return guiFactories.get(id).init();
        }
        return null;
    }

    /**
     * Not applicable for this mod.
     */
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

}
