package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.MEETING_ASSIGNMENT_SUBMISSION;
import static seedu.address.testutil.TypicalEvents.MEETING_BIRTHDAY_PARTY;
import static seedu.address.testutil.TypicalEvents.MEETING_PROJECT_DISCUSSION;
import static seedu.address.testutil.TypicalEvents.MEETING_PROJECT_PRESENTATION;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventNameOrGroupContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindEventCommand}.
 */
public class FindEventCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        EventNameOrGroupContainsKeywordsPredicate firstPredicate =
                new EventNameOrGroupContainsKeywordsPredicate(Collections.singletonList("first"));
        EventNameOrGroupContainsKeywordsPredicate secondPredicate =
                new EventNameOrGroupContainsKeywordsPredicate(Collections.singletonList("second"));

        FindEventCommand findEventFirstCommand = new FindEventCommand(firstPredicate);
        FindEventCommand findEventSecondCommand = new FindEventCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findEventFirstCommand.equals(findEventFirstCommand));

        // same values -> returns true
        FindEventCommand findFirstCommandCopy = new FindEventCommand(firstPredicate);
        assertTrue(findEventFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findEventFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findEventFirstCommand.equals(null));

        // different events -> returns false
        assertFalse(findEventFirstCommand.equals(findEventSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventNameOrGroupContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_oneKeyword_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        EventNameOrGroupContainsKeywordsPredicate predicate = preparePredicate("Project");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_PROJECT_DISCUSSION, MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() {
        // Name only
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventNameOrGroupContainsKeywordsPredicate predicate = preparePredicate("party submission presentation");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION, MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_oneEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        EventNameOrGroupContainsKeywordsPredicate predicate = preparePredicate("birthday party");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY), model.getFilteredEventList());
    }

    @Test
    public void toStringMethod() {
        EventNameOrGroupContainsKeywordsPredicate predicate =
                new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindEventCommand findEventCommand = new FindEventCommand(predicate);
        String expected = FindEventCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findEventCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code EventNameContainsKeywordsPredicate}.
     */
    private EventNameOrGroupContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
