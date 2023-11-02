package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
        PersonNameOrGroupContainsKeywordsPredicate personPredicate = preparePersonPredicate(" ");
        EventNameOrGroupContainsKeywordsPredicate eventPredicate = prepareEventPredicate(" ");
        FindAllCommand command = new FindAllCommand(personPredicate, eventPredicate);
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleItemsFound() {
        // Event and person name only
    }

    @Test
    public void execute_multipleKeywords_oneItemFound() {

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
