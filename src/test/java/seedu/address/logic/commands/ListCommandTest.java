package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Card;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void constructor_invalidPredicates_throwsException() {
        assertThrows(NullPointerException.class, () -> new ListCommand(null));
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        List<Predicate<Card>> validPredicates = List.of(PREDICATE_SHOW_ALL_CARDS);
        assertThrows(NullPointerException.class, () -> new ListCommand(validPredicates).execute(null));
    }

    @Test
    public void execute_validPredicates_success() {
        List<Predicate<Card>> validPredicates = List.of(PREDICATE_SHOW_ALL_CARDS);
        ListCommand listCommand = new ListCommand(validPredicates);
        String expectedCommandResult = ListCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(listCommand, model, expectedCommandResult, model);
    }

    @Test
    public void equals() {
        List<Predicate<Card>> validPredicates = new java.util.ArrayList<>(List.of(PREDICATE_SHOW_ALL_CARDS));
        ListCommand listCommand = new ListCommand(validPredicates);

        validPredicates.add(card -> card.getQuestion().startsWith("What"));
        ListCommand otherListCommand = new ListCommand(validPredicates);

        // same object -> returns true
        assertTrue(listCommand.equals(listCommand));

        // same values -> returns true
        ListCommand listCommandCopy = new ListCommand(validPredicates);
        assertTrue(listCommand.equals(listCommandCopy));

        // different types -> returns false
        assertFalse(listCommand.equals(1));

        // null -> returns false
        assertFalse(listCommand.equals(null));
    }
}
