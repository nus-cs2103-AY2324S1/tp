package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;

/**
 * Adds a task to a lesson in the schedule.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addtask";
    public static final String MESSAGE_SUCCESS = "New task added to lesson with index %1$d: %2$s";
    public static final String DUPLICATE_TASK = "Existing task with same task description with index %1$d!"
            + getUsageInfo();
    public static final String NO_INDEX = "No lesson with index %1$d!"
            + getUsageInfo();
    public static final String NO_LESSON_SHOWN = "Please use show lesson Index before"
            + " adding task when no lesson is displayed!" + getUsageInfo();
    public static final String TASK_ADDED_TO_CURRENT_LESSON = "New task added to current lesson: %1$s";
    private final Task task;
    private final Integer index;
    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to {@code Lesson}
     */
    public AddTaskCommand(Integer index, Task task) {
        requireNonNull(task);
        this.task = task;
        this.index = index;
    }

    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to the current shown lesson
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        this.task = task;
        this.index = null;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.index == null) {
            return execute_withoutLessonIndex(model);
        }
        int size = model.getFilteredScheduleList().size();
        if (this.index < 1 || this.index > size) {
            throw new CommandException(String.format(NO_INDEX, this.index));
        }
        int lessonIndex = this.index - 1;
        try {
            checkClash(model, lessonIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(String.format(NO_INDEX, this.index));
        }
        model.addTask(task, lessonIndex);
        model.showLesson(model.getFilteredScheduleList().get(lessonIndex));
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.index, task.toString()));
    }

    /**
     * Execute the add task to lesson process when lesson index is not specified.
     * @param model Model used to deal with changes tasks in lessons
     * @return CommandResult with add task successfully message
     * @throws CommandException thrown when no index of lesson is specified or
     *      when there is another task with same description in the task list of
     *      the lesson.
     */
    private CommandResult execute_withoutLessonIndex(Model model) throws CommandException {
        Lesson lesson = model.getCurrentlyDisplayedLesson();
        if (lesson == null) {
            throw new CommandException(NO_LESSON_SHOWN);
        }
        if (lesson.hasSameTask(task)) {
            throw new CommandException(String.format(DUPLICATE_TASK, lesson.getTaskClashWith(task)));
        }
        Lesson editedLesson = lesson.clone();
        editedLesson.addToTaskList(task);
        model.setLesson(lesson, editedLesson);
        model.showLesson(editedLesson);
        return new CommandResult(String.format(TASK_ADDED_TO_CURRENT_LESSON, task.toString()));
    }

    /**
     * Check whether there is any tasks with the same description in the lessons.
     * @param model Model used to deal with changes tasks in lessons
     * @param lessonIndex index of the lesson to add task to
     * @throws CommandException thrown when a task with the same name is detected
     */
    private void checkClash(Model model, int lessonIndex) throws CommandException {
        if (model.hasTaskClashWith(task, lessonIndex)) {
            int taskIndex = model.getTaskClashWith(task, lessonIndex);
            throw new CommandException(String.format(DUPLICATE_TASK, taskIndex));
        }
    }
    public static String getUsageInfo() {
        return "\nUsage: addTask + [lesson index] [description]. "
                + "You could omit the lesson index when adding task to showing lesson."
                + "\nExample1: " + COMMAND_WORD + " 1 do homework"
                + "\nExample2 (a lesson is shown): " + COMMAND_WORD + " do homework"
                + "Please note that there cannot be two tasks with the same description in any lesson.";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        AddTaskCommand otherAddTaskCommand = (AddTaskCommand) other;
        return task.equals(otherAddTaskCommand.task);
    }
    public Integer getIndex() {
        return index;
    }
    public Task getTask() {
        return task;
    }
}
