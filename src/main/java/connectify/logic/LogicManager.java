package connectify.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import connectify.commons.core.GuiSettings;
import connectify.commons.core.LogsCenter;
import connectify.logic.commands.Command;
import connectify.logic.commands.CommandResult;
import connectify.logic.commands.exceptions.CommandException;
import connectify.logic.parser.ConnectifyParser;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.Entity;
import connectify.model.InvalidEntityException;
import connectify.model.Model;
import connectify.model.ReadOnlyAddressBook;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.storage.Storage;
import javafx.collections.ObservableList;

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
    private final ConnectifyParser connectifyParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        connectifyParser = new ConnectifyParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;
        Command command = connectifyParser.parseCommand(commandText);
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
    public ObservableList<Company> getFilteredCompanyList() {
        return model.getFilteredCompanyList();
    }

    @Override
    public ObservableList<? extends Entity> getFilteredEntityList() {
        return model.getFilteredEntityList();
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

    @Override
    public String getCurrEntity() {
        return model.getCurrEntity();
    }

    @Override
    public void setCurrEntity(String currEntity) throws InvalidEntityException {
        model.setCurrEntity(currEntity);
    }
}
