package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.StudentContainsKeywordsPredicate;

/**
 * Lookup all students in class manager whose details contain any of
 * the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class LookupCommand extends Command {

    public static final String COMMAND_WORD = "lookup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds and lists all students who's details contain any of "
            + "the specified keywords (case-insensitive) and displays them "
            + "as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_CLASS_NUMBER + "CLASS_NUMBER] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_STUDENT_NUMBER + "STUDENT_NUMBER] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Examples (Comma separated): "
            + COMMAND_WORD + " " + PREFIX_NAME + "li, "
            + COMMAND_WORD + " " + PREFIX_TAG + "t11";

    public static final String MESSAGE_NO_MATCH = "No match found!";

    private final StudentContainsKeywordsPredicate predicate;

    public LookupCommand(StudentContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        if (model.getFilteredStudentList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_MATCH);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LookupCommand)) {
            return false;
        }

        LookupCommand otherLookupCommand = (LookupCommand) other;
        return predicate.equals(otherLookupCommand.predicate);
    }

    @Override
    public int hashCode() {
        return predicate.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
