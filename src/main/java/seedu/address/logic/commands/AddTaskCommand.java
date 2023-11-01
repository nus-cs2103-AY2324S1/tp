package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;

/**
 * Adds a task to a lesson in the schedule.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";
    private Task task;
    private Index index;
    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to {@code Lesson}
     */
    public AddTaskCommand(Index index, Task task) {
        requireNonNull(index);
        requireNonNull(task);
        this.task = task;
        this.index = index;
    }
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        this.task = task;
        this.index = null;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.index == null) {
            Lesson lesson = model.getCurrentlyDisplayedLesson();
            if (lesson == null) {
                throw new CommandException("Please use show lesson Index before adding task when no lesson is displayed!");
            }
            if (lesson.hasSameTask(task)) {
                throw new CommandException("Existing task with same task description with index "
                        + lesson.getTaskClashWith(task) + "!");
            }
            Lesson editedLesson = lesson.clone();
            editedLesson.addToTaskList(task);
            model.setLesson(lesson, editedLesson);
            model.resetAllShowFields();
            model.showLesson(editedLesson);
            return new CommandResult(String.format("New task added to current lesson: " + task.toString()));
        }
        int lessonIndex = this.index.getZeroBased();
        try {
            if (model.hasTaskClashWith(task, lessonIndex)) {
                String taskIndex = model.getTaskClashWith(task, lessonIndex).toString();
                throw new CommandException("Existing task with same task description with index " + taskIndex + "!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Lesson index out of bounds!");
        }
        model.addTask(task, lessonIndex);
        model.resetAllShowFields();
        model.showLesson(model.getFilteredScheduleList().get(lessonIndex));
        return new CommandResult(String.format("New task added to lesson with index "
                + index.getOneBased() + ": " + task.toString()));
    }

}
