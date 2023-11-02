package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Card;

class HintCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void constructor_invalidTarget_throwsException() {
        assertThrows(NullPointerException.class, () -> new HintCommand(null));
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HintCommand(INDEX_FIRST_CARD).execute(null));
    }

    @Test
    public void execute_validIndex_success() {
        Card cardToHint = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        HintCommand hintCommand = new HintCommand(INDEX_FIRST_CARD);

        String expectedMessage = Messages.formatHint(cardToHint, INDEX_FIRST_CARD);

        ModelManager expectedModel = new ModelManager(model.getDeck(), new UserPrefs());

        assertCommandSuccess(hintCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        HintCommand hintCommand = new HintCommand(outOfBoundIndex);

        assertCommandFailure(hintCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        HintCommand hintCommand = new HintCommand(INDEX_FIRST_CARD);
        HintCommand otherHintCommand = new HintCommand(INDEX_SECOND_CARD);

        // same object -> returns true
        assertTrue(hintCommand.equals(hintCommand));

        // same values -> returns true
        HintCommand deleteFirstCommandCopy = new HintCommand(INDEX_FIRST_CARD);
        assertTrue(hintCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(hintCommand.equals(1));

        // null -> returns false
        assertFalse(hintCommand.equals(null));

        // different Card -> returns false
        assertFalse(hintCommand.equals(otherHintCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        HintCommand hintCommand = new HintCommand(targetIndex);
        String expected = HintCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, hintCommand.toString());
    }
}
