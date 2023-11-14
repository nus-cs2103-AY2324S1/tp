package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

/**
 * Finds and lists all groups in StudentConnect whose group contains any of the argument keywords.
 */
public class FindGroupCommand extends Command {

    public static final String COMMAND_WORD = "findGroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all groups whose number contain any of "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 1 5 10";

    private final GroupContainsKeywordsPredicate predicate;

    public FindGroupCommand(GroupContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredGroupList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_GROUPS_LISTED_OVERVIEW, model.getFilteredGroupList().size()),
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindGroupCommand)) {
            return false;
        }

        FindGroupCommand otherFindGroupCommand = (FindGroupCommand) other;
        return predicate.equals(otherFindGroupCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
