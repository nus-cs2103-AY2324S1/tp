package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.GroupMeetingContainsKeywordPredicate;

/**
 * Finds and lists all persons in address book whose name contains the region to
 * be filtered.
 */
public class GroupMeetingCommand extends Command {

    public static final String COMMAND_WORD = "groupmeeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " north/south/east/west/central";

    public static final String MESSAGE_REGION_INVALID = "Region must be either north, south, east, west or central.";

    private final GroupMeetingContainsKeywordPredicate predicate;

    public GroupMeetingCommand(GroupMeetingContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupMeetingCommand)) {
            return false;
        }

        GroupMeetingCommand otherGroupMeetingCommand = (GroupMeetingCommand) other;
        return predicate.equals(otherGroupMeetingCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
