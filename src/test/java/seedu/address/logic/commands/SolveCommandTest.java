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
import seedu.address.logic.Messages;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Card;

public class SolveCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void constructor_invalidTarget_throwsException() {
        assertThrows(NullPointerException.class, () -> new SolveCommand(null));
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SolveCommand(INDEX_FIRST_CARD).execute(null));
    }

    @Test
    public void execute_validIndex_success() {
        Card cardToSolve = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        SolveCommand solveCommand = new SolveCommand(INDEX_FIRST_CARD);

        String expectedMessage = Messages.formatSolve(cardToSolve, INDEX_FIRST_CARD);

        ModelManager expectedModel = new ModelManager(model.getDeck(), new UserPrefs());

        assertCommandSuccess(solveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        SolveCommand solveCommand = new SolveCommand(outOfBoundIndex);

        assertCommandFailure(solveCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_practiseHighestPriority_success() {
        SolveCommand practiseCommand = new SolveCommand(Index.fromZeroBased(0));

        String expectedMessage = String.format(SolveCommand.MESSAGE_SOLVE_CARD_SUCCESS,
                Messages.formatSolve(model.getFilteredCardList().get(0), INDEX_FIRST_CARD));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());

        assertCommandSuccess(practiseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_practiseIndexInTheMiddle_success() {
        Index index = Index.fromZeroBased(1);
        SolveCommand practiseCommand = new SolveCommand(index);

        String expectedMessage = String.format(SolveCommand.MESSAGE_SOLVE_CARD_SUCCESS,
                Messages.formatSolve(model.getFilteredCardList().get(1), index));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());


        assertCommandSuccess(practiseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_practiseIndexInTheEnd_success() {
        Index index = Index.fromZeroBased(model.getFilteredCardList().size() - 1);
        SolveCommand practiseCommand = new SolveCommand(index);

        String expectedMessage = String.format(SolveCommand.MESSAGE_SOLVE_CARD_SUCCESS,
                Messages.formatSolve(model.getFilteredCardList()
                        .get(model.getFilteredCardList().size() - 1), index));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());


        assertCommandSuccess(practiseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        SolveCommand practiseCommand = new SolveCommand(outOfBoundIndex);

        assertCommandFailure(practiseCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_incrementSolveCount() {
        int ind = 2;
        Index index = Index.fromZeroBased(ind);
        SolveCommand practiseCommand = new SolveCommand(index);
        Card targetCard = model.getFilteredCardList().get(ind);

        String expectedMessage = String.format(SolveCommand.MESSAGE_SOLVE_CARD_SUCCESS,
                Messages.formatSolve(model.getFilteredCardList()
                        .get(ind), index));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());

        assertCommandSuccess(practiseCommand, model, expectedMessage, expectedModel);

        assertTrue(targetCard.getSolveCount().equals(1));
    }

    @Test
    public void equals() {
        SolveCommand solveCommand = new SolveCommand(INDEX_FIRST_CARD);
        SolveCommand otherSolveCommand = new SolveCommand(INDEX_SECOND_CARD);

        // same object -> returns true
        assertTrue(solveCommand.equals(solveCommand));

        // same values -> returns true
        SolveCommand deleteFirstCommandCopy = new SolveCommand(INDEX_FIRST_CARD);
        assertTrue(solveCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(solveCommand.equals(1));

        // null -> returns false
        assertFalse(solveCommand.equals(null));

        // different Card -> returns false
        assertFalse(solveCommand.equals(otherSolveCommand));
    }

    @Test
    public void execute_withValidRandomIndex_success() {
        // to make it is valid, make sure random index has been set in model.
        Model modelToUse = new ModelManager(model.getDeck(), new UserPrefs());
        modelToUse.setRandomIndex(Index.fromOneBased(1));

        SolveCommand solveCommand = new SolveCommand(Index.RANDOM);

        String expectedMessage = String.format(SolveCommand.MESSAGE_SOLVE_CARD_SUCCESS,
                Messages.formatSolve(modelToUse.getFilteredCardList().get(0), INDEX_FIRST_CARD));

        Model expectedModel = new ModelManager(new Deck(modelToUse.getDeck()), new UserPrefs());

        assertCommandSuccess(solveCommand, modelToUse, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withInvalidRandomIndex_failure() {
        // attempt to run solve without running random first (i.e. random index not set)
        SolveCommand solveCommand = new SolveCommand(Index.RANDOM);

        assertCommandFailure(solveCommand, model, Messages.MESSAGE_RANDOM_INDEX_NOT_INITIALISED);
    }
}
