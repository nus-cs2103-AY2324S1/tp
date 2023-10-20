package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;



/**
 * Adds a {@link Contact}.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    //TODO values like these could be in some kind of ProductionData, which
    // could be used in tests alongside TestData.Valid
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_NOTE + "NOTE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_NOTE + "CS2103 Prof. "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owes money";

    private final Contact toAdd;
    /**
     * Creates an AddCommand to add the specified {@code Contact}
     */
    public AddCommand(Contact contact) {
        requireNonNull(contact);
        toAdd = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.containsContact(toAdd)) {
            throw new CommandException(Messages.MESSAGE_COMMAND_DUPLICATE_CONTACT);
        }

        model.addContact(toAdd);
        return new CommandResult(String.format(Messages.MESSAGE_ADD_COMMAND_SUCCESS, Contact.format(toAdd)));
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
