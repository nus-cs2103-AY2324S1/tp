package seedu.address.logic.commands;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.AnimalMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;

/**
 * Marks a task of an animal as done.
 */
public class MarkTaskCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task of the animal, identified by the index number "
            + "used in the displayed animal list, as done. "
            + "\nParameters: ANIMAL_INDEX (must be a positive integer)"
            + "TASK_INDEX (must be a positive integer)";

    public static final String EXAMPLE_USAGE = "Example: "
            + COMMAND_WORD
            + " 1 "
            + "1 2 3";

    public static final String MESSAGE_SUCCESS = "Task(s) marked as done";


    public static final String MESSAGE_EXCESS_TASK_INDEX =
            "The task index(es) provided exceeds the number of tasks in the animal!";

    public static final String MESSAGE_EXCESS_ANIMAL_INDEX = "The animal index provided exceeds the number of animals!";

    private final Index targetIndex;
    private final Index[] taskIndex;

    /**
     * Creates a MarkTaskCommand to mark the specified {@code Task} of the {@code Animal} at the specified
     * {@code Index}.
     */
    public MarkTaskCommand(Index targetIndex, Index... taskIndex) {
        this.targetIndex = targetIndex;
        this.taskIndex = taskIndex;
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

        // check if animal index provided exceeds number of animals
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_EXCESS_ANIMAL_INDEX);
        }

        Animal animalToMark = lastShownList.get(targetIndex.getZeroBased());
        try {
            int[] taskIndexes = stream(taskIndex)
                    .map(Index::getZeroBased)
                    .mapToInt(Integer::intValue)
                    .sorted()
                    .toArray();

            assert taskIndexes.length > 0 : "taskIndexes should not be empty";

            assert taskIndexes[0] >= 0 : "taskIndexes should not be negative";

            // check if index provided exceeds number of tasks
            if (taskIndexes[taskIndex.length - 1] > animalToMark.getNumberOfTasks() - 1) {
                throw new CommandException(MESSAGE_EXCESS_TASK_INDEX);
            }

            Animal updatedAnimal = model.updateTask(animalToMark, taskIndexes, true);

            return new CommandResult(MESSAGE_SUCCESS, updatedAnimal);

        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkTaskCommand)) {
            return false;
        }

        MarkTaskCommand otherCommand = (MarkTaskCommand) other;
        return targetIndex.equals(otherCommand.targetIndex)
                && Arrays.equals(taskIndex, otherCommand.taskIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetIndex, taskIndex);
    }
}
