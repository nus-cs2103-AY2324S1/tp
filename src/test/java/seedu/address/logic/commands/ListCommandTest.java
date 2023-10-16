package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model2;
import seedu.address.model.ModelManager2;
import seedu.address.model.UserPrefs2;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model2 model;
    private Model2 expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager2(getTypicalDeck(), new UserPrefs2());
        expectedModel = new ModelManager2(model.getDeck(), new UserPrefs2());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand2(), model, ListCommand2.MESSAGE_SUCCESS, expectedModel);
    }
}
