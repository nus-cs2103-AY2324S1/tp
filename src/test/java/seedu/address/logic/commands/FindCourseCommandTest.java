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

public class FindCourseCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void testEquals() {
        CourseContainsKeywordsPredicate firstPredicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("MA2001"));
        CourseContainsKeywordsPredicate secondPredicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("CS2030"));

        FindCourseCommand findFirstCourseCommand = new FindCourseCommand(firstPredicate);
        FindCourseCommand findSecondCourseCommand = new FindCourseCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCourseCommand.equals(findFirstCourseCommand));

        // same values -> returns true
        FindCourseCommand findFirstCourseCommandCopy = new FindCourseCommand(firstPredicate);
        assertTrue(findFirstCourseCommand.equals(findFirstCourseCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCourseCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCourseCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCourseCommand.equals(findSecondCourseCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        CourseContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCourseCommand command = new FindCourseCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleCourseFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        CourseContainsKeywordsPredicate predicate = preparePredicate("MA2001 CS1101S");
        FindCourseCommand command = new FindCourseCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        CourseContainsKeywordsPredicate predicate = new CourseContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCourseCommand findCourseCommand = new FindCourseCommand(predicate);
        String expected = FindCourseCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCourseCommand.toString());
    }

    private CourseContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CourseContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

