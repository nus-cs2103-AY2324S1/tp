package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Task;


/**
 * Adds a task to the task list of an animal.
 */

public class AddTaskCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a task to the animal identified by the index number used in the displayed animal list.\n"
            + "Parameters: ANIMALINDEX (must be a positive integer) TASK";

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + " 1 Feed";

    public static final String MESSAGE_SUCCESS = "New task added for %s: %s";

    private final Index targetIndex;

    private final Task task;

    /**
     * @param targetIndex of the animal in the filtered animal list to edit
     * @param task to be added to the animal task list
     */
    public AddTaskCommand(Index targetIndex, Task task) {
        requireNonNull(targetIndex);
        requireNonNull(task);

        this.targetIndex = targetIndex;
        this.task = task;
    }

    /**
     * Returns the help message of this command.
     *
     * @return a string containing the help message of this command.
     */
    public static String getHelp() {
        return String.format(AnimalMessages.MESSAGE_HELP_FORMAT, MESSAGE_USAGE, EXAMPLE_USAGE);
    }

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        requireNonNull(model);
        List<Animal> lastShownList = model.getFilteredAnimalList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AnimalMessages.MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX);
        }

        Animal animalToEdit = lastShownList.get(targetIndex.getZeroBased());
        Animal editedAnimal = model.addTask(task, animalToEdit);

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getName(editedAnimal),
                AnimalMessages.format(task)), editedAnimal);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && targetIndex.equals(((AddTaskCommand) other).targetIndex)
                && task.equals(((AddTaskCommand) other).task));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("task", task)
                .toString();
    }
}

