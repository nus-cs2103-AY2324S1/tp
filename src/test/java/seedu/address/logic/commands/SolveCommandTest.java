package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



public class SolveCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_solveAndSetLowestPriority_success() {
        SolveCommand solveCommand = new SolveCommand(Index.fromZeroBased(0), "easy");

        String expectedMessage = "Solved Question 1 (Difficulty level: easy)";


        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty("easy");
        expectedModel.getDeck().getCardList().get(0).setPriority(model.getFilteredCardList().size() - 1);
        expectedModel.getDeck().sort();

        assertCommandSuccess(solveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_solveAndSetMiddlePriority_success() {
        SolveCommand solveCommand = new SolveCommand(Index.fromZeroBased(0), "medium");

        String expectedMessage = "Solved Question 1 (Difficulty level: medium)";


        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty("medium");
        expectedModel.getDeck().getCardList().get(0).setPriority(Math.floorDiv(model.getFilteredCardList().size(), 2));
        expectedModel.getDeck().sort();

        assertCommandSuccess(solveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_solveAndSetHighPriority_success() {
        SolveCommand solveCommand = new SolveCommand(Index.fromZeroBased(0), "hard");

        String expectedMessage = "Solved Question 1 (Difficulty level: hard)";


        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.getDeck().getCardList().get(0).setDifficulty("hard");
        expectedModel.getDeck().getCardList().get(0).setPriority(model.getFilteredCardList().size() - 1);
        expectedModel.getDeck().sort();

        assertCommandSuccess(solveCommand, model, expectedMessage, expectedModel);
    }
}
