package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CourseContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCourseCommand}.
 */
public class FindCourseCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CourseContainsKeywordsPredicate firstPredicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("first"));
        CourseContainsKeywordsPredicate secondPredicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("second"));

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
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        CourseContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCourseCommand command = new FindCourseCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleCoursesFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        CourseContainsKeywordsPredicate predicate = preparePredicate("CS2103T");
        FindCourseCommand command = new FindCourseCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        CourseContainsKeywordsPredicate predicate = new CourseContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCourseCommand findCommand = new FindCourseCommand(predicate);
        String expected = FindCourseCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private CourseContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CourseContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

