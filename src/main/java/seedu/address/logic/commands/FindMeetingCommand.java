package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.meeting.GeneralMeetingPredicate;

/**
 * Finds and lists all meetings in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "findm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings whose titles contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103T Meeting";

    private final GeneralMeetingPredicate predicate;

    /**
     * Constructs a FindMeetingCommand object.
     * @param predicate The predicate that will be used by the FindMeetingCommand object.
     */
    public FindMeetingCommand(GeneralMeetingPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindMeetingCommand)) {
            return false;
        }

        FindMeetingCommand otherFindMeetingCommand = (FindMeetingCommand) other;
        return predicate.equals(otherFindMeetingCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
