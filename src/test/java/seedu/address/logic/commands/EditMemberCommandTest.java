package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_MEMBER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_MEMBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithMembersApplicants;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Member;
import seedu.address.testutil.EditMemberDescriptorBuilder;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMemberCommand.
 */
public class EditMemberCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithMembersApplicants(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Member editedMember = new MemberBuilder().build();
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(editedMember).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS,
            Messages.format(editedMember));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMember(model.getFilteredMemberList().get(0), editedMember);

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMember = Index.fromOneBased(model.getFilteredMemberList().size());
        Member lastMember = model.getFilteredMemberList().get(indexLastMember.getZeroBased());

        MemberBuilder memberInList = new MemberBuilder(lastMember);
        Member editedMember = memberInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(indexLastMember, descriptor);

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS,
            Messages.format(editedMember));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMember(lastMember, editedMember);

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_PERSON, new EditMemberDescriptor());
        Member editedMember = model.getFilteredMemberList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS,
            Messages.format(editedMember));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMemberAtIndex(model, INDEX_FIRST_PERSON);

        Member memberInFilteredList = model.getFilteredMemberList().get(INDEX_FIRST_PERSON.getZeroBased());
        Member editedMember = new MemberBuilder(memberInFilteredList).withName(VALID_NAME_BOB).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_PERSON,
                new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS,
            Messages.format(editedMember));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMember(model.getFilteredMemberList().get(0), editedMember);

        assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Member firstMember = model.getFilteredMemberList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(firstMember).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editMemberCommand, model, EditMemberCommand.MESSAGE_DUPLICATE_MEMBER);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showMemberAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Member memberInList = model.getAddressBook().getMemberList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_PERSON,
                new EditMemberDescriptorBuilder(memberInList).build());

        assertCommandFailure(editMemberCommand, model, EditMemberCommand.MESSAGE_DUPLICATE_MEMBER);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMemberList().size() + 1);
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showMemberAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMemberList().size());

        EditMemberCommand editMemberCommand = new EditMemberCommand(outOfBoundIndex,
                new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMemberCommand standardCommand = new EditMemberCommand(INDEX_FIRST_PERSON, DESC_AMY_MEMBER);

        // same values -> returns true
        EditMemberDescriptor copyDescriptor = new EditMemberDescriptor(DESC_AMY_MEMBER);
        EditMemberCommand commandWithSameValues = new EditMemberCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMemberCommand(INDEX_SECOND_PERSON, DESC_AMY_MEMBER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMemberCommand(INDEX_FIRST_PERSON, DESC_BOB_MEMBER)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditMemberDescriptor editMemberDescriptor = new EditMemberDescriptor();
        EditMemberCommand editMemberCommand = new EditMemberCommand(index, editMemberDescriptor);
        String expected = EditMemberCommand.class.getCanonicalName() + "{index=" + index + ", editMemberDescriptor="
                + editMemberDescriptor + "}";
        assertEquals(expected, editMemberCommand.toString());
    }

}
