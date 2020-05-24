package io.github.lukeeff.gametools.gui.button;

import io.github.lukeeff.gametools.GameTools;
import io.github.lukeeff.gametools.gui.button.buttons.DebugButton;
import io.github.lukeeff.gametools.gui.button.buttons.InputButton;
import io.github.lukeeff.gametools.gui.button.buttons.input.Finished;
import lombok.Getter;
import net.minecraft.client.gui.GuiButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static scala.actors.threadpool.Arrays.*;

public class ButtonRegistry {

    private final GameTools gameTools;
    @Getter private final Map<String, List<GuiButton>> buttons;

    public ButtonRegistry(GameTools gameTools) {
        this.gameTools = gameTools;
        this.buttons = new HashMap<>();
        registerGameToolsButtons();
        registerInputScreenButtons();
    }

    /**
     * Registers buttons on the game tools screen.
     */
    private void registerGameToolsButtons() {
        addButton("gametools",
                new InputButton(gameTools),
                new DebugButton(gameTools));
    }

    /**
     * Adds buttons into the registered buttons map.
     *
     * @param section the name of the section (screen) the buttons are registered to.
     * @param sectionButtons the buttons in that section.
     */
    @SuppressWarnings("unchecked")
    private void addButton(String section, GuiButton... sectionButtons) {
        this.buttons.put(section, asList(sectionButtons));
    }

    /**
     * Registers buttons on the input screen.
     */
    private void registerInputScreenButtons() {
        addButton("input",
                new Finished(gameTools));
    }

}
