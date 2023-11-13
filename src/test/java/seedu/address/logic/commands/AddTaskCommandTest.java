package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_LATER;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void constructor_taskAcceptedByModel_addSuccessful() throws CommandException {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);
        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, Messages.format(validTask)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_conflictingEvent_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        AddTaskCommandTest.ModelStub modelStub = new AddTaskCommandTest.ModelStubWithTask(validTask);

        assertThrows(CommandException.class, String.format(AddTaskCommand.MESSAGE_DUPLICATE_TASK,
                Messages.format(validTask)), () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void equalsTest() {
        Task validTask = new TaskBuilder().build();
        Task otherValidTask = new TaskBuilder().withDeadline(VALID_END_DATE_LATER).build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        AddTaskCommand notEqualAddTaskCommand = new AddTaskCommand(otherValidTask);
        Object nonAddTaskCommandObject = new Object();

        assertTrue(addTaskCommand.equals(addTaskCommand));

        assertFalse(addTaskCommand.equals(notEqualAddTaskCommand));

        assertFalse(addTaskCommand.equals(nonAddTaskCommandObject));
    }

    @Test
    void toStringMethod() {
        Task toAdd = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(toAdd);
        String expected = AddTaskCommand.class.getCanonicalName() + "{taskToAdd=" + toAdd + "}";
        assertEquals(expected, addTaskCommand.toString());
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
        public Path getCalendarFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCalendarFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskManagerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskManagerFilePath(Path addressBookFilePath) {
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
        public void setCalendar(ReadOnlyCalendar newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCalendar getCalendar() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getCurrentWeekEventList() {
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
        public List<Event> eventsInRange(EventPeriod range) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEventsInRange(EventPeriod range) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCalendar getComparisonCalendar() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setComparisonCalendar(ReadOnlyCalendar eventList) {

        }

        @Override
        public ObservableList<Event> getEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task deleteTask(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TaskManager getTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTasksBy(String comparatorType) {
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
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends AddTaskCommandTest.ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public void addTask(Task task) {
            throw new DuplicateTaskException();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTaskAdded extends AddTaskCommandTest.ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<Task>();

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }
    }
}
