package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Deletes all persons in the contacts list.
 */
public class DeleteAllTaskCommand extends Command {

    public static final String COMMAND_WORD = "deleteAllTask";
    public static final String SHORTENED_COMMAND_WORD = "dat";

    public static final String MESSAGE_DELETE_ALL_TASK_SUCCESS = "Deleted all tasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.deleteAllTask();
        return new CommandResult(MESSAGE_DELETE_ALL_TASK_SUCCESS);
    }
}
