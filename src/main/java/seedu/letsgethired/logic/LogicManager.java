package seedu.letsgethired.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.commons.core.LogsCenter;
import seedu.letsgethired.logic.commands.Command;
import seedu.letsgethired.logic.commands.CommandResult;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.logic.parser.InternTrackerParser;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.storage.Storage;

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
    private final InternTrackerParser dataParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        dataParser = new InternTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = dataParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInternTracker(model.getInternTracker());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternTracker getInternTracker() {
        return model.getInternTracker();
    }

    @Override
    public ObservableList<InternApplication> getFilteredInternApplicationList() {
        return model.getFilteredInternApplicationList();
    }

    @Override
    public Path getInternTrackerFilePath() {
        return model.getInternTrackerFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
