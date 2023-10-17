package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.staffsnap.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.applicant.Descriptor;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalApplicantBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getApplicantBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortAndList_success() throws CommandException {
        // Execute the SortCommand
        SortCommand sortCommand = new SortCommand(Descriptor.NAME);
        CommandResult sortResult = sortCommand.execute(model);

        // Create a ListCommand
        ListCommand listCommand = new ListCommand();
        CommandResult listResult = listCommand.execute(model);

        // Assert that both commands were successful
        assertEquals(SortCommand.MESSAGE_SUCCESS, sortResult.getFeedbackToUser());
        assertEquals(ListCommand.MESSAGE_SUCCESS, listResult.getFeedbackToUser());

        // Assert that the actual and expected models are the same
        assertEquals(expectedModel, model);
    }
}
