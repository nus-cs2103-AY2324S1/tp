package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_DONE_TASKS;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.Status;

/**
 * Finds and lists all task in address book whose status is {@link Status#STATUS_DONE}.
 */
public class FindDoneCommand extends Command {

    public static final String COMMAND_WORD = "findDone";
    public static final String SHORTENED_COMMAND_WORD = "fd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (alias: " + SHORTENED_COMMAND_WORD + ")"
            + ": Finds all tasks whose status is marked as "
            + "done and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    public FindDoneCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_DONE_TASKS);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }
}
