package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_EVENT_LISTED_OVERVIEW;
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
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_oneKeyword_multipleEventsFound() {
        // Event name only
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        EventNameOrGroupContainsKeywordsPredicate predicate = preparePredicate("Project");
        FindEventCommand command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_PROJECT_DISCUSSION, MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());

        // Person name only
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        predicate = preparePredicate("Alice");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Group only
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        predicate = preparePredicate("CS2103T");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_PROJECT_DISCUSSION, MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() {
        // Event name only
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventNameOrGroupContainsKeywordsPredicate predicate = preparePredicate("party submission presentation");
        FindEventCommand command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION, MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());

        // Person name only
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        predicate = preparePredicate("Bob Carl");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Group name only
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        predicate = preparePredicate("friends classmates");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Event and person name
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        predicate = preparePredicate("Bob Submission");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Person and group name
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        predicate = preparePredicate("friends Carl");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Event and group name
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        predicate = preparePredicate("friends Submission");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Event, person and group name
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 4);
        predicate = preparePredicate("friends Submission CS2103T");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION, MEETING_PROJECT_DISCUSSION,
                        MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_oneEventFound() {
        // Event name only
        String expectedMessage = String.format(MESSAGE_EVENT_LISTED_OVERVIEW, 1);
        EventNameOrGroupContainsKeywordsPredicate predicate = preparePredicate("birthday party");
        FindEventCommand command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY), model.getFilteredEventList());

        // Person name only
        expectedMessage = String.format(MESSAGE_EVENT_LISTED_OVERVIEW, 1);
        predicate = preparePredicate("Bob Daniel");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY),
                model.getFilteredEventList());

        // Group name only
        expectedMessage = String.format(MESSAGE_EVENT_LISTED_OVERVIEW, 1);
        predicate = preparePredicate("friends family");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY),
                model.getFilteredEventList());

        // Event and person name
        expectedMessage = String.format(MESSAGE_EVENT_LISTED_OVERVIEW, 1);
        predicate = preparePredicate("Bob Party");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY),
                model.getFilteredEventList());

        // Person and group name
        expectedMessage = String.format(MESSAGE_EVENT_LISTED_OVERVIEW, 1);
        predicate = preparePredicate("family Bob");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY), model.getFilteredEventList());

        // Event and group name
        expectedMessage = String.format(MESSAGE_EVENT_LISTED_OVERVIEW, 1);
        predicate = preparePredicate("Party friends");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY),
                model.getFilteredEventList());

        // Event, person and group name (problem)
        expectedMessage = String.format(MESSAGE_EVENT_LISTED_OVERVIEW, 1);
        predicate = preparePredicate("Bob Party friends");
        command = new FindEventCommand(predicate);
        predicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredEventList(predicate);
        predicate.setPersonList(model.getFullPersonList());
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
