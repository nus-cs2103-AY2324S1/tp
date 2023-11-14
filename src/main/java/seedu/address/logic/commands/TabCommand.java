package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.tab.Tab.FINANCE_PARAMETER;

import seedu.address.commons.core.tab.Tab;
import seedu.address.model.Model;

/**
 * Changes tabs in the program.
 */
public class TabCommand extends Command {

    public static final String COMMAND_WORD = "tab";

    public static final String MESSAGE_USAGE = "tab: changes the tab to the specified tab "
            + "Example: " + COMMAND_WORD + " " + FINANCE_PARAMETER;


    private final Tab tab;

    /**
     * Creates a TabCommand to switch to specified {@code Tab}
     */
    public TabCommand(Tab tab) {
        requireNonNull(tab);
        this.tab = tab;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // Returns empty feedback to user as it will immediately switch to a different tab,
        // so the message won't be shown to the user regardless
        return new CommandResult("", false, tab.getZeroBasedTabIndex(), false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TabCommand)) {
            return false;
        }

        TabCommand otherTabCommand = (TabCommand) other;
        return tab.equals(otherTabCommand.tab);
    }
}
