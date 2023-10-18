package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.meeting.AttendeeContainsKeywordsPredicate;
import seedu.address.model.meeting.LocationContainsKeywordsPredicate;
import seedu.address.model.meeting.MeetingTimeContainsPredicate;
import seedu.address.model.meeting.TitleContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterMeetingCommand extends Command {

    public static final String COMMAND_WORD = "filterm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings whose titles contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103T Meeting";

    private final TitleContainsKeywordsPredicate predicate;

    public FilterMeetingCommand(TitleContainsKeywordsPredicate predicate, LocationContainsKeywordsPredicate predicate1, MeetingTimeContainsPredicate predicate2, AttendeeContainsKeywordsPredicate predicate3, ) {
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
        if (!(other instanceof FilterMeetingCommand)) {
            return false;
        }

        FilterMeetingCommand otherFilterMeetingCommand = (FilterMeetingCommand) other;
        return predicate.equals(otherFilterMeetingCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
