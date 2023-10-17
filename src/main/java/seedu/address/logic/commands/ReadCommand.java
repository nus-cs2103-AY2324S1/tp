package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Reads a specific piece of information of a particular employee
 * and display it.
 */
public class ReadCommand extends Command {

    public static final String COMMAND_WORD = "read";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Read a specific information of a particular employee "
            + "by the index number used in the last person listing and the specific field name. "
            + "The app will then display the information accordingly.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 b";

    public static final String MESSAGE_READ_PERSON_SUCCESS = "Read %s";

    private final Index targetIndex;
    private final String field;

    /**
     * Constructs a ReadCommand to read the information of the specified field from
     * a person at the given index.
     *
     * @param index The index of the person in the last displayed list.
     * @param field The field to read (e.g., "p" for phone, "a" for address, "e" for
     *              email).
     */
    public ReadCommand(Index index, String field) {
        requireNonNull(index, field);
        this.targetIndex = index;
        this.field = field;
    }

    public String getField() {
        return this.field;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToRead = lastShownList.get(targetIndex.getZeroBased());

        String fieldStr = fieldValueToString(personToRead);

        model.setSpecificPersonToDisplay(personToRead);

        return new CommandResult(String.format(MESSAGE_READ_PERSON_SUCCESS, field), true, fieldStr);
    }

    /**
     * Returns a string representation of the specific field of a person.
     *
     * @param person The person to retrieve the field from.
     * @return The information specified by the field.
     * @throws CommandException if the field is invalid.
     */

    public String fieldValueToString(Person person) throws CommandException {
        switch (field) {
        case "phone":
            return person.getPhone().value;
        case "address":
            return person.getAddress().value;
        case "email":
            return person.getEmail().value;
        case "bank account":
            return person.getBankAccount().value;
        case "join date":
            return person.getJoinDate().value;
        case "salary":
            return person.getSalary().value;
        case "annual leave":
            return person.getAnnualLeave().value;
        default:
            throw new CommandException(Messages.MESSAGE_INVALID_FIELD_TO_READ);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReadCommand)) {
            return false;
        }

        ReadCommand otherReadCommand = (ReadCommand) other;
        return targetIndex.equals(otherReadCommand.targetIndex) && field.equals(otherReadCommand.getField());
    }
}
