package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMemberAtIndex;
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
import seedu.address.model.person.Member;

/**
 * Contains integration tests (interaction with the Model) and unit tests for * {@code DeleteMemberCommand}.
 */
public class DeleteMemberTest {
    private Model model = new ModelManager(getTypicalAddressBookWithMembersApplicants(), new UserPrefs());

    /**
    * Test case to verify the successful execution of a delete operation on a valid index
    * in an unfiltered list of members. The test checks whether the operation produces the
    * expected results.
    */
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Member memberToDelete = model.getFilteredMemberList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteMemberCommand.MESSAGE_DELETE_MEMBER_SUCCESS,
                Messages.format(memberToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteMember(memberToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    /**
     * Test case to verify that executing a delete operation with an invalid index in an
     * unfiltered list of members results in a {@link CommandException}. The test checks
     * whether the operation correctly throws the expected exception.
     *
     */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMemberList().size() + 1);
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(outOfBoundIndex);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    /**
     * Test case to verify the successful execution of a delete operation on a valid index
     * within a filtered list of members. The test checks whether the operation produces the
     * expected results.
     */
    @Test
    public void execute_validIndexFilteredList_success() {
        showMemberAtIndex(model, INDEX_FIRST_PERSON);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Member memberToDelete = model.getFilteredMemberList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteMemberCommand.MESSAGE_DELETE_MEMBER_SUCCESS,
                Messages.format(memberToDelete));

        expectedModel.deleteMember(memberToDelete);
        showNoMember(expectedModel);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoMember(Model model) {
        model.updateFilteredMemberList(p -> false);

        assertTrue(model.getFilteredMemberList().isEmpty());
    }

    /**
     * Test case to verify that executing a delete operation with an invalid index in a
     * filtered list of members results in a {@link CommandException}. The test checks
     * whether the operation correctly throws the expected exception.
     */

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMemberAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMemberList().size());

        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMemberCommand deleteFirstCommand = new DeleteMemberCommand(INDEX_FIRST_PERSON);
        DeleteMemberCommand deleteSecondCommand = new DeleteMemberCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values(index) -> returns true
        DeleteMemberCommand deleteFirstCommandCopy = new DeleteMemberCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        //different types(index vs int) -> returns false;
        assertFalse(deleteSecondCommand.equals(2));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different member(index) -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index memberIndex = Index.fromOneBased(1);
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(memberIndex);
        String expected = DeleteMemberCommand.class.getCanonicalName() + "{memberIndex=" + memberIndex + "}";
        assertEquals(expected, deleteMemberCommand.toString());
    }

}
