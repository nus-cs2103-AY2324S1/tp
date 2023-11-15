package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.CompletedPrescriptions.getCompletedPrescriptionList;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the seedu.address.model.Model)
 * and unit tests for ListAllConflictsCommand.
 */
public class ListAllConflictsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPrescriptionList(), getCompletedPrescriptionList(), new UserPrefs());
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
    }

    // EP: Has conflicting drugs in current prescriptions
    @Test
    public void execute_listAllConflicts_success() {
        String expectedSuccessMessage = ListAllConflictsCommand.MESSAGE_SUCCESS + "\nIbuprofen\n";
        assertCommandSuccess(new ListAllConflictsCommand(), model,
                expectedSuccessMessage, expectedModel);
    }

    // EP: No conflicting drugs in current prescriptions
    @Test
    public void execute_listAllConflicts_noConflictsSuccess() {
        // Only the first prescription has a conflict in our test data
        model.deletePrescription(model.getFilteredPrescriptionList().get(0));
        expectedModel.deletePrescription(expectedModel.getFilteredPrescriptionList().get(0));
        assertCommandSuccess(new ListAllConflictsCommand(), model,
                ListAllConflictsCommand.MESSAGE_EMPTY_LIST, expectedModel);
    }

    @Test
    public void equals() {
        ListAllConflictsCommand listAllConflictsCommand = new ListAllConflictsCommand();
        ListAllConflictsCommand listAllConflictsCommandCopy = new ListAllConflictsCommand();

        // same object -> returns true
        assert(listAllConflictsCommand.equals(listAllConflictsCommand));

        // same values -> returns true
        assert(listAllConflictsCommand.equals(listAllConflictsCommandCopy));

        // different types -> returns false
        assert(!listAllConflictsCommand.equals(1));

        // null -> returns false
        assert(!listAllConflictsCommand.equals(null));
    }
}
