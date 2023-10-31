package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.course.UniqueCourseList;
import seedu.address.model.person.predicates.TeachingCoursePredicate;

/**
 * Adds the default course for users in the address book.
 */
public class TeachCommand extends Command {
    public static final String COMMAND_WORD = "teach";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets up default course users are teaching.\n"
            + "Parameters: "
            + PREFIX_COURSE + "COURSE_CODE \n"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_COURSE + "CS1231S \n"
            + UniqueCourseList.getCourseListString();

    public static final String MESSAGE_SUCCESS = " is successfully added as default course.";

    /**
     * Sets course as the default.
     */
    private final Course course;

    /**
     * Creates TeachCommand object.
     * @param course The course to be added as default course.
     */
    public TeachCommand(Course course) {
        requireNonNull(course);
        this.course = course;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.setTeaching(course);

        //Filter the TAs by the default course
        TeachingCoursePredicate predicate = new TeachingCoursePredicate(List.of(course));
        model.updateFilteredPersonList(predicate);

        return new CommandResult(course.getCourseCode() + MESSAGE_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TeachCommand)) {
            return false;
        }

        TeachCommand otherTeachCommand = (TeachCommand) other;
        return course.equals(otherTeachCommand.course);
    }
}
