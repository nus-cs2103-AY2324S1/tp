//@@author tiongjjyi
package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_PENDING_QUESTION_AMY;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_PENDING_QUESTION_BOB;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.testutil.CourseBuilder;
import seedu.codesphere.testutil.StudentBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for PendingQuestionCommand.
 */
public class PendingQuestionCommandTest {

    private static final String PENDING_QUESTION_STUB = "Some question";

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());
    @Test
    public void execute_addPendingQuestionUnfilteredList_success() throws CommandException {
        Course validCourse1 = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse1);
        validCourse1.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse1.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withPendingQuestion(PENDING_QUESTION_STUB).build();

        PendingQuestionCommand pendingQuestionCommand = new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(PENDING_QUESTION_STUB));
        CommandResult commandResult = pendingQuestionCommand.execute(model);
        String expectedMessage = String.format(PendingQuestionCommand
                .MESSAGE_ADD_PENDING_QUESTION_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }


    @Test
    public void execute_deletePendingQuestionUnfilteredList_success() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withPendingQuestion("").build();

        PendingQuestionCommand pendingQuestionCommand =
                new PendingQuestionCommand(INDEX_FIRST_STUDENT, new PendingQuestion(""));
        CommandResult commandResult = pendingQuestionCommand.execute(model);
        String expectedMessage = String.format(PendingQuestionCommand
                .MESSAGE_ADD_PENDING_QUESTION_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void equals() {
        final PendingQuestionCommand standardCommand = new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(VALID_PENDING_QUESTION_AMY));
        final PendingQuestionCommand commandWithSameValues = new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(VALID_PENDING_QUESTION_AMY));
        System.out.println(commandWithSameValues);

        // same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PendingQuestionCommand(INDEX_SECOND_STUDENT,
                new PendingQuestion(VALID_PENDING_QUESTION_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(VALID_PENDING_QUESTION_BOB))));
    }
}
