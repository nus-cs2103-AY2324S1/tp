package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Create/Opens the folder of a person identified using it's displayed index
 * from the address book.
 */
public class FileCommand extends Command {

    public static final String COMMAND_WORD = "file";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a folder for the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String OPEN_FILE_PERSON_SUCCESS = "Created/Opened a folder for Person: %1$s";

    private final Index targetIndex;

    public FileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFile = lastShownList.get(targetIndex.getZeroBased());
        String name = personToFile.getName().toString();
        createFolder(name);
        return new CommandResult(String.format(OPEN_FILE_PERSON_SUCCESS, Messages.format(personToFile)));
    }

    /**
     * Creates a folder with the name ClientFiles if it does not exist,
     * creates a folder within that folder named {@code folderName} if it does not
     * exist,
     * then opens the folder.
     */
    public void createFolder(String folderName) {
        try {
            Path folder = Paths.get("./ClientFiles/");
            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
            }
            Path storageFolder = Paths.get("./ClientFiles/" + folderName);
            if (!Files.exists(storageFolder)) {
                Files.createDirectory(storageFolder);
            }
            File file = storageFolder.toFile();
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            System.out.println("an error occurred while creating folder: " + e);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FileCommand)) {
            return false;
        }

        FileCommand otherFileCommand = (FileCommand) other;
        return targetIndex.equals(otherFileCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
