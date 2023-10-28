package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NameSubjectPredicate;
import seedu.address.model.person.SubjectContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;
    private final SubjectContainsKeywordsPredicate subject;

    /**
     * Constructore for the findCommand class
     *
     * @param predicate the keyword to be searched which starts with the prefix n/
     * @param subject the keyword to be searched which starts with the prefix sb/
     */
    public FindCommand(NameContainsKeywordsPredicate predicate, SubjectContainsKeywordsPredicate subject) {
        this.predicate = predicate;
        this.subject = subject;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        NameSubjectPredicate nameSubject = new NameSubjectPredicate(predicate, subject);
        model.updateFilteredPersonList(nameSubject);
        return new CommandResult(
                String.format(Messages.MESSAGE_TUTEES_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size()));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
