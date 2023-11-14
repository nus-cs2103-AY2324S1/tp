//@@author tiongjjyi
package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;

/**
 * Finds and lists all courses with name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCourseCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all courses with names containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS";

    private final Predicate<Course> predicate;

    /**
     * Constructs a FindCourseCommand with the specified predicate for filtering.
     *
     * @param predicate A predicate that filters entities based on the course names.
     */
    public FindCourseCommand(Predicate <Course> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredCourseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_COURSES_LISTED_OVERVIEW, model.getFilteredCourseList().size()));
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

        FindCourseCommand otherFindCourseCommand = (FindCourseCommand) other;

        return predicate.equals(otherFindCourseCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
