package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



/**
 * Contains integration tests (interaction with the Model) and unit tests for SortStallPriceCommand.
 */
public class SortStallPriceCommandTest {
    private Model model;
    private Model expectedSortedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedSortedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedSortedModel.sortStallPrice();
    }

    @Test
    public void execute_listIsNotSortedByPrice_showsSameList() {
        assertCommandSuccess(new SortStallPriceCommand(), model, SortStallPriceCommand.MESSAGE_SUCCESS,
                expectedSortedModel);
    }

    @Test
    public void execute_listIsSortedByPrice_showsEverything() {
        assertCommandSuccess(new SortStallPriceCommand(), expectedSortedModel,
                SortStallPriceCommand.MESSAGE_SUCCESS,
                expectedSortedModel);
    }

}
