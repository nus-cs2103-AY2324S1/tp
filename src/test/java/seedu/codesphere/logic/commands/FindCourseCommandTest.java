//@@author tiongjjyi
package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.TypicalCourses.CS2030S;
import static seedu.codesphere.testutil.TypicalCourses.ST2334;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.Messages;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.CourseNameContainsKeywordsPredicate;

public class FindCourseCommandTest {

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());
    @Test
    public void equals() {
        CourseNameContainsKeywordsPredicate firstPredicate =
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CourseNameContainsKeywordsPredicate secondPredicate =
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCourseCommand findFirstCommand = new FindCourseCommand(firstPredicate);
        FindCourseCommand findSecondCommand = new FindCourseCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCourseCommand findFirstCommandCopy = new FindCourseCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCourseFound() {
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(Arrays.asList(" "));
        FindCourseCommand command = new FindCourseCommand(predicate);
        CommandResult result = command.execute(model);
        CommandResult expectedResult = new CommandResult(
                String.format(Messages.MESSAGE_COURSES_LISTED_OVERVIEW, 0));
        model.updateFilteredCourseList(predicate);
        assertEquals(result, expectedResult);
        assertEquals(Collections.emptyList(), model.getFilteredCourseList());
    }

    @Test
    public void execute_multipleKeywords_multipleCoursesFound() {
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(
                Arrays.asList("CS2030S", "ST"));
        FindCourseCommand command = new FindCourseCommand(predicate);
        CommandResult result = command.execute(model);
        CommandResult expectedResult = new CommandResult(
                String.format(Messages.MESSAGE_COURSES_LISTED_OVERVIEW, 2));
        model.updateFilteredCourseList(predicate);
        assertEquals(result, expectedResult);
        model.updateFilteredCourseList(predicate);
        assertEquals(Arrays.asList(CS2030S, ST2334), model.getFilteredCourseList());
    }

    @Test
    public void toStringMethod() {
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(
                Arrays.asList("keyword"));
        FindCourseCommand findCourseCommand = new FindCourseCommand(predicate);
        String expected = FindCourseCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCourseCommand.toString());
    }

}
