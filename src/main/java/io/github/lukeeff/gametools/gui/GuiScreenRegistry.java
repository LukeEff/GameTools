package io.github.lukeeff.gametools.gui;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.screen.GuiScreenWrapper;
import io.github.lukeeff.gametools.gui.screen.gametools.GameToolsScreen;
import io.github.lukeeff.gametools.gui.screen.input.InputScreen;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsible for registering guis across the program.
 *
 * @author lukeeff
 * @since 5/24/2020
 */
public class GuiScreenRegistry {

    @Getter private final Map<String, Integer> guiIdHandles;
    @Getter private final List<GuiFactory> factoryHandles;
    @Getter private final GameTools gameTools;

    /**
     * Simple Gui Factory created with the intentions of avoiding code duplication.
     *
     * Simply implement the method inside of the fillScreenList method and it will be good to go!
     */
    interface GuiFactory {
        GuiScreenWrapper init();
    }

    /**
     * Constructor for ScreenRegistry. Responsible for registering gui user interfaces.
     *
     * @param gameTools the main instance.
     */
    public GuiScreenRegistry(@NonNull final GameTools gameTools) {
        this.guiIdHandles = new HashMap<>();
        this.factoryHandles = new ArrayList<>();
        this.gameTools = gameTools;
        initGuiRegistration();
    }

    /**
     * handles all registration mapping.
     *
     * @param guiFactories the main instance.
     */
    private void handleRegistrationMapping(GuiFactory... guiFactories) {
        for(int i = 0; i < guiFactories.length; i++) {
            final GuiFactory factory = guiFactories[i];
            final String key = factory.init().getScreenKey();
            registerFactory(factory);
            registerId(key, i);
        }
    }

    /**
     * Registers a key and id into the list for future reference.
     *
     * @param key the key of the gui.
     * @param id the id of the gui.
     */
    private void registerId(String key, int id) {
        guiIdHandles.put(key, id);
    }

    /**
     * Registers a factory.
     *
     * @param factory the factory to be registered.
     */
    private void registerFactory(GuiFactory factory) {
        factoryHandles.add(factory);
    }

    /**
     * Initializes the Gui registration process.
     *
     * When adding new screens, they only need to be added here.
     */
    private void initGuiRegistration() {
        handleRegistrationMapping(
                () -> new GameToolsScreen(gameTools),
                () -> new InputScreen(gameTools)
        );
    }

}
