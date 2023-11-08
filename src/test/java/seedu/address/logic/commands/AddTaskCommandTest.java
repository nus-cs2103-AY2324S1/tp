package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.ScheduleList;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.testutil.ModelStub;

class AddTaskCommandTest {

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new Task("Valid task");
        Index lessonIndex = Index.fromOneBased(1);

        CommandResult commandResult = new AddTaskCommand(lessonIndex, validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, lessonIndex.getOneBased(), validTask.toString()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }
    @Test
    public void execute_taskAcceptedToCurrentLesson_addSuccessful() throws Exception {
        Lesson lessonToAddTask = Lesson.getDefaultLesson();
        ModelStubAcceptingTaskToCurrentLesson modelStub = new ModelStubAcceptingTaskToCurrentLesson(lessonToAddTask);
        Task validTask = new Task("Valid task");
        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);
        Lesson editedLesson = Lesson.getDefaultLesson();
        editedLesson.addToTaskList(validTask);

        assertEquals(String.format(AddTaskCommand.TASK_ADDED_TO_CURRENT_LESSON, validTask.toString()),
                commandResult.getFeedbackToUser());
        assertEquals(editedLesson, modelStub.editedLesson);
    }
    @Test
    public void execute_duplicateTask_throwsCommandException() throws CommandException {
        Task validTask = new Task("Valid Task");
        Index lessonIndex = Index.fromOneBased(1);
        AddTaskCommand addTaskCommand = new AddTaskCommand(lessonIndex, validTask);
        ModelStub modelStub = new ModelStubWithTask(validTask);
        assertThrows(CommandException.class,
                String.format(AddTaskCommand.DUPLICATE_TASK, 1), () -> addTaskCommand.execute(modelStub));
    }
    @Test
    public void equals() {
        Task task1 = new Task("Valid task 1");
        Task task2 = new Task("Valid task 2");
        AddTaskCommand addTask1Command = new AddTaskCommand(task1);
        AddTaskCommand addTask2Command = new AddTaskCommand(task2);
        // same object -> returns true
        assertTrue(addTask1Command.equals(addTask1Command));

        // same values -> returns true
        AddTaskCommand addTask1CommandCopy = new AddTaskCommand(task1);
        assertTrue(addTask1Command.equals(addTask1CommandCopy));

        // different types -> returns false
        assertFalse(addTask1Command.equals(1));

        // null -> returns false
        assertFalse(addTask1Command.equals(null));

        // different person -> returns false
        assertFalse(addTask1Command.equals(addTask2Command));
    }

    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTaskClashWith(Task task, int index) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }

        @Override
        public int getTaskClashWith(Task task, int index) {
            if (hasTaskClashWith(task, 1)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public void addTask(Task task, int index) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public boolean hasTaskClashWith(Task task, int index) {
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ReadOnlySchedule getScheduleList() {
            return new seedu.address.model.ScheduleList();
        }

        @Override
        public void showLesson(Lesson lessonToShow) {
        }

        @Override
        public void setLesson(Lesson target, Lesson editedLesson) {
        }

        @Override
        public ObservableList<Lesson> getFilteredScheduleList() {
            ScheduleList scheduleList = new ScheduleList(getScheduleList());
            scheduleList.addLesson(Lesson.getDefaultLesson());
            return scheduleList.getLessonList();
        }
    }
    private class ModelStubAcceptingTaskToCurrentLesson extends ModelStub {
        private final Lesson lesson;
        private Lesson editedLesson;
        ModelStubAcceptingTaskToCurrentLesson(Lesson lesson) {
            this.lesson = lesson;
        }
        @Override
        public Lesson getCurrentlyDisplayedLesson() {
            return this.lesson;
        }
        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ReadOnlySchedule getScheduleList() {
            return new seedu.address.model.ScheduleList();
        }

        @Override
        public void showLesson(Lesson lessonToShow) {
        }

        @Override
        public void setLesson(Lesson target, Lesson editedLesson) {
            this.editedLesson = editedLesson;
        }

        @Override
        public ObservableList<Lesson> getFilteredScheduleList() {
            ScheduleList scheduleList = new ScheduleList(getScheduleList());
            scheduleList.addLesson(Lesson.getDefaultLesson());
            return scheduleList.getLessonList();
        }
    }
}
