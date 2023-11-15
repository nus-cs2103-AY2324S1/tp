package seedu.cc.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.cc.commons.core.index.Index;
import seedu.cc.commons.core.tabs.Tabs;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;

/**
 * Switches to the tab specified by the index number used in the navigating sidebar.
 */
public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";
    public static final String MESSAGE_SUCCESS = "Switched to ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the tab specified by the index number "
            + "used in the navigating sidebar.\n"
            + "Parameters: INDEX (must be a 1/2/3)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index tabIndex;

    /**
     * Creates an SwitchCommand to switch to the tab specified by the index number.
     */
    public SwitchCommand(Index tabIndex) {
        requireNonNull(tabIndex);
        this.tabIndex = tabIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setCurrentTab(this.tabIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SUCCESS + "%s tab", this.getTabName(this.tabIndex)));
    }

    private Tabs getTabName(Index tabIndex) {
        switch (tabIndex.getOneBased()) {
        case 1:
            return Tabs.PATIENTS;
        case 2:
            return Tabs.MEDICAL_HISTORY;
        case 3:
            return Tabs.APPOINTMENTS;
        default:
            return null;
        }
    }
}
