package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.sort.enums.SortOrderEnum;
import seedu.address.logic.sort.enums.SortTypeEnum;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTaskWise;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.member.Member;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Note;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.ui.MainWindow;


public class SortCommandTest {
    private static final SortCommand validTaskNameAsc = new SortCommand(SortOrderEnum.ASCENDING,
            SortTypeEnum.TASK_DESCRIPTION);
    private static final SortCommand validTaskNameDes = new SortCommand(SortOrderEnum.DESCENDING,
            SortTypeEnum.TASK_DESCRIPTION);
    private static final SortCommand validPriorityAsc = new SortCommand(SortOrderEnum.ASCENDING,
            SortTypeEnum.PRIORITY);
    private static final SortCommand validPriorityDes = new SortCommand(SortOrderEnum.DESCENDING,
            SortTypeEnum.PRIORITY);
    private static final SortCommand validDeadlineAsc = new SortCommand(SortOrderEnum.ASCENDING,
            SortTypeEnum.DEADLINE);
    private static final SortCommand validDeadlineDes = new SortCommand(SortOrderEnum.DESCENDING,
            SortTypeEnum.DEADLINE);
    private static final SortCommand validStatusAsc = new SortCommand(SortOrderEnum.ASCENDING,
            SortTypeEnum.STATUS);
    private static final SortCommand validStatusDes = new SortCommand(SortOrderEnum.DESCENDING,
            SortTypeEnum.STATUS);
    private static final Task taskOne = new Task(
            new Description("Delete Unused Code"),
            new Status(true),
            new Note("Used Code"),
            Deadline.of(LocalDateTime.of(2023, 1, 1, 18, 0)),
            Priority.HIGH,
            new HashSet<>(List.of(
                    new Member("Andy"),
                    new Member("Colin"),
                    new Member("Dan")
            )));
    private static final Task taskTwo = new Task(
            new Description("Complete OP3 Presentation"),
            new Status(false),
            new Note("Presentation Due"),
            Deadline.of(LocalDateTime.of(2023, 1, 1, 22, 0)),
            Priority.LOW,
            new HashSet<>(List.of(
                    new Member("Jake"),
                    new Member("Jane"),
                    new Member("Judy")
            )));
    private static final Task taskThree = new Task(
            new Description("Test Case Creation"),
            new Status(true),
            new Note("Create Case"),
            Deadline.of(LocalDateTime.of(2023, 1, 1, 20, 0)),
            Priority.MEDIUM,
            new HashSet<>(List.of(
                    new Member("Harry"),
                    new Member("Tan"),
                    new Member("Potter")
            )));

    @Test
    public void constructor_nullTasks_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new SortCommand(null, null));
    }

    @Test
    public void execute_allTasksSetInModel_success() {
        ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertTrue(modelStub.isSorted(List.of(taskOne, taskTwo, taskThree)));
    }

    @Test
    public void execute_sortByTaskNameAscending_success() {
        SortCommandTest.ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertDoesNotThrow(() -> SortCommandTest.validTaskNameAsc.execute(modelStub));
        assertTrue(modelStub.isSorted(List.of(taskTwo, taskOne, taskThree)));
    }

    @Test
    public void execute_sortByTaskNameDescending_success() {
        SortCommandTest.ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertDoesNotThrow(() -> SortCommandTest.validTaskNameDes.execute(modelStub));
        assertTrue(modelStub.isSorted(List.of(taskThree, taskOne, taskTwo)));
    }

    @Test
    public void execute_sortByPriorityAscending_success() {
        SortCommandTest.ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertDoesNotThrow(() -> SortCommandTest.validPriorityAsc.execute(modelStub));
        assertTrue(modelStub.isSorted(List.of(taskTwo, taskThree, taskOne)));
    }

    @Test
    public void execute_sortByPriorityDescending_success() {
        SortCommandTest.ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertDoesNotThrow(() -> SortCommandTest.validPriorityDes.execute(modelStub));
        assertTrue(modelStub.isSorted(List.of(taskOne, taskThree, taskTwo)));
    }

    @Test
    public void execute_sortByDeadlineAscending_success() {
        SortCommandTest.ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertDoesNotThrow(() -> SortCommandTest.validDeadlineAsc.execute(modelStub));
        assertTrue(modelStub.isSorted(List.of(taskOne, taskThree, taskTwo)));
    }

    @Test
    public void execute_sortByDeadlineDescending_success() {
        SortCommandTest.ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertDoesNotThrow(() -> SortCommandTest.validDeadlineDes.execute(modelStub));
        assertTrue(modelStub.isSorted(List.of(taskTwo, taskThree, taskOne)));
    }

    @Test
    public void execute_sortByStatusAscending_success() {
        SortCommandTest.ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertDoesNotThrow(() -> SortCommandTest.validStatusAsc.execute(modelStub));

        assertTrue(modelStub.isSorted(List.of(taskTwo, taskOne, taskThree)));
    }

    @Test
    public void execute_sortByStatusDescending_success() {
        SortCommandTest.ModelStubWithTaskAccess modelStub = new ModelStubWithTaskAccess();
        assertDoesNotThrow(() -> SortCommandTest.validStatusDes.execute(modelStub));
        assertTrue(modelStub.isSorted(List.of(taskOne, taskThree, taskTwo)));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(validTaskNameAsc.equals(validTaskNameAsc));

        // same values -> returns true
        assertTrue(validTaskNameAsc.equals(new SortCommand(SortOrderEnum.ASCENDING, SortTypeEnum.TASK_DESCRIPTION)));

        // different types -> returns false
        assertFalse(validTaskNameAsc.equals(true));

        // null -> returns false
        assertFalse(validTaskNameAsc.equals(null));

        // different task -> returns false
        assertFalse(validDeadlineDes.equals(validPriorityDes));
    }

    @Test
    public void toString_convertToString_success() {
        String expected = SortCommand.class.getCanonicalName() + "{order=" + SortOrderEnum.ASCENDING + ", "
                + "type=" + SortTypeEnum.DEADLINE + "}";
        assertEquals(expected, SortCommandTest.validDeadlineAsc.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
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
        public Path getTaskWiseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskWiseFilePath(Path taskWiseFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskWise(ReadOnlyTaskWise newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskWise getTaskWise() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAllTasks(List<Task> tasks) {
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
        public MainWindow getMainWindow() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMainWindow(MainWindow mainWindow) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private static class ModelStubWithTaskAccess extends SortCommandTest.ModelStub {

        private final List<Task> innerList = new LinkedList<>();

        ModelStubWithTaskAccess() {
            this.innerList.add(taskOne);
            this.innerList.add(taskTwo);
            this.innerList.add(taskThree);
        }

        @Override
        public void addTask(Task task) {
            this.innerList.add(task);
        }

        @Override
        public void setAllTasks(List<Task> tasks) {
            this.innerList.clear();
            this.innerList.addAll(tasks);
        }

        @Override
        public ReadOnlyTaskWise getTaskWise() {
            return new ReadOnlyTaskWise() {
                private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

                @Override
                public ObservableList<Task> getTaskList() {
                    this.uniqueTaskList.add(taskOne);
                    this.uniqueTaskList.add(taskTwo);
                    this.uniqueTaskList.add(taskThree);

                    return this.uniqueTaskList.asUnmodifiableObservableList();
                }
            };
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            UniqueTaskList tl = new UniqueTaskList();
            tl.setTasks(this.innerList);
            return tl.asUnmodifiableObservableList();
        }

        public boolean isSorted(List<Task> expected) {
            return this.innerList.equals(expected);
        }
    }

}
