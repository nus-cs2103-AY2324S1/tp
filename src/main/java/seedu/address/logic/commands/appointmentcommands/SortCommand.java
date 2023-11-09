package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;


/**
 * Sorts all appointments by the attribute provided.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the appointment list by the corresponding parameters.\n"
            + "The direction should either be asc or desc. The parameter must be one of [time, priority].\n"
            + "Parameters: DIRECTION "
            + "by=ATTRIBUTE\n"
            + "Example: " + COMMAND_WORD + " asc by=priority";

    public static final String MESSAGE_SUCCESS = "Sorted all appointments in %1$s order by %2$s.";

    private final String attribute;
    private final boolean isAscending;

    /**
     * Creates a SortCommand to sort by type
     * @param isAscending whether the sort type is ascending
     * @param attribute attribute to sort by
     */
    public SortCommand(boolean isAscending, String attribute) {
        requireNonNull(attribute);
        this.isAscending = isAscending;
        this.attribute = attribute;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortAppointmentList(isAscending, attribute);
        return new CommandResult(String.format(MESSAGE_SUCCESS, order(isAscending), attribute),
                false, false, false, true);
    }

    private static String order(boolean isAscending) {
        if (isAscending) {
            return "ascending";
        } else {
            return "descending";
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand e = (SortCommand) other;
        return attribute.equals(e.attribute) && isAscending == e.isAscending;
    }
}
