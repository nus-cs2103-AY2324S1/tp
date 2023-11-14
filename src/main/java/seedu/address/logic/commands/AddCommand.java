package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    /**
     * Keyword to trigger the add command.
     */
    public static final String COMMAND_WORD = "add";

    /**
     * Usage instructions for the 'add' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "\nParameters: "
            + PREFIX_NAME + " NAME"
            + " [" + PREFIX_ROLE + " ROLE1, ...]"
            + " [" + PREFIX_CONTACT + " CONTACT1, ...]"
            + " [" + PREFIX_COURSE + " COURSECODE1/CLASS1, ...]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John "
            + PREFIX_ROLE + " Student, TA "
            + PREFIX_CONTACT + " john@example.com, 98765432 "
            + PREFIX_COURSE + " CS2103T/G06, CS2101/G06, CS2100/T24";


    /**
     * Success message displayed upon successfully adding a person.
     */
    public static final String MESSAGE_SUCCESS = "You have added a new person in : \n %1$s";

    /**
     * Message displayed when trying to add a person with a name that already exists.
     */
    public static final String MESSAGE_DUPLICATE_PERSON = "Note: A person with the same name already exists." + "\n"
            + "Please edit the existing person or change the name of this person to be added";

    public static final String MESSAGE_NAME_MISSING = "Note: Compulsory name input is missing"
            + "\nUnable to add a person without name";

    private final Person toAdd;

    /**
     * Constructor for AddCommand to add the specified {@code Person}.
     *
     * @param person The person to be added to the address book.
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    /**
     * Executes the AddCommand to add the specified person to the address book.
     *
     * @param model The current model containing the address book data.
     * @return The result of the command execution.
     * @throws CommandException If the person already exists in the address book.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    /**
     * Checks equality of this AddCommand with another object.
     *
     * @param other The other object to compare the AddCommand with.
     * @return True if the other object is equal to this AddCommand, otherwise returns false.
     */
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

    /**
     * Generates a string representation of this AddCommand.
     *
     * @return A string representation of the AddCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
