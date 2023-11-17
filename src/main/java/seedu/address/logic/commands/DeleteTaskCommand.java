package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;

/**
 * Deletes a specific task from the task list of an animal.
 */

public class DeleteTaskCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "deletetask";

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specific task from an animal's task list.\n"
            + "Parameters: ANIMALINDEX TASKINDEX (must both be positive integers)";

    public static final String MESSAGE_SUCCESS = "Task deleted for %s: %s";

    private final Index targetAnimalIndex;

    private final Index targetTaskIndex;

    /**
     * @param targetAnimalIndex of the animal in the filtered animal list to edit
     * @param targetTaskIndex of the task in the animal task list to delete
     */
    public DeleteTaskCommand(Index targetAnimalIndex, Index targetTaskIndex) {
        requireNonNull(targetAnimalIndex);
        requireNonNull(targetTaskIndex);

        this.targetAnimalIndex = targetAnimalIndex;
        this.targetTaskIndex = targetTaskIndex;
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

        if (targetAnimalIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AnimalMessages.MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX);
        }

        Animal animalToEdit = lastShownList.get(targetAnimalIndex.getZeroBased());

        if (targetTaskIndex.getZeroBased() >= model.getSizeOfTaskList(animalToEdit)) {
            throw new CommandException(AnimalMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Animal editedAnimal = model.deleteTask(animalToEdit, targetTaskIndex);

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getName(editedAnimal),
                AnimalMessages.format(model.getTaskByIndex(animalToEdit, targetTaskIndex))), editedAnimal);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetAnimalIndex.equals(((DeleteTaskCommand) other).targetAnimalIndex)
                && targetTaskIndex.equals(((DeleteTaskCommand) other).targetTaskIndex));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetAnimalIndex)
                .add("task", targetTaskIndex)
                .toString();
    }
}

