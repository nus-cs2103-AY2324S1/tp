package seedu.address.logic.commands;

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
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Card;

public class PractiseCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void constructor_invalidTarget_throwsException() {
        assertThrows(NullPointerException.class, () -> new PractiseCommand(null));
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PractiseCommand(INDEX_FIRST_CARD).execute(null));
    }

    @Test
    public void execute_validIndex_success() {
        Card cardToPractise = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        PractiseCommand practiseCommand = new PractiseCommand(INDEX_FIRST_CARD);

        String expectedMessage = Messages.formatPractise(cardToPractise, INDEX_FIRST_CARD);

        ModelManager expectedModel = new ModelManager(model.getDeck(), new UserPrefs());

        assertCommandSuccess(practiseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        PractiseCommand practiseCommand = new PractiseCommand(outOfBoundIndex);

        assertCommandFailure(practiseCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_practiseHighestPriority_success() {
        PractiseCommand practiseCommand = new PractiseCommand(Index.fromZeroBased(0));

        String expectedMessage = String.format(PractiseCommand.MESSAGE_PRACTISE_CARD_SUCCESS,
                Messages.formatPractise(model.getFilteredCardList().get(0), INDEX_FIRST_CARD));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());


        assertCommandSuccess(practiseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_practiseIndexInTheMiddle_success() {
        Index index = Index.fromZeroBased(1);
        PractiseCommand practiseCommand = new PractiseCommand(index);

        String expectedMessage = String.format(PractiseCommand.MESSAGE_PRACTISE_CARD_SUCCESS,
                Messages.formatPractise(model.getFilteredCardList().get(1), index));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());


        assertCommandSuccess(practiseCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_practiseIndexInTheEnd_success() {
        Index index = Index.fromZeroBased(model.getFilteredCardList().size() - 1);
        PractiseCommand practiseCommand = new PractiseCommand(index);

        String expectedMessage = String.format(PractiseCommand.MESSAGE_PRACTISE_CARD_SUCCESS,
                Messages.formatPractise(model.getFilteredCardList()
                        .get(model.getFilteredCardList().size() - 1), index));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());


        assertCommandSuccess(practiseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        PractiseCommand practiseCommand = new PractiseCommand(outOfBoundIndex);

        assertCommandFailure(practiseCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_equals() {
        PractiseCommand practiseCommand = new PractiseCommand(INDEX_FIRST_CARD);
        PractiseCommand otherPractiseCommand = new PractiseCommand(INDEX_SECOND_CARD);

        // same object -> returns true
        assertTrue(practiseCommand.equals(practiseCommand));

        // same values -> returns true
        PractiseCommand deleteFirstCommandCopy = new PractiseCommand(INDEX_FIRST_CARD);
        assertTrue(practiseCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(practiseCommand.equals(1));

        // null -> returns false
        assertFalse(practiseCommand.equals(null));

        // different Card -> returns false
        assertFalse(practiseCommand.equals(otherPractiseCommand));
    }

    @Test
    public void execute_practiseToString() {
        Index testIndex = Index.fromOneBased(1);
        PractiseCommand modelCommand = new PractiseCommand(testIndex);
        String expectedString = new ToStringBuilder(modelCommand)
                .add("targetIndex", testIndex)
                .toString();
        String resultString = modelCommand.toString();

        assertTrue(resultString.equals(expectedString));
    }


}
