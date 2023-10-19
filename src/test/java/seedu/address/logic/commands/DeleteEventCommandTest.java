package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.EventBuilder.DEFAULT_END_TIME_STRING;
import static seedu.address.testutil.EventBuilder.DEFAULT_START_TIME_STRING;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;


class DeleteEventCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteEventCommand(null));
    }

    @Test
    public void constructor_eventDeletedFromModel_deleteSuccessful() throws Exception {
        Event validEvent = new EventBuilder().build();
        DeleteEventCommandTest.ModelStubWithEvent modelStub = new DeleteEventCommandTest.ModelStubWithEvent(validEvent);
        LocalDateTime eventTime = LocalDateTime.parse(DEFAULT_START_TIME_STRING, DATE_TIME_STRING_FORMATTER);
        CommandResult commandResult = new DeleteEventCommand(eventTime).execute(modelStub);
        assertEquals(String.format(DeleteEventCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_noEvent_throwsCommandException() {
        LocalDateTime eventTime = LocalDateTime.parse(DEFAULT_END_TIME_STRING, DATE_TIME_STRING_FORMATTER);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(eventTime);
        DeleteEventCommandTest.ModelStub modelStub = new DeleteEventCommandTest.ModelStubWithNoEvent();

        assertThrows(CommandException.class, DeleteEventCommand.MESSAGE_NO_EVENT, () -> deleteEventCommand
                .execute(modelStub));
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canAddEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEventAt(LocalDateTime dateTime) throws EventNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override

        public Event findEventAt(LocalDateTime dateTime) throws EventNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void sortPersonList(Comparator<Person> personComparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithNoEvent extends DeleteEventCommandTest.ModelStub {

        @Override
        public Event findEventAt(LocalDateTime dateTime) {
            throw new EventNotFoundException();
        }
    }

    private class ModelStubWithEvent extends DeleteEventCommandTest.ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public Event findEventAt(LocalDateTime dateTime) {
            return event;
        }

        @Override
        public void deleteEventAt(LocalDateTime dateTime) {
            return;
        }
    }
}
