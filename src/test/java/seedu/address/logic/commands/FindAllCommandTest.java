package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_PERSON_AND_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_PERSON_AND_EVENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.MEETING_ASSIGNMENT_SUBMISSION;
import static seedu.address.testutil.TypicalEvents.MEETING_BIRTHDAY_PARTY;
import static seedu.address.testutil.TypicalEvents.MEETING_PROJECT_DISCUSSION;
import static seedu.address.testutil.TypicalEvents.MEETING_PROJECT_PRESENTATION;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventNameOrGroupContainsKeywordsPredicate;
import seedu.address.model.person.PersonNameOrGroupContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAllCommand}.
 */
public class FindAllCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonNameOrGroupContainsKeywordsPredicate firstPersonPredicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Collections.singletonList("first"));
        PersonNameOrGroupContainsKeywordsPredicate secondPersonPredicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Collections.singletonList("second"));

        EventNameOrGroupContainsKeywordsPredicate firstEventPredicate =
                new EventNameOrGroupContainsKeywordsPredicate(Collections.singletonList("first"));
        EventNameOrGroupContainsKeywordsPredicate secondEventPredicate =
                new EventNameOrGroupContainsKeywordsPredicate(Collections.singletonList("second"));

        FindAllCommand findFirstCommand = new FindAllCommand(firstPersonPredicate, firstEventPredicate);
        FindAllCommand findSecondCommand = new FindAllCommand(secondPersonPredicate, secondEventPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAllCommand findFirstCommandCopy = new FindAllCommand(firstPersonPredicate, firstEventPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW, 0, 0);
        String userInput = " ";
        PersonNameOrGroupContainsKeywordsPredicate personPredicate = preparePersonPredicate(userInput);
        EventNameOrGroupContainsKeywordsPredicate eventPredicate = prepareEventPredicate(userInput);
        FindAllCommand command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_oneKeyword_multipleItemsFound() {
        // Person name only
        String expectedMessage = String.format(MESSAGE_PERSON_AND_EVENTS_LISTED_OVERVIEW, 1, 2);
        String userInput = "Alice";
        PersonNameOrGroupContainsKeywordsPredicate personPredicate = preparePersonPredicate(userInput);
        EventNameOrGroupContainsKeywordsPredicate eventPredicate = prepareEventPredicate(userInput);
        FindAllCommand command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Event name only
        expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW, 0, 2);
        userInput = "Project";
        personPredicate = preparePersonPredicate(userInput);
        eventPredicate = prepareEventPredicate(userInput);
        command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_PROJECT_DISCUSSION, MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());

        // Person group only
        expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW, 2, 0);
        userInput = "teammates";
        personPredicate = preparePersonPredicate(userInput);
        eventPredicate = prepareEventPredicate(userInput);
        command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE), model.getFilteredPersonList());
        assertEquals(Collections.emptyList(), model.getFilteredEventList());

        // Event group only
        expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW, 0, 2);
        userInput = "CS2103T";
        personPredicate = preparePersonPredicate(userInput);
        eventPredicate = prepareEventPredicate(userInput);
        command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_PROJECT_DISCUSSION, MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());

    }

    @Test
    public void execute_multipleKeywords_multipleItemsFound() {
        // Event and person name only
        String expectedMessage = String.format(MESSAGE_PERSON_AND_EVENTS_LISTED_OVERVIEW, 1, 2);
        String userInput = "Daniel presentation";
        PersonNameOrGroupContainsKeywordsPredicate personPredicate = preparePersonPredicate(userInput);
        EventNameOrGroupContainsKeywordsPredicate eventPredicate = prepareEventPredicate(userInput);
        FindAllCommand command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_PROJECT_PRESENTATION), model.getFilteredEventList());

        // Event and person group only
        expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW, 3, 2);
        userInput = "friends classmates";
        personPredicate = preparePersonPredicate(userInput);
        eventPredicate = prepareEventPredicate(userInput);
        command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Event name and person group
        expectedMessage = String.format(MESSAGE_PERSON_AND_EVENTS_LISTED_OVERVIEW, 1, 2);
        userInput = "family Submission";
        personPredicate = preparePersonPredicate(userInput);
        eventPredicate = prepareEventPredicate(userInput);
        command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // Event group and person name
        expectedMessage = String.format(MESSAGE_PERSON_AND_EVENT_LISTED_OVERVIEW, 1, 1);
        userInput = "classmates Fiona";
        personPredicate = preparePersonPredicate(userInput);
        eventPredicate = prepareEventPredicate(userInput);
        command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_ASSIGNMENT_SUBMISSION),
                model.getFilteredEventList());

        // All
        expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW, 2, 4);
        userInput = "Alice Benson project";
        personPredicate = preparePersonPredicate(userInput);
        eventPredicate = prepareEventPredicate(userInput);
        command = new FindAllCommand(personPredicate, eventPredicate);
        eventPredicate.setPersonList(expectedModel.getFullPersonList());
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        eventPredicate.setPersonList(model.getFullPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_BIRTHDAY_PARTY, MEETING_ASSIGNMENT_SUBMISSION, MEETING_PROJECT_DISCUSSION,
                        MEETING_PROJECT_PRESENTATION),
                model.getFilteredEventList());
    }

    @Test
    public void toStringMethod() {
        PersonNameOrGroupContainsKeywordsPredicate personPredicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("keyword"));
        EventNameOrGroupContainsKeywordsPredicate eventPredicate =
                new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList("keyword"));

        FindAllCommand findAllCommand = new FindAllCommand(personPredicate, eventPredicate);
        String expected = FindAllCommand.class.getCanonicalName() + "{predicate=" + personPredicate + "}";
        assertEquals(expected, findAllCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PersonNameContainsKeywordsPredicate}.
     */
    private PersonNameOrGroupContainsKeywordsPredicate preparePersonPredicate(String userInput) {
        return new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EventNameContainsKeywordsPredicate}.
     */
    private EventNameOrGroupContainsKeywordsPredicate prepareEventPredicate(String userInput) {
        return new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
