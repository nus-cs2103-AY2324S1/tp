package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.CompletedPrescriptions.getCompletedPrescriptionList;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PrescriptionList;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the seedu.address.model.ModelPrescription)
 * and unit tests for ListCompletedCommand.
 */
public class ListCompletedCommandTest {

    private Model model;
    private CommandResult expectedSuccessMessage;
    private CommandResult expectedEmptyMessage;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPrescriptionList(), getCompletedPrescriptionList(), new UserPrefs());
        expectedSuccessMessage = new CommandResult(ListCompletedCommand.MESSAGE_SUCCESS,
                ListCompletedCommand.COMMAND_WORD);
        expectedEmptyMessage = new CommandResult(ListCompletedCommand.MESSAGE_EMPTY_LIST,
                ListCompletedCommand.COMMAND_WORD);
    }

    @Test
    public void execute_listCompletedIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCompletedCommand(), model,
                expectedSuccessMessage, model);
    }

    @Test
    public void execute_listCompletedEmptyList_showsEmptyList() {
        Model emptyModel = new ModelManager(getTypicalPrescriptionList(), new PrescriptionList(), new UserPrefs());
        assertCommandSuccess(new ListCompletedCommand(), emptyModel,
                expectedEmptyMessage, emptyModel);
    }
}
