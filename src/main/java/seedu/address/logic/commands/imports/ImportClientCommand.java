package seedu.address.logic.commands.imports;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TabIndex;
import seedu.address.logic.commands.add.AddClientCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Represents a command to import clients from a CSV file into the address book.
 * This command allows users to add multiple clients from a CSV file with specific column titles.
 */
public class ImportClientCommand extends Command {
    public static final String COMMAND_WORD = "import-client";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports clients from a CSV file.\n"
            + "Column titles should follow this format strictly:\n"
            + "Name, Contact Number, Email, Address, Role, Organisation, Document, Projects";
    public static final String MESSAGE_SUCCESS = "New client added: %1$s";

    private final ArrayList<Client> toAddList;

    /**
     * Creates an ImportClientCommand to import the specified list of clients.
     *
     * @param clientList The list of clients to be imported.
     */
    public ImportClientCommand(ArrayList<Client> clientList) {
        requireNonNull(clientList);
        for (Client i : clientList) {
            requireNonNull(i);
        }
        toAddList = clientList;
    }

    /**
     * Executes the import operation by adding clients from the CSV file to the address book.
     *
     * @param model The model in which to import the clients.
     * @return A CommandResult indicating the success of the import operation.
     * @throws CommandException If an error occurs during the import operation.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String output = "";
        for (Client toAdd : toAddList) {
            CommandResult result = new AddClientCommand(toAdd).execute(model);
            output += result.getFeedbackToUser() + "\n";
        }
        return new CommandResult(output, TabIndex.Client);
    }

    /**
     * Checks if this ImportClientCommand is equal to another object.
     *
     * @param other The object to compare with this ImportClientCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImportClientCommand)) {
            return false;
        }

        ImportClientCommand otherImportCommand = (ImportClientCommand) other;
        return toAddList.equals(otherImportCommand.toAddList);
    }

    /**
     * Generates a string representation of this ImportClientCommand.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAddList", toAddList)
                .toString();
    }
}
