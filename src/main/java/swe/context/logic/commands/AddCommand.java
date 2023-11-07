package swe.context.logic.commands;

import static java.util.Objects.requireNonNull;
import static swe.context.logic.parser.CliSyntax.PREFIX_ALTERNATE;
import static swe.context.logic.parser.CliSyntax.PREFIX_EMAIL;
import static swe.context.logic.parser.CliSyntax.PREFIX_NAME;
import static swe.context.logic.parser.CliSyntax.PREFIX_NOTE;
import static swe.context.logic.parser.CliSyntax.PREFIX_PHONE;
import static swe.context.logic.parser.CliSyntax.PREFIX_TAG;

import swe.context.commons.util.ToStringBuilder;
import swe.context.logic.Messages;
import swe.context.logic.commands.exceptions.CommandException;
import swe.context.model.Model;
import swe.context.model.contact.Contact;



/**
 * Adds a {@link Contact}.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = String.format(
        "%s: Adds a contact."
                + "%nParameters: %sNAME %sPHONE_NUMBER %sEMAIL"
                + " [%sNOTE] [%sTAG]... [%sALTERNATE_CONTACT]..."
                + "%nExample: %s %sJohn Doe %s98765432 %sjohn.doe@email.com"
                + " %sLikes SE. %sNUS %sCS2103 course %sTelegram: JohnDoe",
        AddCommand.COMMAND_WORD,
        PREFIX_NAME,
        PREFIX_PHONE,
        PREFIX_EMAIL,
        PREFIX_NOTE,
        PREFIX_TAG,
        PREFIX_ALTERNATE,
        AddCommand.COMMAND_WORD,
        PREFIX_NAME,
        PREFIX_PHONE,
        PREFIX_EMAIL,
        PREFIX_NOTE,
        PREFIX_TAG,
        PREFIX_TAG,
        PREFIX_ALTERNATE
    );

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
            throw new CommandException(Messages.COMMAND_DUPLICATE_CONTACT);
        }

        model.addContact(toAdd);
        return new CommandResult(Messages.addCommandSuccess(Contact.format(toAdd)));
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
