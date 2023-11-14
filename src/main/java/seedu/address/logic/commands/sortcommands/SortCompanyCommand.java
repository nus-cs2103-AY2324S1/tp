package seedu.address.logic.commands.sortcommands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.internship.InternshipInterviewDateTime;

/**
 * Sorts the company list based on the internship interview date.
 */
public class SortCompanyCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS = "Sorted company list based on closest internship date!";
    private final Optional<InternshipInterviewDateTime> startDateTime;
    private final Optional<InternshipInterviewDateTime> endDateTime;

    /**
     * Creates a SortCompanyCommand to sort the company list based on the internship interview date.
     */
    public SortCompanyCommand(Optional<InternshipInterviewDateTime> startDateTime,
                              Optional<InternshipInterviewDateTime> endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortCompanyList(startDateTime, endDateTime);
        return new RegularCommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public int hashCode() {
        return startDateTime.hashCode() + endDateTime.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SortCompanyCommand)) {
            return false;
        }
        SortCompanyCommand otherSortCompanyCommand = (SortCompanyCommand) other;
        return startDateTime.equals(otherSortCompanyCommand.startDateTime)
                && endDateTime.equals(otherSortCompanyCommand.endDateTime);
    }
}
