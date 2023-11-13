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
import seedu.address.logic.parser.UniMateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.task.ReadOnlyTaskManager;
import seedu.address.model.task.Task;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";
    public static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "File was moved/modified";
    public static final String FILE_PARSE_EXCEPTION_MESSAGE = "Data in the file could not be parsed.";
    public static final String FEATURE_NOT_IMPLEMENTED_YET = "Feature coming soon...";
    private static final String TIMEZONE_STRING = "Asia/Singapore";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final UniMateParser uniMateParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        uniMateParser = new UniMateParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = uniMateParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveCalendar(model.getCalendar());
            storage.saveTaskManager(model.getTaskManager());
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
    public ReadOnlyCalendar getCalendar() {
        return model.getCalendar();
    }

    @Override
    public ReadOnlyTaskManager getTaskManager() {
        return model.getTaskManager();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return model.getEventList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return model.getTaskList();
    }

    @Override
    public ObservableList<Event> getCurrentWeekEventList() {
        return model.getCurrentWeekEventList();
    }

    @Override
    public ReadOnlyCalendar getComparisonCalendar() {
        return model.getComparisonCalendar();
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
}
