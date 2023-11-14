package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.UiModeManager;


/**
 * Toggles the UI between light mode and dark mode.
 *
 */
public class ToggleModeCommand extends Command {

    public static final String COMMAND_WORD = "toggleMode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Toggles between LightMode and DarkMode";

    public static final String TOGGLE_SUCCESS = "Changed Mode, the next time InsuraHub is opened it will be in ";
    private final UiModeManager uiModeManager;
    private final String newMode;

    /**
     * Creates a ToggleModeCommand to toggle the {@code UiMode}
     */
    public ToggleModeCommand() {
        this.uiModeManager = new UiModeManager();
        if (uiModeManager.getUiMode().equals("LightWindow.fxml")) {
            this.newMode = "DarkMode";
        } else {
            this.newMode = "LightMode";
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String uiMode = uiModeManager.getUiMode();
        if (uiMode.equals("MainWindow.fxml")) {
            uiModeManager.setUiMode("LightWindow.fxml");
        } else {
            uiModeManager.setUiMode("MainWindow.fxml");
        }
        return new CommandResult(TOGGLE_SUCCESS + newMode);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
