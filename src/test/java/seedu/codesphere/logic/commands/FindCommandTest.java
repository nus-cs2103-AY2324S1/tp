package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.codesphere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalStudents.BENSON;
import static seedu.codesphere.testutil.TypicalStudents.DANIEL;
import static seedu.codesphere.testutil.TypicalStudents.ELLE;
import static seedu.codesphere.testutil.TypicalStudents.getTypicalStudentList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.codesphere.testutil.CourseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_falseKeywords_noPersonsFound() {
        Course validCourse = new CourseBuilder().withCourseName("CS1101S")
                .withStudents(getTypicalStudentList()).build();

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("abcde");
        FindCommand command = new FindCommand(predicate);
        validCourse.updateFilteredStudentList(predicate);
        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), validCourse.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        Course validCourse = new CourseBuilder().withCourseName("CS1101S")
                .withStudents(getTypicalStudentList()).build();

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Me");
        FindCommand command = new FindCommand(predicate);
        validCourse.updateFilteredStudentList(predicate);
        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        System.out.println(expectedModel.equals(model));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, ELLE), validCourse.getFilteredStudentList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
