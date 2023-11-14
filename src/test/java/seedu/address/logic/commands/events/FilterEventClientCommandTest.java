package seedu.address.logic.commands.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.EVENT1;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.FinancesBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventClientContainsKeywordsPredicate;




public class FilterEventClientCommandTest {
    private Model model = new ModelManager(getTypicalPersonsBook(), getTypicalEventsBook(), new FinancesBook(),
            new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPersonsBook(), getTypicalEventsBook(), new FinancesBook(),
            new UserPrefs());

    @Test
    public void equals() {
        EventClientContainsKeywordsPredicate predicate1 =
                new EventClientContainsKeywordsPredicate(Arrays.asList("meeting"));
        EventClientContainsKeywordsPredicate predicate2 =
                new EventClientContainsKeywordsPredicate(Arrays.asList("conference"));

        FilterEventClientCommand command1 = new FilterEventClientCommand(predicate1);
        FilterEventClientCommand command2 = new FilterEventClientCommand(predicate1);
        FilterEventClientCommand command3 = new FilterEventClientCommand(predicate2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        assertTrue(command1.equals(command2));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different predicate -> returns false
        assertFalse(command1.equals(command3));
    }

    @Test
    public void execute_zeroKeywords_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventClientContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterEventClientCommand command = new FilterEventClientCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_personsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        EventClientContainsKeywordsPredicate predicate = preparePredicate("Alice");
        FilterEventClientCommand command = new FilterEventClientCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EVENT1), model.getFilteredEventList());
    }

    @Test
    public void toStringMethod() {
        EventClientContainsKeywordsPredicate predicate =
                new EventClientContainsKeywordsPredicate(Arrays.asList("meeting"));
        FilterEventClientCommand command = new FilterEventClientCommand(predicate);
        String expected = FilterEventClientCommand.class.getCanonicalName() + "{clientPredicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private EventClientContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EventClientContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
