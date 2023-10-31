package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Task;

/**
 * Adds a task to a lesson in the schedule.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";
    private Task task;
    private int index;
    /**
     * Creates an AddTaskCommand to add the specified {@code Task} to {@code Lesson}
     */
    public AddTaskCommand(int index, Task task) {
        requireNonNull(index);
        requireNonNull(task);
        this.task = task;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            if (model.hasTaskClashWith(task, index)) {
                String taskIndex = model.getTaskClashWith(task, index).toString();
                throw new CommandException("Existing task with same task description with index " + taskIndex + "!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Lesson index out of bounds!");
        }
        model.addTask(task, index);
        return new CommandResult(String.format("New task added to lesson " + index + ": " + task.toString()));
    }

}
