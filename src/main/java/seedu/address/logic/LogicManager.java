package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.ViewModeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final ViewModeParser viewModeParser;
    private final Model backupModel;
    private boolean finalConfirmation = false;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        viewModeParser = new ViewModeParser();
        this.backupModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Path mainPath = Path.of("data\\addressbookbackup.json");
        backupModel.setAddressBookFilePath(mainPath);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, IOException,
            DataLoadingException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command;
        CommandResult commandResult;
        command = addressBookParser.parseCommand(commandText);
        //checking for undo command
        if (command instanceof UndoCommand) {
            if (backupModel.getAddressBook().equals(model.getAddressBook())) {
                commandResult = command.execute(null);
                return commandResult;
            } else {
                model.setAddressBook(backupModel.getAddressBook());
            }
        } else {
            backupModel.setAddressBook(model.getAddressBook());
        }

        if (this.finalConfirmation) {
            command.toString();
        }

        commandResult = command.execute(model);

        if (commandResult.getCommandType() == CommandType.CLEAR) {
            this.finalConfirmation = true;
        } else {
            this.finalConfirmation = false;
        }

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public CommandResult executeInView(String commandText, Person newPerson, Index targetIndex)
            throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command;
        CommandResult commandResult;

        command = viewModeParser.parseCommand(commandText, newPerson, targetIndex);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    public boolean getFinalConfirmation() {
        return this.finalConfirmation;
    }
}
