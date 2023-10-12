package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

public class LoadCommand extends Command{

    public static final String COMMAND_WORD = "load";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Load student information from an existing JSON file. "
            + "Copy the JSON file to be loaded into the data folder. "
            + "The data in the JSON file will be loaded into the app. "
            + "The file also becomes the new default save file.\n"
            + "Parameters: FILE_NAME (case sensitive string) "
            + "f/FILE_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "f/export-v1";

    public static final String MESSAGE_SUCCESS = "The file %1$s.json has successfully loaded!\n"
            + "This will be the new default save file.\n";

    public static final String MESSAGE_FILE_NOT_FOUND = "The file %1$s.json cannot be found.\n"
            + "Please make sure that the file is in the /data folder.\n";

    public static final String MESSAGE_FILE_CANNOT_LOAD = "The file %1$s.json cannot be loaded.\n"
            + "Please try loading another file.\n";

    public static final String MESSAGE_GENERAL_EXCEPTION = "General Exception!";

    private final String fileName;
    private final Path filePath;

    /**
     * @param fileName New save file
     * @param filePath Relative path of the new save file
     */
    public LoadCommand(String fileName, Path filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        System.out.println("8r8r8");
        try {
            File f = filePath.toFile();
            if (!f.isFile()) {
                throw new FileNotFoundException();
            }
            requireNonNull(model);
            model.setAddressBookFilePath(filePath);
            ReadOnlyAddressBook newAddressBook = model.getAddressBook();
            model.setAddressBook(newAddressBook);
            return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
        } catch (FileNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_FILE_NOT_FOUND, fileName));
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
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
}
