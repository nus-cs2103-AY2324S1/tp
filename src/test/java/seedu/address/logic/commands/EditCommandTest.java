package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TestData.IndexContact.FIRST_CONTACT;
import static seedu.address.testutil.TestData.IndexContact.SECOND_CONTACT;
import static seedu.address.testutil.TestData.Valid.Contact.getTypicalContacts;
import static seedu.address.testutil.TestData.Valid.NAME_BOB;
import static seedu.address.testutil.TestData.Valid.PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.model.Contacts;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Settings;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.TestData;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalContacts(), new Settings());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        EditCommand editCommand = new EditCommand(FIRST_CONTACT, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_COMMAND_SUCCESS, Contact.format(editedContact));

        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());
        expectedModel.updateContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastContact = Index.fromOneBased(model.getFilteredContactList().size());
        Contact lastContact = model.getFilteredContactList().get(indexLastContact.getZeroBased());

        ContactBuilder contactInList = new ContactBuilder(lastContact);
        Contact editedContact = contactInList.withName(NAME_BOB).withPhone(PHONE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES).build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(NAME_BOB)
                .withPhone(PHONE_BOB).withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES).build();
        EditCommand editCommand = new EditCommand(indexLastContact, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_COMMAND_SUCCESS, Contact.format(editedContact));

        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());
        expectedModel.updateContact(lastContact, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(FIRST_CONTACT, new EditContactDescriptor());
        Contact editedContact = model.getFilteredContactList().get(FIRST_CONTACT.getZeroBased());

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_COMMAND_SUCCESS, Contact.format(editedContact));

        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showContactAtIndex(model, FIRST_CONTACT);

        Contact contactInFilteredList = model.getFilteredContactList().get(FIRST_CONTACT.getZeroBased());
        Contact editedContact = new ContactBuilder(contactInFilteredList).withName(NAME_BOB).build();
        EditCommand editCommand = new EditCommand(FIRST_CONTACT,
                new EditContactDescriptorBuilder().withName(NAME_BOB).build());

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_COMMAND_SUCCESS, Contact.format(editedContact));

        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());
        expectedModel.updateContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateContactUnfilteredList_failure() {
        Contact firstContact = model.getFilteredContactList().get(FIRST_CONTACT.getZeroBased());
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(firstContact).build();
        EditCommand editCommand = new EditCommand(SECOND_CONTACT, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_COMMAND_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_duplicateContactFilteredList_failure() {
        showContactAtIndex(model, FIRST_CONTACT);

        // edit contact in filtered list into a duplicate
        Contact contactInList = model.getContacts().getUnmodifiableList().get(SECOND_CONTACT.getZeroBased());
        EditCommand editCommand = new EditCommand(FIRST_CONTACT,
                new EditContactDescriptorBuilder(contactInList).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_COMMAND_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidContactIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of Contacts
     */
    @Test
    public void execute_invalidContactIndexFilteredList_failure() {
        showContactAtIndex(model, FIRST_CONTACT);
        Index outOfBoundIndex = SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of Contacts
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContacts().getUnmodifiableList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditContactDescriptorBuilder().withName(NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(FIRST_CONTACT, TestData.Valid.EditDescriptor.AMY);

        // same values -> returns true
        EditContactDescriptor copyDescriptor = new EditContactDescriptor(TestData.Valid.EditDescriptor.AMY);
        EditCommand commandWithSameValues = new EditCommand(FIRST_CONTACT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(SECOND_CONTACT, TestData.Valid.EditDescriptor.AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(FIRST_CONTACT, TestData.Valid.EditDescriptor.BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditContactDescriptor editContactDescriptor = new EditContactDescriptor();
        EditCommand editCommand = new EditCommand(index, editContactDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editContactDescriptor="
                + editContactDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }
}
