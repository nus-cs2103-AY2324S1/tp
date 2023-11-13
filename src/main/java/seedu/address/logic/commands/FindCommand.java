package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NameSubjectPredicate;
import seedu.address.model.person.SubjectContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find persons with names or subjects "
            + "matching the specified keywords (case-insensitive).\n"
            + "Parameters: " + PREFIX_NAME + "[NAME_KEYWORD] " + PREFIX_SUBJECT + "[SUBJECT_KEYWORD]\n"
            + "Examples: \n"
            + "1. " + COMMAND_WORD + " " + PREFIX_NAME + "Alice " + PREFIX_SUBJECT + "Maths \n"
            + "2. " + COMMAND_WORD + " " + PREFIX_NAME + "Alice \n"
            + "3. " + COMMAND_WORD + " " + PREFIX_SUBJECT + "Maths";

    private static final Logger logger = LogsCenter.getLogger(FindCommand.class);

    private final NameContainsKeywordsPredicate predicate;
    private final SubjectContainsKeywordsPredicate subject;

    /**
     * Constructore for the findCommand class
     *
     * @param predicate the keyword to be searched which starts with the prefix n/
     * @param subject the keyword to be searched which starts with the prefix sb/
     */
    public FindCommand(NameContainsKeywordsPredicate predicate, SubjectContainsKeywordsPredicate subject) {
        requireAllNonNull(predicate, subject);
        this.predicate = predicate;
        this.subject = subject;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        NameSubjectPredicate nameSubject = new NameSubjectPredicate(predicate, subject);
        model.updateFilteredPersonList(nameSubject);

        logger.log(Level.INFO, "FindCommand executed with predicate: " + predicate + "and subject:" + subject);

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
