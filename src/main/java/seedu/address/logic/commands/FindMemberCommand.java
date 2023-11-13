package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.MemberContainsKeywordsPredicate;


/**
 * Finds and lists all members in address book whose fields contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindMemberCommand extends Command {

    public static final String COMMAND_WORD = "findmember";
    public static final String COMMAND_ALIAS = "findm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Finds all members whose information contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD...\n"
            + "Example: " + COMMAND_WORD + " alice 91119111 design";

    private final MemberContainsKeywordsPredicate predicate;

    /**
     * Creates a FindMemberCommand to find the specified {@code Member}
     *
     * @param predicate The predicate to find the member.
     */
    public FindMemberCommand(MemberContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMemberList(predicate);
        model.clearTaskList();
        return new CommandResult(
                String.format(Messages.MESSAGE_MEMBERS_LISTED_OVERVIEW, model.getFilteredMemberList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindMemberCommand)) {
            return false;
        }

        FindMemberCommand otherFindCommand = (FindMemberCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
