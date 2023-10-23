package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


<<<<<<< HEAD:src/test/java/seedu/address/logic/commands/SetDifficultyCommandTest.java

public class SetDifficultyCommandTest {
=======
public class SolveCommandTest {
>>>>>>> 5c76934b9f0e873ad8373ff60e5dc23f13c1c6e6:src/test/java/seedu/address/logic/commands/SolveCommandTest.java

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
}
