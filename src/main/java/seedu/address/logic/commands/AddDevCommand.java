package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Represents an AddDevCommand with the associated logic and the ability to be executed.
 * Adds a developer to the address book.
 */
public class AddDevCommand extends Command {

    public static final String COMMAND_WORD = "newdev";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a developer to the Team. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New developer added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This developer already exists in the team";

    private final Person devToAdd;

    /**
     * Constructs an {@code AddDevCommand} to add the specified developer to the address book.
     *
     * @param person The developer to be added.
     */
    public AddDevCommand(Person person) {
        requireNonNull(person);
        devToAdd = person;
    }

    /**
     * Executes the AddDevCommand, adding a developer to the address book.
     *
     * @param model The current state of the application model.
     * @return A CommandResult indicating the result of executing this command on the given model.
     * @throws CommandException if the developer already exists in the address book.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(devToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(devToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(devToAdd)));
    }

    /**
     * Checks whether another object is equal to this AddDevCommand.
     *
     * @param other The object to compare with.
     * @return true if the other object is an AddDevCommand with the same developer details, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDevCommand)) {
            return false;
        }

        AddDevCommand otherAddCommand = (AddDevCommand) other;
        return devToAdd.equals(otherAddCommand.devToAdd);
    }

    /**
     * Returns a string representation of this AddDevCommand, including the developer's details.
     *
     * @return A string representing this command, including the developer's details.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("devToAdd", devToAdd)
                .toString();
    }
}
