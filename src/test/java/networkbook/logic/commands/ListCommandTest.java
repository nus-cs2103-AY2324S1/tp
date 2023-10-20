package networkbook.logic.commands;

import static networkbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static networkbook.logic.commands.CommandTestUtil.showPersonAtIndex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import networkbook.model.Model;
import networkbook.model.ModelManager;
import networkbook.model.UserPrefs;
import networkbook.testutil.TypicalIndexes;
import networkbook.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getNetworkBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model,
                String.format(ListCommand.MESSAGE_PERSONS_LISTED_OVERVIEW, 7),
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model,
                String.format(ListCommand.MESSAGE_PERSONS_LISTED_OVERVIEW, 7),
                expectedModel);
    }
}
