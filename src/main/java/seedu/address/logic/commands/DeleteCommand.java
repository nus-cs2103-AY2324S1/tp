package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Indices;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Fosterer: %1$s";

    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "Deleted Fosterers: %1$s";

    private final Indices targetIndices;

    public DeleteCommand(Indices targetIndices) {
        this.targetIndices = targetIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int[] zeroBasedIndices = this.targetIndices.getZeroBased();

        int numberOfDeletions = zeroBasedIndices.length;
        Person[] peopleToDelete = new Person[numberOfDeletions];

        for (int i = 0; i < numberOfDeletions; i++) {
            int targetIndex = zeroBasedIndices[i];

            if (targetIndex >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(targetIndex);
            peopleToDelete[i] = personToDelete;
        }

        for (Person personToDelete : peopleToDelete) {
            model.deletePerson(personToDelete);
        }

        if (numberOfDeletions == 1) {
           return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(peopleToDelete)));
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PEOPLE_SUCCESS, Messages.format(peopleToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return this.targetIndices.equals(otherDeleteCommand.targetIndices);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndices)
                .toString();
    }
}
