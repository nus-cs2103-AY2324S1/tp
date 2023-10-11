package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class LoadCommand extends Command{

    public static final String COMMAND_WORD = "load";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Load student information from an existing JSON file. "
            + "Copy the JSON file to be loaded into the data folder. "
            + "The data in the JSON file will be loaded into the app. "
            + "The file also becomes the new default save file.\n"
            + "Parameters: FILE_NAME (case sensitive string) "
            + "f/ [FILE_NAME]\n"
            + "Example: " + COMMAND_WORD + " "
            + "f/ export-v1";

    public static final String MESSAGE_ARGUMENTS = "Filename: %1$s";

    private final Path filePath;

    /**
     * @param filePath of the file to be updated to
     */
    public LoadCommand(Path filePath) {
        requireNonNull(filePath);

        this.filePath = filePath;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setAddressBookFilePath(filePath);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LoadCommand)) {
            return false;
        }

        LoadCommand e = (LoadCommand) other;
        return filePath.equals(e.filePath);
    }

    /**
     * Generates a command execution success message based on whether
     * the file is loaded
     * {@code personToEdit}.
     */
    private String generateSuccessMessage() {
        String message = !load.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }
}
