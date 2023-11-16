package seedu.address.logic.commands.imports;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TabIndex;
import seedu.address.logic.commands.add.AddDeveloperCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.developer.Developer;
/**
 * Represents a command to import developers from a CSV file into the address book.
 * This command allows users to add multiple developers from a CSV file with specific column titles.
 */
public class ImportDeveloperCommand extends Command {
    public static final String COMMAND_WORD = "import-developer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports developers from a CSV file.\n"
            + "Column titles should follow this format strictly:\n"
            + "Name, Contact Number, Email, Address, Date Joined, Role, Salary, GithubId, Rating, Projects";
    public static final String MESSAGE_SUCCESS = "New developer added: %1$s";

    private final ArrayList<Developer> toAddList;

    /**
     * Creates an ImportDeveloperCommand to import the specified list of developers.
     *
     * @param developerList The list of developers to be imported.
     */
    public ImportDeveloperCommand(ArrayList<Developer> developerList) {
        requireNonNull(developerList);
        for (Developer i : developerList) {
            requireNonNull(i);
        }
        toAddList = developerList;
    }

    /**
     * Executes the import operation by adding developers from the CSV file to the address book.
     *
     * @param model The model in which to import the developers.
     * @return A CommandResult indicating the success of the import operation.
     * @throws CommandException If an error occurs during the import operation.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String output = "";
        for (Developer toAdd : toAddList) {
            CommandResult result = new AddDeveloperCommand(toAdd).execute(model);
            output += result.getFeedbackToUser() + "\n";
        }
        return new CommandResult(output, TabIndex.Developer);
    }

    /**
     * Checks if this ImportDeveloperCommand is equal to another object.
     *
     * @param other The object to compare with this ImportDeveloperCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImportDeveloperCommand)) {
            return false;
        }

        ImportDeveloperCommand otherImportCommand = (ImportDeveloperCommand) other;
        return toAddList.equals(otherImportCommand.toAddList);
    }

    /**
     * Generates a string representation of this ImportDeveloperCommand.
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
