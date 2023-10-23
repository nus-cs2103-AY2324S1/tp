package seedu.ccacommander.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;
import static seedu.ccacommander.testutil.TypicalEvents.AURORA_BOREALIS;
import static seedu.ccacommander.testutil.TypicalEvents.BOXING_DAY;
import static seedu.ccacommander.testutil.TypicalEvents.CHINESE_NEW_YEAR;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;
import seedu.ccacommander.model.event.EventNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindEventCommand}.
 */
public class FindEventCommandTest {
    private Model model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCcaCommander(), new UserPrefs());

    @Test
    public void equals() {
        EventNameContainsKeywordsPredicate firstPredicate =
                new EventNameContainsKeywordsPredicate(Collections.singletonList("first"));
        EventNameContainsKeywordsPredicate secondPredicate =
                new EventNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindEventCommand findFirstEventCommand = new FindEventCommand(firstPredicate);
        FindEventCommand findSecondEventCommand = new FindEventCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstEventCommand.equals(findFirstEventCommand));

        // same values -> returns true
        FindEventCommand findFirstEventCommandCopy = new FindEventCommand(firstPredicate);
        assertTrue(findFirstEventCommand.equals(findFirstEventCommandCopy));

        // different types -> returns false
        assertFalse(findFirstEventCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstEventCommand.equals(null));

        // different member -> returns false
        assertFalse(findFirstEventCommand.equals(findSecondEventCommand));
    }

    @Test
    public void execute_zeroKeywords_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventNameContainsKeywordsPredicate predicate = preparePredicate("Aurora Boxing Chinese");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AURORA_BOREALIS, BOXING_DAY, CHINESE_NEW_YEAR), model.getFilteredEventList());
    }

    @Test
    public void toStringMethod() {
        EventNameContainsKeywordsPredicate predicate =
                new EventNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindEventCommand findEventCommand = new FindEventCommand(predicate);
        String expected = FindEventCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findEventCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code EventNameContainsKeywordsPredicate}.
     */
    private EventNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EventNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
