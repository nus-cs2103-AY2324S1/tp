package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;

import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.course.Lesson;
import seedu.address.model.course.UniqueCourseList;

/**
 * Displays all courses in the address book if no course is listed.
 * Displays lesson information of the course if a course is listed.
 */
public class CourseCommand extends Command {

    public static final String COMMAND_WORD = "course";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays lesson information about course\n"
            + "Parameters: " + PREFIX_COURSE + "Course\n"
            + "Example: " + COMMAND_WORD + " c/CS2103T" + "\n"
            + UniqueCourseList.getCourseListString();

    private final Course targetCourse;

    public CourseCommand(Course targetCourse) {
        this.targetCourse = targetCourse;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder sb = new StringBuilder();
        sb.append("Course: ").append(targetCourse.getCourseCode()).append(" ");
        sb.append(targetCourse.getName()).append("\n");
        Set<Lesson> lessons = targetCourse.getLessons();
        lessons.forEach(lesson -> sb.append(lesson.toString()).append("\n"));
        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseCommand)) {
            return false;
        }

        CourseCommand otherCourseCommand = (CourseCommand) other;
        return targetCourse.equals(otherCourseCommand.targetCourse);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetCourse", targetCourse)
                .toString();
    }
}
