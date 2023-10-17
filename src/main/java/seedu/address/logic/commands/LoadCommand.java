package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Loads student information from an existing JSON file in the data folder.
 */
public class LoadCommand extends Command {

    public static final String COMMAND_WORD = "load";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Load student information from an existing JSON file in the data folder. "
            + "The file becomes the new default save file. "
            + "Parameters: " + PREFIX_FILE + "FILE_NAME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FILE + "export-v1";
    public static final String MESSAGE_LOAD_SUCCESS = "The file %1$s.json has successfully loaded!\n"
            + "This will be the new default save file.\n";
    public static final String MESSAGE_FILE_NOT_FOUND = "The file %1$s.json cannot be found.\n"
            + "Please make sure the file is in the /data folder.\n";
    public static final String MESSAGE_FILE_CANNOT_LOAD = "The file %1$s.json cannot be loaded.\n"
            + "Please make sure the file is formatted correctly.\n";

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

    /**
     * Checks if the file exists and loads the file into the address book.
     * If the file cannot be loaded or does not exist, an exception is thrown.
     * @param  model {@code Model} which the command should operate on.
     * @return A command result with the success message.
     * @throws CommandException If the file cannot be loaded or does not exist.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        File f = filePath.toFile();
        if (!f.isFile()) {
            throw new CommandException(String.format(MESSAGE_FILE_NOT_FOUND, fileName));
        }
        requireNonNull(model);
        AddressBookStorage tempAddressBookStorage = new JsonAddressBookStorage(filePath);
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook newData;
        try {
            addressBookOptional = tempAddressBookStorage.readAddressBook();
            newData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataLoadingException e) {
            throw new CommandException(String.format(MESSAGE_FILE_CANNOT_LOAD, fileName));
        }
        model.setAddressBookFilePath(filePath);
        model.setAddressBook(newData);
        return new CommandResult(String.format(MESSAGE_LOAD_SUCCESS, fileName), false, false, true);
    }

    /**
     * Checks if the file path and file name are the same.
     * @param other Object to compare with.
     * @return True if the file path and file name are the same.
     */
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
        return filePath.equals(e.filePath) && fileName.equals(e.fileName);
    }
}
