package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages2;
import seedu.address.model.Model2;
import seedu.address.model.ModelManager2;
import seedu.address.model.UserPrefs2;
import seedu.address.model.person.Card;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model2 model = new ModelManager2(getTypicalDeck(), new UserPrefs2());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Card cardToDelete = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        DeleteCommand2 deleteCommand = new DeleteCommand2(INDEX_FIRST_CARD);

        String expectedMessage = String.format(DeleteCommand2.MESSAGE_DELETE_CARD_SUCCESS,
                Messages2.format(cardToDelete));

        ModelManager2 expectedModel = new ModelManager2(model.getDeck(), new UserPrefs2());
        expectedModel.deleteCard(cardToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        DeleteCommand2 deleteCommand = new DeleteCommand2(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages2.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand2 deleteFirstCommand = new DeleteCommand2(INDEX_FIRST_CARD);
        DeleteCommand2 deleteSecondCommand = new DeleteCommand2(INDEX_SECOND_CARD);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand2 deleteFirstCommandCopy = new DeleteCommand2(INDEX_FIRST_CARD);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different Card -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand2 deleteCommand = new DeleteCommand2(targetIndex);
        String expected = DeleteCommand2.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCard(Model2 model) {
        model.updateFilteredCardList(p -> false);

        assertTrue(model.getFilteredCardList().isEmpty());
    }
}
