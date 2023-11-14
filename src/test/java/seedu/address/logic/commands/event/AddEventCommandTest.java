package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;

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
import seedu.address.model.ReadOnlyJobFestGo;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.event.EventBuilder;
public class AddEventCommandTest {
    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_DUPLICATE_EVENT, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddEventCommand addFirstEventCommand = new AddEventCommand(NTU);
        AddEventCommand addSecondEventCommand = new AddEventCommand(JOBFEST);

        assertTrue(addFirstEventCommand.equals(addFirstEventCommand));
        assertTrue(addFirstEventCommand.equals(new AddEventCommand(NTU)));
        assertFalse(addSecondEventCommand.equals(addFirstEventCommand));
        assertFalse(addFirstEventCommand.equals(null));
        assertFalse(addFirstEventCommand.equals(""));
        assertFalse(addFirstEventCommand.equals(addSecondEventCommand));
    }

    @Test
    public void toString_testEqualEvents() {
        AddEventCommand addEventCommand = new AddEventCommand(NTU);
        assertEquals(addEventCommand.toString(), addEventCommand.toString());
    }

    @Test
    public void toString_testDifferentEvents() {
        AddEventCommand addFirstEventCommand = new AddEventCommand(NTU);
        AddEventCommand addSecondEventCommand = new AddEventCommand(JOBFEST);
        assertNotEquals(addSecondEventCommand.toString(), addFirstEventCommand.toString());
    }
    /**
     * A default model stub that have all the methods failing.
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
        public ObservableList<Contact> getUnfilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
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
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getUnfilteredEventList() {
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
        public Task getTask(TaskDescription taskDescription, Date date, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
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
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();
        private boolean isOnContactsScreen = false;
        private boolean isOnEventsScreen = false;
        private boolean isOnTagsScreen = false;

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event :: isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
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

