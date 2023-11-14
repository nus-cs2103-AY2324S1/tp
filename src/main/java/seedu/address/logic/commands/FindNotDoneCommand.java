package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_NOT_DONE_TASKS;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.Status;

/**
 * Finds and lists all task in address book whose status is {@link Status#STATUS_NOT_DONE}.
 */
public class FindNotDoneCommand extends Command {

    public static final String COMMAND_WORD = "findNotDone";
    public static final String SHORTENED_COMMAND_WORD = "fnd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (alias: " + SHORTENED_COMMAND_WORD + ")"
            + ": Finds all tasks whose status is marked as "
            + "not done and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    public FindNotDoneCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_NOT_DONE_TASKS);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }
}
