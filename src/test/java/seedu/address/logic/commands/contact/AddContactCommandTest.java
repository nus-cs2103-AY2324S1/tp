package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalJobFestGo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.JobFestGo;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyJobFestGo;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.contact.ContactBuilder;

public class AddContactCommandTest {

    private Model model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
    @Test
    public void constructor_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddContactCommand(null));
    }

    @Test
    public void execute_contactAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingContactAdded modelStub = new ModelStubAcceptingContactAdded();
        Contact validContact = new ContactBuilder().build();

        CommandResult commandResult = new AddContactCommand(validContact).execute(modelStub);

        assertEquals(String.format(AddContactCommand.MESSAGE_SUCCESS, Messages.format(validContact)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validContact), modelStub.contactsAdded);
    }
    @Test
    public void addContact_tagNotPresentInJobFestGo_throwsCommandException() {
        //Empty taglist in model
        model.deleteContact(ALICE);
        assertThrows(CommandException.class, "Tag: friends"
                + AddContactCommand.MESSAGE_INVALID_TAG, () -> new AddContactCommand(ALICE).execute(model));

    }

    @Test
    public void addContact_tagPresentInJobFestGo_doesNotThrowException() {
        //Add "friends tag into tag list"
        model.deleteContact(ALICE);
        model.addTag(new Tag("friends"));
        assertDoesNotThrow(() -> new AddContactCommand(ALICE).execute(model));
    }


    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact validContact = new ContactBuilder().build();
        AddContactCommand addCommand = new AddContactCommand(validContact);
        ModelStub modelStub = new ModelStubWithContact(validContact);

        assertThrows(CommandException.class, AddContactCommand.MESSAGE_DUPLICATE_CONTACT, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Contact alice = new ContactBuilder().withName("Alice").build();
        Contact bob = new ContactBuilder().withName("Bob").build();
        AddContactCommand addAliceCommand = new AddContactCommand(alice);
        AddContactCommand addBobCommand = new AddContactCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddContactCommand addAliceCommandCopy = new AddContactCommand(alice);
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
        AddContactCommand addCommand = new AddContactCommand(ALICE);
        String expected = AddContactCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getJobFestGoFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJobFestGoFilePath(Path jobFestGoFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Contact getContact(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJobFestGo(ReadOnlyJobFestGo newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyJobFestGo getJobFestGo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getUnfilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTag(Tag target, Tag editedTag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTagList(Predicate<Tag> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Event getEvent(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isContactLinkedToEvent(Contact contact, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void linkContactToEvent(Contact contact, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unlinkContactFromEvent(Contact contact, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(TaskDescription taskDescription, Name associatedEventName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(TaskDescription taskDescription, Name associatedEventName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task getTask(TaskDescription description, Date date, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markTask(TaskDescription taskDescription, Name associatedEventName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkTask(TaskDescription taskDescription, Name associatedEventName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getUnfilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getTasksDueSoonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Contact> getContactListPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean verifyContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isOnContactsScreen() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isOnEventsScreen() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isOnTagsScreen() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToContactsScreen(boolean isOnContactsScreen) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToEventsScreen(boolean isOnEventsScreen) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToTagsScreen(boolean isOnTagsScreen) {
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
        public boolean hasContact(Contact contact) {
            requireNonNull(contact);
            return this.contact.isSameContact(contact);
        }
    }

    /**
     * A Model stub that always accept the contact being added.
     */
    private class ModelStubAcceptingContactAdded extends ModelStub {
        final ArrayList<Contact> contactsAdded = new ArrayList<>();
        private boolean isOnContactsScreen = false;
        private boolean isOnEventsScreen = false;
        private boolean isOnTagsScreen = false;

        @Override
        public boolean hasContact(Contact contact) {
            requireNonNull(contact);

            return contactsAdded.stream().anyMatch(contact::isSameContact);

        }

        @Override
        public void addContact(Contact contact) {
            requireNonNull(contact);
            contactsAdded.add(contact);
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            requireNonNull(predicate);
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            requireNonNull(predicate);
        }

        @Override
        public boolean isOnContactsScreen() {
            return this.isOnContactsScreen;
        }

        @Override
        public boolean isOnEventsScreen() {
            return this.isOnEventsScreen;
        }

        @Override
        public boolean isOnTagsScreen() {
            return this.isOnTagsScreen;
        }

        @Override
        public void switchToContactsScreen(boolean isOnContactsScreen) {
            this.isOnContactsScreen = isOnContactsScreen;
        }

        @Override
        public void switchToEventsScreen(boolean isOnEventsScreen) {
            this.isOnEventsScreen = isOnEventsScreen;
        }

        @Override
        public void switchToTagsScreen(boolean isOnTagsScreen) {
            this.isOnTagsScreen = isOnTagsScreen;
        }

        @Override
        public ReadOnlyJobFestGo getJobFestGo() {
            return new JobFestGo();
        }
    }
}
