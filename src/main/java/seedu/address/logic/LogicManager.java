package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ProfBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.statemanager.State;
//import seedu.address.storage.Storage;
import seedu.address.storage.ProfBookStorage;
import seedu.address.ui.Displayable;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final State state;

    private final ProfBookStorage storage;
    private final ProfBookParser profBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(State state, ProfBookStorage storage) {
        //todo : storage;
        this.storage = storage;
        this.state = state;
        profBookParser = new ProfBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = profBookParser.parseCommand(commandText, state.getCurrPath());
        commandResult = command.execute(state);

        try {
            storage.saveProfBook(state.getRoot());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ObservableList<Displayable> getDisplayList() {
        return state.getDisplayList();
    }

    @Override
    public String getCurrPath() {
        return state.getCurrPath().toString();
    }

    @Override
    public Path getAddressBookFilePath() {
        return state.getProfBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return state.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        state.setGuiSettings(guiSettings);
    }
}
