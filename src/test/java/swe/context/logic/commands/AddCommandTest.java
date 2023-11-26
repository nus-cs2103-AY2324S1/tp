package swe.context.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.testutil.Assert.assertThrows;
import static swe.context.testutil.TestData.Valid.Contact.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import swe.context.commons.core.GuiSettings;
import swe.context.logic.Messages;
import swe.context.logic.commands.exceptions.CommandException;
import swe.context.model.Contacts;
import swe.context.model.Model;
import swe.context.model.ReadOnlyContacts;
import swe.context.model.Settings;
import swe.context.model.contact.Contact;
import swe.context.testutil.ContactBuilder;

public class AddCommandTest {
    @Test
    public void constructor_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_contactAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingContactAdded modelStub = new ModelStubAcceptingContactAdded();
        Contact validContact = new ContactBuilder().build();

        CommandResult commandResult = new AddCommand(validContact).execute(modelStub);

        assertEquals(Messages.addCommandSuccess(Contact.format(validContact)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validContact), modelStub.contactsAdded);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact validContact = new ContactBuilder().build();
        AddCommand addCommand = new AddCommand(validContact);
        ModelStub modelStub = new ModelStubWithContact(validContact);

        assertThrows(CommandException.class,
                Messages.COMMAND_DUPLICATE_CONTACT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Contact alice = new ContactBuilder().withName("Alice").build();
        Contact bob = new ContactBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different contact -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * Default Stub of {@link Model} whose methods all throw an
     * {@link AssertionError}.
     */
    private class ModelStub implements Model {
        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyContacts getContacts() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean containsContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContactsFilter(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeAllContacts() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Settings getSettings() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single contact.
     */
    private class ModelStubWithContact extends ModelStub {
        private final Contact contact;

        ModelStubWithContact(Contact contact) {
            requireNonNull(contact);
            this.contact = contact;
        }

        @Override
        public boolean containsContact(Contact contact) {
            requireNonNull(contact);
            return this.contact.isSameContact(contact);
        }
    }

    /**
     * A Model stub that always accept the contact being added.
     */
    private class ModelStubAcceptingContactAdded extends ModelStub {
        final ArrayList<Contact> contactsAdded = new ArrayList<>();

        @Override
        public boolean containsContact(Contact contact) {
            requireNonNull(contact);
            return contactsAdded.stream().anyMatch(contact::isSameContact);
        }

        @Override
        public void addContact(Contact contact) {
            requireNonNull(contact);
            contactsAdded.add(contact);
        }

        @Override
        public ReadOnlyContacts getContacts() {
            return new Contacts();
        }
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Contact validContact = new ContactBuilder().build();
        AddCommand addCommand = new AddCommand(validContact);
        assertThrows(NullPointerException.class, () -> addCommand.execute(null));
    }

    @Test
    public void execute_contactWithMultipleTags_addSuccessful() throws Exception {
        ModelStubAcceptingContactAdded modelStub = new ModelStubAcceptingContactAdded();
        Contact multipleTagsContact = new ContactBuilder()
            .withTags("tag1", "tag2", "tag3")
            .build();

        CommandResult commandResult = new AddCommand(multipleTagsContact).execute(modelStub);

        assertEquals(Messages.addCommandSuccess(Contact.format(multipleTagsContact)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(multipleTagsContact), modelStub.contactsAdded);
    }

    @Test
    public void execute_contactWithInvalidPhoneFormat_throwsCommandException() {
        Contact invalidPhoneContact = new ContactBuilder().withPhone("invalidPhone").build();
        AddCommand addCommand = new AddCommand(invalidPhoneContact);
        ModelStub modelStub = new ModelStubWithContact(invalidPhoneContact);

        assertThrows(CommandException.class, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_contactWithEmptyOptionalFields_addSuccessful() throws Exception {
        ModelStubAcceptingContactAdded modelStub = new ModelStubAcceptingContactAdded();
        Contact emptyOptionalFieldsContact = new ContactBuilder()
            .withNote("")
            .withTags()
            .withAlternateContacts()
            .build();

        CommandResult commandResult = new AddCommand(emptyOptionalFieldsContact).execute(modelStub);

        assertEquals(Messages.addCommandSuccess(Contact.format(emptyOptionalFieldsContact)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(emptyOptionalFieldsContact), modelStub.contactsAdded);
    }

    @Test
    public void execute_contactWithMalformedTags_throwsCommandException() {
        Contact malformedTagsContact = new ContactBuilder()
            .withTags("Invalid Tag1", "123")
            .build();
        AddCommand addCommand = new AddCommand(malformedTagsContact);
        ModelStub modelStub = new ModelStubWithContact(malformedTagsContact);

        assertThrows(CommandException.class, () -> addCommand.execute(modelStub));
    }
}
