package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



public class SetDifficultyCommandTest {



    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_setLowestPriority_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "easy");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: easy)";


        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty("easy");
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith("easy");
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setDifficultyMiddle_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "medium");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: medium)";


        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty("medium");
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith("medium");
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setDifficultyHigh_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "hard");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: hard)";


        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty("hard");
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith("hard");
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withValidRandomIndex_success() {
        // to make it is valid, make sure random index has been set in model.
        Model modelToUse = new ModelManager(model.getDeck(), new UserPrefs());
        modelToUse.setRandomIndex(Index.fromOneBased(1));

        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.RANDOM, "easy");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: easy)";

        Model expectedModel = new ModelManager(new Deck(modelToUse.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty("easy");
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith("easy");
        expectedModel.getDeck().sort();
        assertCommandSuccess(setDifficultyCommand, modelToUse, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withInvalidRandomIndex_failure() {
        // attempt to run set difficulty without running random first (i.e. random index not set)
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.RANDOM, "easy");

        assertCommandFailure(setDifficultyCommand, model, Messages.MESSAGE_RANDOM_INDEX_NOT_INITIALISED);
    }
}
