package seedu.lovebook.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.logic.commands.Command;
import seedu.lovebook.logic.commands.CommandResult;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.logic.parser.LoveBookParser;
import seedu.lovebook.logic.parser.exceptions.ParseException;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.storage.Storage;

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
    private final LoveBookParser loveBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        loveBookParser = new LoveBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = loveBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveLoveBook(model.getLoveBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyLoveBook getLoveBook() {
        return model.getLoveBook();
    }

    @Override
    public ObservableList<Date> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getLoveBookFilePath() {
        return model.getLoveBookFilePath();
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
    public String getWelcomeMessage() {
        return model.getWelcomeMessage();
    }

}
