package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_DONE_TASKS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes all done tasks in the task list.
 */
public class DeleteAllDoneCommand extends Command {

    public static final String COMMAND_WORD = "deleteAllDone";
    public static final String SHORTENED_COMMAND_WORD = "dad";

    public static final String MESSAGE_DELETE_ALL_DONE_SUCCESS = "Deleted all completed tasks";
    public static final String MESSAGE_NO_DONE_TASKS = "No Done tasks found in task list";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_DONE_TASKS);
        List<Task> lastShownList = model.getFilteredTaskList();
        if (lastShownList.isEmpty()) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            throw new CommandException(MESSAGE_NO_DONE_TASKS);
        }
        model.deleteAllDone();
        return new CommandResult(MESSAGE_DELETE_ALL_DONE_SUCCESS);
    }
}
