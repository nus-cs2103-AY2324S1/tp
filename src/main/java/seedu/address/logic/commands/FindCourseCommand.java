package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CourseContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose course contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCourseCommand extends Command {

    public static final String COMMAND_WORD = "findcourse";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose courses contain the "
            + "specified course module ID (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: MODULE_ID\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    private final CourseContainsKeywordsPredicate predicate;

    public FindCourseCommand(CourseContainsKeywordsPredicate predicate) {
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
        if (!(other instanceof FindCourseCommand)) {
            return false;
        }

        FindCourseCommand otherFindCommand = (FindCourseCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
