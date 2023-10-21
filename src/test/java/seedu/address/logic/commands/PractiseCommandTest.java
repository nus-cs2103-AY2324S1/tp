package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class PractiseCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

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
    public void equals() {
        final PractiseCommand standardCommand = new PractiseCommand(INDEX_FIRST_CARD);

        // same values -> returns true

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));


        // different index -> returns false
        assertFalse(standardCommand.equals(new PractiseCommand(INDEX_SECOND_CARD)));

    }

}
