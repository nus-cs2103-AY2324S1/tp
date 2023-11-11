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
import seedu.address.model.card.Difficulty;

public class SetDifficultyCommandTest {

    private final Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_setDifficultyEasy_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "easy");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: EASY)";


        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty(Difficulty.EASY);
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith(Difficulty.EASY);
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setDifficultyMiddle_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "medium");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: MEDIUM)";

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty(Difficulty.MEDIUM);
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith(Difficulty.MEDIUM);
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setDifficultyHigh_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "hard");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: HARD)";

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty(Difficulty.HARD);
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith(Difficulty.HARD);
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setDifficultyInvalid_failure() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "middle");

        String expectedMessage = "middle is not a valid difficult level! Please enter easy, medium or hard!";
        assertCommandFailure(setDifficultyCommand, model, expectedMessage);
    }

    @Test
    public void execute_setDifficultyInvalidIndex_failure() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(999), "easy");

        assertCommandFailure(setDifficultyCommand, model, "The card index provided is invalid");
    }

    @Test
    public void execute_withValidRandomIndex_success() {
        // to make it is valid, make sure random index has been set in model.
        Model modelToUse = new ModelManager(model.getDeck(), new UserPrefs());
        modelToUse.setRandomIndex(Index.fromOneBased(1));

        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.RANDOM, "easy");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: EASY)";

        Model expectedModel = new ModelManager(new Deck(modelToUse.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty(Difficulty.EASY);
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith(Difficulty.EASY);
        expectedModel.getDeck().sort();
        assertCommandSuccess(setDifficultyCommand, modelToUse, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withInvalidRandomIndex_failure() {
        // attempt to run set difficulty without running random first (i.e. random index not set)
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.RANDOM, "easy");

        assertCommandFailure(setDifficultyCommand, model, Messages.MESSAGE_RANDOM_INDEX_NOT_INITIALISED);
    }

    @Test
    public void execute_withNoIndex_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "easy");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: EASY)";

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty(Difficulty.EASY);
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith(Difficulty.EASY);
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withLastIndex_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(4), "hard");

        String expectedMessage = "Set Difficulty for Question 5 (Difficulty level: HARD)";

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(4).setDifficulty(Difficulty.HARD);
        expectedModel.getDeck().getCardList().get(4).setNewPracticeDateWith(Difficulty.HARD);
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(INDEX_FIRST_CARD, "easy");
        SetDifficultyCommand otherSetDifficultyCommand = new SetDifficultyCommand(INDEX_SECOND_CARD, "hard");

        // same object -> returns true
        assertTrue(setDifficultyCommand.equals(setDifficultyCommand));

        // same values -> returns true
        SetDifficultyCommand deleteFirstCommandCopy = new SetDifficultyCommand(INDEX_FIRST_CARD, "easy");
        assertTrue(setDifficultyCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(setDifficultyCommand.equals(1));

        // null -> returns false
        assertFalse(setDifficultyCommand.equals(null));

        // different Card -> returns false
        assertFalse(setDifficultyCommand.equals(otherSetDifficultyCommand));
    }

}
