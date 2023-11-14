//@@author tiongjjyi
package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.predicates.AllPendingQuestionPredicate;
import seedu.codesphere.testutil.CourseBuilder;
import seedu.codesphere.testutil.StudentBuilder;

public class ListPendingQuestionsCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void equals() {
        AllPendingQuestionPredicate firstPredicate =
                new AllPendingQuestionPredicate();

        ListPendingQuestionsCommand listPendingQuestionCommand = new ListPendingQuestionsCommand(firstPredicate);

        // same object -> returns true
        assertTrue(listPendingQuestionCommand.equals(listPendingQuestionCommand));

        // same values -> returns true
        ListPendingQuestionsCommand listPendingQuestionCommandCopy = new ListPendingQuestionsCommand(firstPredicate);
        assertTrue(listPendingQuestionCommand.equals(listPendingQuestionCommandCopy));

        // different types -> returns false
        assertFalse(listPendingQuestionCommand.equals(1));

        // null -> returns false
        assertFalse(listPendingQuestionCommand.equals(null));

    }

    @Test
    public void execute_zeroKeywords_success() {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());
        AllPendingQuestionPredicate predicate = new AllPendingQuestionPredicate();
        ListPendingQuestionsCommand command = new ListPendingQuestionsCommand(predicate);
        command.execute(model);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                validCourse.getFilteredStudentList().size());
        CommandResult commandResult = command.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);
        assertEquals(commandResult, expectedResult);
    }
}
