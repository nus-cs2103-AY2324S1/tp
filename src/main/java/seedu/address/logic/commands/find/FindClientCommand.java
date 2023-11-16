package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.getMessageClientsListedOverview;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORGANISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TabIndex;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Represents a command to find clients based on various attributes.
 * This command allows users to search for clients by specifying keywords for different client attributes.
 */
public class FindClientCommand extends Command {
    public static final String COMMAND_WORD = "find-client";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find clients based on various attributes.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME_KEYWORDS] "
            + "[" + PREFIX_ROLE + "ROLE_KEYWORDS] "
            + "[" + PREFIX_ADDRESS + "ADDRESS_KEYWORDS] "
            + "[" + PREFIX_EMAIL + "EMAIL_KEYWORDS] "
            + "[" + PREFIX_PHONE + "PHONE_KEYWORDS] "
            + "[" + PREFIX_PROJECT + "PROJECT_KEYWORDS] "
            + "[" + PREFIX_DOCUMENT + "DOCUMENT_KEYWORDS] "
            + "[" + PREFIX_ORGANISATION + "ORGANISATION_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " n/John r/client\n";

    private Predicate<Client> predicate;

    /**
     * Creates a FindClientCommand with the specified predicate.
     *
     * @param predicate The predicate used to filter the list of clients.
     */
    public FindClientCommand(Predicate<Client> predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the find operation by updating the filtered client list based on the given predicate.
     *
     * @param model The model to execute the command on.
     * @return A CommandResult indicating the results of the find operation.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);

        int resultCount = model.getFilteredClientList().size();
        String message = getMessageClientsListedOverview(resultCount);

        return new CommandResult(message, TabIndex.Client);
    }

    /**
     * Checks if this FindClientCommand is equal to another object.
     *
     * @param other The object to compare with this FindClientCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindClientCommand)) {
            return false;
        }

        FindClientCommand otherFindClientCommand = (FindClientCommand) other;
        return predicate.equals(otherFindClientCommand.predicate);
    }

    /**
     * Generates a string representation of this FindClientCommand.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
