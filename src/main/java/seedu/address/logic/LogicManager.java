package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
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

    private String[] displayedFieldsList = new String[0];

    // Boolean property to track changes to ListUI to indicate a refresh
    private BooleanProperty refreshListUi = new SimpleBooleanProperty(false);

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser(model);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        model.addCommandHistory(commandText);
        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        // Set displayFieldsList if there is a list of params specified
        String[] displayParams = commandResult.getDisplayParams(); // array of strings eg. ["phone", "subjects"]
        if (displayParams.length != 0) {
            if (displayParams[0].equals("none")) {
                setDisplayedFieldsList(new String[0]);
            } else {
                setDisplayedFieldsList(displayParams);
            }
        }

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveScheduleList(model.getScheduleList());
            storage.savePersonLessonMap(model.getPersonLessonMap());
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
    public ObservableList<Lesson> getFilteredScheduleList() {
        return model.getFilteredScheduleList();
    }

    @Override
    public ObservableList<Task> getFullTaskList() {
        return model.getFullTaskList();
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
    public String[] getDisplayedFieldsList() {
        return displayedFieldsList;
    }
    // list command should validate the fields, make sure they are valid
    public void setDisplayedFieldsList(String[] displayedFieldsList) {
        this.displayedFieldsList = displayedFieldsList;
        refreshListUi();
    }

    public BooleanProperty getRefreshListUi() {
        return refreshListUi;
    }

    public void refreshListUi() {
        refreshListUi.setValue(!refreshListUi.getValue());
    }
}
