package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CourseContainsKeywordsPredicate;

/**
 * Command that filters out the people based on their course
 */
public class FindCourseCommand extends Command {

    /**
     * Keyword to trigger the searchcourse command.
     */
    public static final String COMMAND_WORD = "searchcourse";

    /**
     * Usage instructions for the 'searchcourse' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose courses contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2100";

    private final CourseContainsKeywordsPredicate predicate;

    /**
     * Constructs a FindCourseCommand with a specific keyword predicate.
     *
     * @param predicate The predicate used to filter persons by course.
     */
    public FindCourseCommand(CourseContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the FindCourseCommand to filter and list persons whose courses contain the specified keywords.
     *
     * @param model The model that this command should operate on.
     * @return The CommandResult containing a summary of the filtered persons.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Checks equality of the FindCourseCommand with another object.
     *
     * @param other The object to compare for equality.
     * @return True if the objects are equal, false otherwise.
     */
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

    /**
     * Returns a string representation of the FindCourseCommand.
     *
     * @return A string representing the FindCourseCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
