package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a patient to the address book.
 */
public class AddCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "add";

    public static final String COMMAND_WORD_ALIAS = "a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_WORD_ALIAS
            + ": Adds a Patient to the Patient list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "ID "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_APPOINTMENT + "APPOINTMENT] "
            + "[" + PREFIX_MEDICAL + "MEDICAL_HISTORY]...\n"
            + "Example 1: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ID + "S8765432A "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_APPOINTMENT + "17-10-2023 11:00 13:00 "
            + PREFIX_MEDICAL + "hypochondriac "
            + PREFIX_MEDICAL + "on Medicine XYZ \n"
            + "Example 2: " + COMMAND_WORD_ALIAS + " "
            + PREFIX_NAME + "Alex Yeoh "
            + PREFIX_ID + "S6742632F "
            + PREFIX_PHONE + "85743822 "
            + PREFIX_EMAIL + "alex@example.com "
            + PREFIX_ADDRESS + "420, Country Road, #02-25 "
            + PREFIX_APPOINTMENT + "10-11-2023 11:00 13:00 "
            + PREFIX_MEDICAL + "tachycardia "
            + PREFIX_MEDICAL + "on Medicine CHS ";

    public static final String MESSAGE_SUCCESS = "New Patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This Patient already exists in HealthSync.";
    public static final String MESSAGE_UNDO_ADD_SUCCESS = "Undoing the adding of Patient:  %1$s";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.addPerson(toAdd);
        model.addToHistory(this);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        model.deletePerson(toAdd);
        return new CommandResult(String.format(MESSAGE_UNDO_ADD_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
