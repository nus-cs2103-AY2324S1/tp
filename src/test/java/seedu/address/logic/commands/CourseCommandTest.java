package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.course.Lesson;
import seedu.address.model.course.UniqueCourseList;

public class CourseCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validCourse_success() {
        Course targetCourse = UniqueCourseList.findByCourseCode("CS2103T");
        CourseCommand courseCommand = new CourseCommand(targetCourse);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        StringBuilder sb = new StringBuilder();
        sb.append("Course: ").append(targetCourse.getCourseCode()).append(" ");
        sb.append(targetCourse.getName()).append("\n");
        Set<Lesson> lessons = targetCourse.getLessons();
        lessons.forEach(lesson -> sb.append(lesson.toString()).append("\n"));

        CommandResult expectedCommandResult = new CommandResult(sb.toString());
        assertCommandSuccess(courseCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        Course targetCourse = UniqueCourseList.findByCourseCode("CS2103T");
        CourseCommand courseCommand = new CourseCommand(targetCourse);
        CourseCommand otherCourseCommand = new CourseCommand(targetCourse);
        CourseCommand differentCourseCommand = new CourseCommand(UniqueCourseList.findByCourseCode("CS1231S"));

        // same object -> returns true
        assertEquals(courseCommand, courseCommand);

        // same values -> returns true
        assertEquals(courseCommand, otherCourseCommand);

        // different types -> returns false
        assertNotEquals(courseCommand, 1);

        // null -> returns false
        assertNotEquals(courseCommand, null);

        // different course -> returns false
        assertNotEquals(courseCommand, differentCourseCommand);
    }

    @Test
    public void toStringMethod() {
        Course targetCourse = UniqueCourseList.findByCourseCode("CS2103T");
        CourseCommand courseCommand = new CourseCommand(targetCourse);
        String expected = CourseCommand.class.getCanonicalName() + "{targetCourse=" + targetCourse + "}";
        assertEquals(expected, courseCommand.toString());
    }
}
