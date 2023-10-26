package transact.logic.commands;

import static java.util.Objects.requireNonNull;
import static transact.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static transact.logic.parser.CliSyntax.PREFIX_EMAIL;
import static transact.logic.parser.CliSyntax.PREFIX_NAME;
import static transact.logic.parser.CliSyntax.PREFIX_PHONE;
import static transact.logic.parser.CliSyntax.PREFIX_TAG;

import transact.commons.util.ToStringBuilder;
import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.person.Person;
import transact.ui.MainWindow.TabWindow;

/**
 * Adds a person to the address book.
 */
public class AddStaffCommand extends Command {

    public static final String COMMAND_WORD = "addstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
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

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddStaffCommand to add the specified {@code Person}
     */
    public AddStaffCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd.getPersonId())) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)), TabWindow.ADDRESSBOOK);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStaffCommand)) {
            return false;
        }

        AddStaffCommand otherAddStaffCommand = (AddStaffCommand) other;
        return toAdd.equals(otherAddStaffCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
