package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.CompletedPrescriptions.getCompletedPrescriptionList;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ListConflictsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPrescriptionList(), getCompletedPrescriptionList(), new UserPrefs());
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
    }

    // EP: Index > size of prescription list
    @Test
    public void execute_listConflicts_failure() {
        Index invalidIndex = Index.fromZeroBased(model.getPrescriptionList().getPrescriptionList().size());
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX, () -> new ListConflictsCommand(invalidIndex)
                        .execute(model));
    }

    // EP: Has conflicting drugs in prescription at an index
    @Test
    public void execute_listConflicts_success() {
        String expectedSuccessMessage = ListConflictsCommand.MESSAGE_SUCCESS + "\nIbuprofen\n";
        assertCommandSuccess(new ListConflictsCommand(Index.fromOneBased(1)), model,
                expectedSuccessMessage, expectedModel);
    }

    // EP: No conflicting drugs in current prescriptions
    @Test
    public void execute_listConflicts_noConflictsSuccess() {
        // Only the first prescription has a conflict in our test data
        model.deletePrescription(model.getFilteredPrescriptionList().get(0));
        expectedModel.deletePrescription(expectedModel.getFilteredPrescriptionList().get(0));
        assertCommandSuccess(new ListAllConflictsCommand(), model,
                ListAllConflictsCommand.MESSAGE_EMPTY_LIST, expectedModel);
    }

    @Test
    public void equals() {
        ListConflictsCommand listConflictsCommand = new ListConflictsCommand(Index.fromOneBased(1));
        ListConflictsCommand listConflictsCommandCopy = new ListConflictsCommand(Index.fromOneBased(1));
        ListConflictsCommand listConflictsCommandDifferentIndex = new ListConflictsCommand(Index.fromOneBased(2));

        // same object -> returns true
        assert(listConflictsCommand.equals(listConflictsCommand));
        // same index -> returns true
        assert(listConflictsCommand.equals(listConflictsCommandCopy));
        // different index -> returns false
        assert(!listConflictsCommand.equals(listConflictsCommandDifferentIndex));
        // different types -> returns false
        assert(!listConflictsCommand.equals(1));
        // null -> returns false
        assert(!listConflictsCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ListConflictsCommand listConflictsCommand = new ListConflictsCommand(targetIndex);
        String expected = ListConflictsCommand.class.getCanonicalName() + "{toListConflicts=" + targetIndex + "}";
        assertEquals(expected, listConflictsCommand.toString());
    }
}
