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
import seedu.address.logic.parser.ParseCommandHandlers;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.finance.Finance;
import seedu.address.model.person.Person;
import seedu.address.storage.BookStorage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final BookStorage storage;
    private final ParseCommandHandlers parser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code BookStorage}.
     */
    public LogicManager(Model model, BookStorage storage, ParseCommandHandlers parser) {
        this.model = model;
        this.storage = storage;
        this.parser = parser;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;

        Command command = parser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveEventsBook(model.getEventsBook());
            storage.saveFinancesBook(model.getFinancesBook());
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
    public ObservableList<Event> getFilteredEventList() {
        return model.getFilteredEventList();
    }


    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public Path getEventsBookFilePath() {
        return model.getEventsFilePath();
    }

    @Override
    public Path getFinancesBookFilePath() {
        return model.getFinanceFilePath();
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
    public Logic setNewParser(ParseCommandHandlers newParser) {
        return new LogicManager(this.model, this.storage, newParser);
    }
    @Override
    public ObservableList<Event> getEventList() {
        return model.getEventList();
    }
    @Override
    public ObservableList<Finance> getFinanceList() {
        return model.getFilteredFinanceList();
    }
    public ParseCommandHandlers getParser() {
        return parser;
    }
}
