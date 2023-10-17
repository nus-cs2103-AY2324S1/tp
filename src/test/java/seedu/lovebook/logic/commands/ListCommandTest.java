package seedu.lovebook.logic.commands;

import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.lovebook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.lovebook.model.Model;
import seedu.lovebook.model.ModelManager;
import seedu.lovebook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
        expectedModel = new ModelManager(model.getLoveBook(), new UserPrefs(), getTypicalDatePrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
