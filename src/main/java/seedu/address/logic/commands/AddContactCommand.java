package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.ui.AppState;

/**
 * Adds a contact to the address book.
 */
public class AddContactCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact to the address book. "
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + PREFIX_PHONE + " PHONE "
            + PREFIX_EMAIL + " EMAIL \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John Doe "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_EMAIL + " johnd@example.com ";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in the address book";

    private final Contact toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Contact}
     */
    public AddContactCommand(Contact contact) {
        requireNonNull(contact);
        toAdd = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasContact(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.addContact(toAdd);

        AppState appState = AppState.getInstance();
        appState.setContact(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatContact(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddContactCommand)) {
            return false;
        }

        AddContactCommand otherAddCommand = (AddContactCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
