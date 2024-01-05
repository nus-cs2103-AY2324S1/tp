package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Finds and lists all tasks in task list whose respective fields contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewTasksCommand extends Command {

    public static final String COMMAND_WORD = "viewtasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD
            + "[" + PREFIX_TASK_NAME + "cs2101 cs2100] "
            + "[" + PREFIX_TASK_DESCRIPTION + "homework assignment] "
            + "[" + PREFIX_DATE + "22/10/2023] "
            + "[" + PREFIX_TASK_PRIORITY + "high] "
            + "[" + PREFIX_TASK_PROGRESS + "pending] ";

    public static final CommandType COMMAND_TYPE = CommandType.VIEW_TASKS;

    public static final String MESSAGE_MANY_PREFIXES = "Too many prefixes given.";

    private final Predicate<Task> predicate;

    public ViewTasksCommand(Predicate<Task> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()),
                COMMAND_TYPE);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewTasksCommand)) {
            return false;
        }

        ViewTasksCommand otherFindCommand = (ViewTasksCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
