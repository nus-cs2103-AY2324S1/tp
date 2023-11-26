package swe.context.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.logic.commands.CommandTestUtil.assertCommandFailure;
import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swe.context.logic.commands.CommandTestUtil.showContactAtIndex;

import org.junit.jupiter.api.Test;

import swe.context.commons.core.index.Index;
import swe.context.logic.Messages;
import swe.context.logic.commands.EditCommand.EditContactDescriptor;
import swe.context.model.Contacts;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;
import swe.context.model.contact.Contact;
import swe.context.testutil.ContactBuilder;
import swe.context.testutil.EditContactDescriptorBuilder;
import swe.context.testutil.TestData;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TestData.Valid.Contact.getTypicalContacts(), new Settings());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        EditCommand editCommand = new EditCommand(TestData.IndexContact.FIRST_CONTACT, descriptor);

        String expectedMessage = Messages.editCommandSuccess(Contact.format(editedContact));

        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());
        expectedModel.updateContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastContact = Index.fromOneBased(model.getFilteredContactList().size());
        Contact lastContact = model.getFilteredContactList().get(indexLastContact.getZeroBased());

        ContactBuilder contactInList = new ContactBuilder(lastContact);
        Contact editedContact = contactInList.withName(TestData.Valid.NAME_BOB).withPhone(TestData.Valid.PHONE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .withAlternateContacts(TestData.Valid.AlternateContact.ALPHANUMERIC_UNDERSCORE).build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(TestData.Valid.NAME_BOB)
                .withPhone(TestData.Valid.PHONE_BOB).withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .withAlternateContacts(TestData.Valid.AlternateContact.ALPHANUMERIC_UNDERSCORE).build();
        EditCommand editCommand = new EditCommand(indexLastContact, descriptor);

        String expectedMessage = Messages.editCommandSuccess(Contact.format(editedContact));

        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());
        expectedModel.updateContact(lastContact, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(TestData.IndexContact.FIRST_CONTACT, new EditContactDescriptor());
        Contact editedContact = model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());

        String expectedMessage = Messages.editCommandSuccess(Contact.format(editedContact));

        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showContactAtIndex(model, TestData.IndexContact.FIRST_CONTACT);

        Contact contactInFilteredList =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        Contact editedContact = new ContactBuilder(contactInFilteredList).withName(TestData.Valid.NAME_BOB).build();
        EditCommand editCommand = new EditCommand(TestData.IndexContact.FIRST_CONTACT,
                new EditContactDescriptorBuilder().withName(TestData.Valid.NAME_BOB).build());

        String expectedMessage = Messages.editCommandSuccess(Contact.format(editedContact));

        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());
        expectedModel.updateContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateContactUnfilteredList_failure() {
        Contact firstContact = model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(firstContact).build();
        EditCommand editCommand = new EditCommand(TestData.IndexContact.SECOND_CONTACT, descriptor);

        assertCommandFailure(editCommand, model, Messages.COMMAND_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_duplicateContactFilteredList_failure() {
        showContactAtIndex(model, TestData.IndexContact.FIRST_CONTACT);

        // edit contact in filtered list into a duplicate
        Contact contactInList =
                model.getContacts().getUnmodifiableList().get(TestData.IndexContact.SECOND_CONTACT.getZeroBased());
        EditCommand editCommand = new EditCommand(TestData.IndexContact.FIRST_CONTACT,
                new EditContactDescriptorBuilder(contactInList).build());

        assertCommandFailure(editCommand, model, Messages.COMMAND_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidContactIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(TestData.Valid.NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.INVALID_EDIT_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of Contacts
     */
    @Test
    public void execute_invalidContactIndexFilteredList_failure() {
        showContactAtIndex(model, TestData.IndexContact.FIRST_CONTACT);
        Index outOfBoundIndex = TestData.IndexContact.SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of Contacts
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContacts().getUnmodifiableList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditContactDescriptorBuilder().withName(TestData.Valid.NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.INVALID_EDIT_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand =
                new EditCommand(TestData.IndexContact.FIRST_CONTACT, TestData.Valid.EditDescriptor.AMY);

        // same values -> returns true
        EditContactDescriptor copyDescriptor = new EditContactDescriptor(TestData.Valid.EditDescriptor.AMY);
        EditCommand commandWithSameValues = new EditCommand(TestData.IndexContact.FIRST_CONTACT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(TestData.IndexContact.SECOND_CONTACT, TestData.Valid.EditDescriptor.AMY)
        ));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(TestData.IndexContact.FIRST_CONTACT, TestData.Valid.EditDescriptor.BOB))
        );
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

    @Test
    public void execute_editWithNoChangesUnfilteredList_success() {
        Contact firstContact =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        // Create an EditContactDescriptor using the same values as the first contact,
        // implying no changes should be made when this descriptor is used to edit.
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(firstContact).build();
        EditCommand editCommand = new EditCommand(TestData.IndexContact.FIRST_CONTACT, descriptor);

        // Prepare the expected message to be shown when edit is successful,
        // which should indicate that the contact has been edited (even if it remains the same).
        String expectedMessage = Messages.editCommandSuccess(Contact.format(firstContact));

        assertCommandSuccess(editCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_editWithMixedFieldsUnfilteredList_success() {
        Contact firstContact =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        Contact editedContact = new ContactBuilder(firstContact).withPhone(TestData.Valid.PHONE_BOB).build();
        // Create an EditContactDescriptor that will change the first contact's phone number
        // to BOB's phone number.
        EditContactDescriptor descriptor =
                new EditContactDescriptorBuilder().withPhone(TestData.Valid.PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(TestData.IndexContact.FIRST_CONTACT, descriptor);

        String expectedMessage = Messages.editCommandSuccess(Contact.format(editedContact));

        // Prepare an expected model state to reflect the contact list after the edit has been made.
        Model expectedModel = new ModelManager(new Contacts(model.getContacts()), new Settings());
        // In the expected model, update the first contact to match the edited contact.
        expectedModel.updateContact(firstContact, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

}
