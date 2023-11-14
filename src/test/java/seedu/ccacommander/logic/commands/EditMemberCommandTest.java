package seedu.ccacommander.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.ccacommander.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ccacommander.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_SECOND_MEMBER;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;
import seedu.ccacommander.model.member.Member;
import seedu.ccacommander.model.shared.Name;
import seedu.ccacommander.testutil.EditMemberDescriptorBuilder;
import seedu.ccacommander.testutil.MemberBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMemberCommand.
 */
public class EditMemberCommandTest {

    private Model model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Member editedMember = new MemberBuilder().build();
        EditMemberCommand.EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(editedMember).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_MEMBER, descriptor);

        String commitMessage = String.format(EditMemberCommand.MESSAGE_COMMIT, editedMember.getName());
        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS,
                Messages.format(editedMember));

        Model expectedModel = new ModelManager(new CcaCommander(model.getCcaCommander()), new UserPrefs());
        expectedModel.setMember(model.getFilteredMemberList().get(0), editedMember);

        Name prevName = model.getFilteredMemberList().get(0).getName();
        Name newName = editedMember.getName();

        if (!prevName.equals(newName)) {
            expectedModel.editEnrolmentsWithMemberName(prevName, newName);
        }

        expectedModel.commit(commitMessage);
        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMember = Index.fromOneBased(model.getFilteredMemberList().size());
        Member lastMember = model.getFilteredMemberList().get(indexLastMember.getZeroBased());

        MemberBuilder memberInList = new MemberBuilder(lastMember);
        Member editedMember = memberInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditMemberCommand.EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(indexLastMember, descriptor);

        String commitMessage = String.format(EditMemberCommand.MESSAGE_COMMIT, editedMember.getName());
        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS,
                Messages.format(editedMember));

        Model expectedModel = new ModelManager(new CcaCommander(model.getCcaCommander()), new UserPrefs());
        expectedModel.setMember(lastMember, editedMember);

        Name prevName = lastMember.getName();
        Name newName = editedMember.getName();

        if (!prevName.equals(newName)) {
            expectedModel.editEnrolmentsWithMemberName(prevName, newName);
        }

        expectedModel.commit(commitMessage);
        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_MEMBER,
                                                                    new EditMemberCommand.EditMemberDescriptor());
        Member editedMember = model.getFilteredMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());

        String commitMessage = String.format(EditMemberCommand.MESSAGE_COMMIT, editedMember.getName());
        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS,
                                               Messages.format(editedMember));

        Model expectedModel = new ModelManager(new CcaCommander(model.getCcaCommander()), new UserPrefs());
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);

        Member memberInFilteredList = model.getFilteredMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Member editedMember = new MemberBuilder(memberInFilteredList).withName(VALID_NAME_AMY).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_MEMBER,
                new EditMemberDescriptorBuilder().withName(VALID_NAME_AMY).build());

        String commitMessage = String.format(EditMemberCommand.MESSAGE_COMMIT, editedMember.getName());
        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS,
                                               Messages.format(editedMember));

        Model expectedModel = new ModelManager(new CcaCommander(model.getCcaCommander()), new UserPrefs());
        expectedModel.setMember(model.getFilteredMemberList().get(0), editedMember);
        expectedModel.commit(commitMessage);

        Name prevName = model.getFilteredMemberList().get(0).getName();
        Name newName = editedMember.getName();

        if (!prevName.equals(newName)) {
            expectedModel.editEnrolmentsWithMemberName(prevName, newName);
        }

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMemberUnfilteredList_failure() {
        Member firstMember = model.getFilteredMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        EditMemberCommand.EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(firstMember).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_SECOND_MEMBER, descriptor);

        assertCommandFailure(editMemberCommand, model, EditMemberCommand.MESSAGE_DUPLICATE_MEMBER);
    }

    @Test
    public void execute_duplicateMemberFilteredList_failure() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);

        // edit member in filtered list into a duplicate in CcaCommander
        Member memberInList = model.getCcaCommander().getMemberList().get(INDEX_SECOND_MEMBER.getZeroBased());
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_MEMBER,
                new EditMemberDescriptorBuilder(memberInList).build());

        assertCommandFailure(editMemberCommand, model, EditMemberCommand.MESSAGE_DUPLICATE_MEMBER);
    }

    @Test
    public void execute_invalidMemberIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMemberList().size() + 1);
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of CcaCommander
     */
    @Test
    public void execute_invalidMemberIndexFilteredList_failure() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);
        Index outOfBoundIndex = INDEX_SECOND_MEMBER;
        // ensures that outOfBoundIndex is still in bounds of CcaCommander list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCcaCommander().getMemberList().size());

        EditMemberCommand editMemberCommand = new EditMemberCommand(outOfBoundIndex,
                new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMemberCommand standardCommand = new EditMemberCommand(INDEX_FIRST_MEMBER, DESC_AMY);

        // same values -> returns true
        EditMemberDescriptor copyDescriptor = new EditMemberDescriptor(DESC_AMY);
        EditMemberCommand commandWithSameValues = new EditMemberCommand(INDEX_FIRST_MEMBER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMemberCommand(INDEX_SECOND_MEMBER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMemberCommand(INDEX_FIRST_MEMBER, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditMemberDescriptor editMemberDescriptor = new EditMemberCommand.EditMemberDescriptor();
        EditMemberCommand editMemberCommand = new EditMemberCommand(index, editMemberDescriptor);
        String expected = EditMemberCommand.class.getCanonicalName() + "{index=" + index + ", editMemberDescriptor="
                + editMemberDescriptor + "}";
        assertEquals(expected, editMemberCommand.toString());
    }

}
