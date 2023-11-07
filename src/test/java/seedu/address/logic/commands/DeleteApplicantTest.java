package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithMembersApplicants;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Applicant;


/**
 * Contains integration tests (interaction with the Model) and unit tests for * {@code DeleteMemberCommand}.
 */
public class DeleteApplicantTest {
    private Model model = new ModelManager(getTypicalAddressBookWithMembersApplicants(), new UserPrefs());

    /**
    * Test case to verify the successful execution of a delete operation on a valid index
    * in an unfiltered list of applicants. The test checks whether the operation produces the
    * expected results.
    */
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteApplicantCommand deleteCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteApplicantCommand.MESSAGE_DELETE_APPLICANT_SUCCESS,
                Messages.format(applicantToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    /**
     * Test case to verify that executing a delete operation with an invalid index in an
     * unfiltered list of applicants results in a {@link CommandException}. The test checks
     * whether the operation correctly throws the expected exception.
     *
     */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(outOfBoundIndex);

        assertCommandFailure(deleteApplicantCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    /**
     * Test case to verify the successful execution of a delete operation on a valid index
     * within a filtered list of members. The test checks whether the operation produces the
     * expected results.
     */
    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteApplicantCommand deleteCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteApplicantCommand.MESSAGE_DELETE_APPLICANT_SUCCESS,
                Messages.format(applicantToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);
        showNoApplicant(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplicant(Model model) {
        model.updateFilteredApplicantList(p -> false);

        assertTrue(model.getFilteredApplicantList().isEmpty());
    }

    /**
     * Test case to verify that executing a delete operation with an invalid index in a
     * filtered list of applicants results in a {@link CommandException}. The test checks
     * whether the operation correctly throws the expected exception.
     */

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicantList().size());

        DeleteApplicantCommand deleteCommand = new DeleteApplicantCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteApplicantCommand deleteFirstCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);
        DeleteApplicantCommand deleteSecondCommand = new DeleteApplicantCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values(index) -> returns true
        DeleteApplicantCommand deleteFirstCommandCopy = new DeleteApplicantCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        //different types(index vs int) -> returns false;
        assertFalse(deleteSecondCommand.equals(2));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different applicant(index) -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index applicantIndex = Index.fromOneBased(2);
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(applicantIndex);
        String expected = DeleteApplicantCommand.class.getCanonicalName() + "{applicantIndex=" + applicantIndex + "}";
        assertEquals(expected, deleteApplicantCommand.toString());
    }

}
