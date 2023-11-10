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
import seedu.address.logic.parser.BookingBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.prefixcompletion.PrefixCompletion;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyBookingsBook;
import seedu.address.model.booking.Booking;
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
    private final BookingBookParser bookingBookParser;

    /**
     * Constructs a LogicManager with the given Model and Storage.
     *
     * @param model   The Model component providing the application's data.
     * @param storage The Storage component handling the saving and loading of data.
     */
    public LogicManager(Model model, Storage storage) {
        assert model != null;
        assert storage != null;
        this.model = model;
        this.storage = storage;
        bookingBookParser = new BookingBookParser();
        PrefixCompletion.setBookingBook(model.getBookingsBook());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = bookingBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        // Update the bookingBook for prefix completion to use
        PrefixCompletion.setBookingBook(model.getBookingsBook());

        try {
            storage.saveBookingBook(model.getBookingsBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyBookingsBook getBookingBook() {
        return model.getBookingsBook();
    }

    @Override
    public ObservableList<Booking> getFilteredPersonList() {
        return model.getFilteredBookingList();
    }

    @Override
    public Path getBookingBookFilePath() {
        return model.getBookingBookFilePath();
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
