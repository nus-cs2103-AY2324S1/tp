package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortStallRatingCommand.
 */
public class SortStallRatingCommandTest {

    private Model model;
    private Model expectedSortedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedSortedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedSortedModel.sortStallRating();
    }

    @Test
    public void execute_listIsNotSortedByRating_showsSameList() {
        assertCommandSuccess(new SortStallRatingCommand(), model, SortStallRatingCommand.MESSAGE_SUCCESS,
                expectedSortedModel);
    }

    @Test
    public void execute_listIsSortedByRating_showsEverything() {
        assertCommandSuccess(new SortStallRatingCommand(), expectedSortedModel,
                SortStallRatingCommand.MESSAGE_SUCCESS,
                expectedSortedModel);
    }
}
