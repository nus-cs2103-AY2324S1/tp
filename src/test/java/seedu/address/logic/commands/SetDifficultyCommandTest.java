package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Difficulty;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;


public class SetDifficultyCommandTest {

    private final Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_setLowestPriority_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "easy");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: easy)";


        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty(Difficulty.EASY);
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith(Difficulty.EASY);
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setDifficultyMiddle_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "medium");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: medium)";

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty(Difficulty.MEDIUM);
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith(Difficulty.MEDIUM);
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setDifficultyHigh_success() {
        SetDifficultyCommand setDifficultyCommand = new SetDifficultyCommand(Index.fromZeroBased(0), "hard");

        String expectedMessage = "Set Difficulty for Question 1 (Difficulty level: hard)";

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty(Difficulty.HARD);
        expectedModel.getDeck().getCardList().get(0).setNewPracticeDateWith(Difficulty.HARD);
        expectedModel.getDeck().sort();

        assertCommandSuccess(setDifficultyCommand, model, expectedMessage, expectedModel);
    }
}
