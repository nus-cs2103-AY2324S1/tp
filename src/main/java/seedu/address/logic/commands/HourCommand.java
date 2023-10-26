package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds some hour to all TAs in view
 */
public class HourCommand extends Command {
    public static final String COMMAND_WORD = "hour";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": update work hours for all teaching assistant "
            + "identified. "
            + "Existing values will be increased by the input values.\n"
            + "Parameter: [HOUR] (must be an integer, can be both positive and negative) \n"
            + "Example: " + COMMAND_WORD + " 4 ";
    public static final String MESSAGE_SUCCESS = "Hour updated to all TAs identified";
    public static final String MESSAGE_FAILURE = "Updated hour should be in range 0-9999";

    private final Integer hourToAdd;


    public HourCommand(Integer hourToAdd) {
        this.hourToAdd = hourToAdd;
    }

    /**
     * Executes the HourCommand to update work hours for all teaching assistants identified in the address book.
     * Existing work hour values will be increased by the input hourToAdd. This method ensures that every TA's work
     * hours can be updated correctly. If an invalid input value is provided, it throws a CommandException indicating
     * that the updated hour should be in the range of 0 to 9999.
     *
     * @param model The model containing the data of TAs and work hours.
     * @return A CommandResult with a success message if the update is successful.
     * @throws CommandException If an invalid input value is provided or if the update operation fails.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> currentList = model.getFilteredPersonList();

        // Ensure that every person in the list can be updated correctly, otherwise throw error message to user
        try {
            for (Person person : currentList) {
                person.updateHour(this.hourToAdd);
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        for (Person person : currentList) {
            Person updatedPerson = person.updateHour(this.hourToAdd);
            model.setPerson(person, updatedPerson);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Checks if this HourCommand is equal to another object.
     *
     * @param other The object to compare to this HourCommand.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof HourCommand) {
            HourCommand otherCommand = (HourCommand) other;
            return this.hourToAdd.equals(otherCommand.hourToAdd);
        }
        return false;
    }
}


