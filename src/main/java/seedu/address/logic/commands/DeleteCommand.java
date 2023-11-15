package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Indices;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes one or more fosterers identified using their displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes fosterers identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX [INDEX...] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "1 fosterer deleted:\n%1$s";

    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "%1$d fosterers deleted:\n%2$s";

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private final Indices targetIndices;

    public DeleteCommand(Indices targetIndices) {
        this.targetIndices = targetIndices;
    }

    /**
     * Checks if zero-based target index is valid.
     */
    public static boolean isValidIndex(int targetIndex, int listSize) throws CommandException {
        if (targetIndex >= listSize || targetIndex < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        return true;
    }

    /**
     * Returns the people to delete, given the last shown list of Persons.
     *
     * @throws CommandException when target index is out of bounds.
     */
    public Person[] getPeopleToDelete(List<Person> lastShownList) throws CommandException {
        logger.info("Retrieving people to delete from last shown list.");
        int[] zeroBasedIndices = this.targetIndices.getZeroBased();
        int numberOfDeletions = this.targetIndices.getSize();
        Person[] peopleToDelete = new Person[numberOfDeletions];

        for (int i = 0; i < numberOfDeletions; i++) {
            int targetIndex = zeroBasedIndices[i];

            if (isValidIndex(targetIndex, lastShownList.size())) {
                Person personToDelete = lastShownList.get(targetIndex);
                peopleToDelete[i] = personToDelete;
            }
        }
        return peopleToDelete;
    }

    public String getDeleteResultMessage(Person[] peopleToDelete) {
        int numberDeleted = peopleToDelete.length;
        if (numberDeleted == 1) {
            return String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(peopleToDelete));
        }
        return String.format(MESSAGE_DELETE_PEOPLE_SUCCESS, numberDeleted, Messages.format(peopleToDelete));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person[] peopleToDelete = getPeopleToDelete(lastShownList);

        for (Person personToDelete : peopleToDelete) {
            model.deletePerson(personToDelete);
        }
        String message = getDeleteResultMessage(peopleToDelete);
        return new CommandResult(message);
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
                .add("targetIndices", targetIndices)
                .toString();
    }
}
