package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Dashboard;
import seedu.address.model.Model;

/**
 * Shows the dashboard containing statistics of the address book.
 */
public class DashboardCommand extends Command {

    public static final String COMMAND_WORD = "dashboard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the dashboard. ";

    public static final String MESSAGE_SUCCESS = "DASHBOARD";

    /**
     * Opens the dashboard for viewing.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Dashboard dashboard = model.getDashboard();
        dashboard.openDashboard();

        int interactionCount = dashboard.getTotalInteraction();

        double interestedPercentage = dashboard.interestedPercentage();
        double notInterestedPercentage = dashboard.notInterestedPercentage();

        // Might want to refactor this for flexibility. Left as is for now.
        String message = "Total number of interactions: "
                + interactionCount + "\n"
                + "Percentage of interested interactions: "
                + String.format("%.2f", interestedPercentage) + "%\n"
                + "Percentage of not interested interactions: "
                + String.format("%.2f", notInterestedPercentage) + "%\n";

        return new CommandResult(MESSAGE_SUCCESS + "\n" + message);
    }
}
