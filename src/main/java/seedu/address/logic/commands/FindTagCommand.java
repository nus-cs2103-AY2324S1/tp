package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsTagsPredicate;
import seedu.address.model.task.TaskContainsTagsPredicate;

/**
 * Finds and lists all persons and tasks in address book whose tag contains any of the argument keywords.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findTag";
    public static final String SHORTENED_COMMAND_WORD = "ftag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (alias: " + SHORTENED_COMMAND_WORD + ")"
            + ": Finds all persons and tasks whose tag contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " agenda";
    private final PersonContainsTagsPredicate personPredicate;
    private final TaskContainsTagsPredicate taskPredicate;

    /**
     * Creates a FindTagCommand to find the specified {@code TaskContainsTagsPredicate} and
     * {@code PersonContainsTagsPredicate}
     */
    public FindTagCommand(PersonContainsTagsPredicate personPredicate, TaskContainsTagsPredicate taskPredicate) {
        this.taskPredicate = taskPredicate;
        this.personPredicate = personPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(taskPredicate);
        model.updateFilteredPersonList(personPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(), model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTagCommand)) {
            return false;
        }

        FindTagCommand otherFindTagCommand = (FindTagCommand) other;
        return taskPredicate.equals(otherFindTagCommand.taskPredicate)
                && personPredicate.equals(otherFindTagCommand.personPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personPredicate", personPredicate)
                .add("taskPredicate", taskPredicate)
                .toString();
    }
}
