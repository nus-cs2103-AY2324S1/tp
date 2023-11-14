package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalStudents.ALICE;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.codesphere.commons.core.GuiSettings;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.ReadOnlyUserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.testutil.CourseBuilder;
import seedu.codesphere.testutil.StudentBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        Student validStudent = new StudentBuilder().build();
        Course validCourse = new CourseBuilder().build();
        ModelStubWithCourse modelStub = new ModelStubWithCourse(validCourse);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);
        CommandResult commandResult = new AddCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validStudent)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateStudent_throwsDuplicateStudentException() {
        Student validStudent = new StudentBuilder().build();
        Course validCourse = new CourseBuilder().build();
        validCourse.addStudent(validStudent);
        ModelStubWithCourse modelStub = new ModelStubWithCourse(validCourse);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);
        AddCommand addCommand = new AddCommand(validStudent);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_STUDENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        Student bob = new StudentBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different Student -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getCodeSphereFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCodeSphereFilePath(Path codeSphereFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCourse(Course course) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourseList(ReadOnlyCourseList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCourseList getCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCourse(Course course) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCourse(Course target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourse(Course target, Course editedCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Course> getFilteredCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCourseList(Predicate<Course> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetFilteredCourseList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Course.
     */
    private class ModelStubWithCourse extends ModelStub {
        private final Course course;

        ModelStubWithCourse(Course course) {
            requireNonNull(course);
            this.course = course;
        }
    }

}
