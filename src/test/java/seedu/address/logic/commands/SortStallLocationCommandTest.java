package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortStallLocationCommand.
 */
public class SortStallLocationCommandTest {

    private Model model;
    private Model expectedSortedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedSortedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedSortedModel.sortStallLocation();
    }

    @Test
    public void execute_listIsNotSortedByLocation_showsSameList() {
        assertCommandSuccess(new SortStallLocationCommand(), model, SortStallLocationCommand.MESSAGE_SUCCESS,
                expectedSortedModel);
    }

    @Test
    public void execute_listIsSortedLocation_showsEverything() {
        assertCommandSuccess(new SortStallLocationCommand(), expectedSortedModel,
                SortStallLocationCommand.MESSAGE_SUCCESS,
                expectedSortedModel);
    }
}
