package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;

/**
 * Deletes an animal identified using it's displayed index from the address book.
 */
public class DeleteAnimalCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the animal identified by the index number used in the displayed animal list.\n"
            + "Parameters: INDEX (must be a positive integer)";

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ANIMAL_SUCCESS = "Deleted Animal: %1$s";

    private final Index targetIndex;

    public DeleteAnimalCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
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

        Animal animalToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteAnimal(animalToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ANIMAL_SUCCESS, AnimalMessages.format(animalToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAnimalCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAnimalCommand) other).targetIndex));
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
