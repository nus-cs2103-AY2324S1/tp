package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Shows the dashboard containing statistics of the address book.
 */
public class DashboardCommand extends Command {

    public static final String COMMAND_WORD = "dashboard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the dashboard. ";

    public static final String SHOWING_DASHBOARD_MESSAGE = "Showing dashboard";

    /**
     * Opens the dashboard for viewing.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(SHOWING_DASHBOARD_MESSAGE, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof DashboardCommand; // instanceof handles nulls
    }
}
