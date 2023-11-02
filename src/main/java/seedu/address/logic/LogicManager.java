package seedu.address.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.UniMateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.RuntimeParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
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
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public CommandResult importUserCalendar(File file) {
        logger.info("----------------[IMPORT CALENDAR][" + file.getName() + "]");
        CommandResult commandResult;


        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            CalendarBuilder helperCalendarBuilder = new CalendarBuilder();
            Calendar helperCalendar = helperCalendarBuilder.build(fileInputStream);

            TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
            VTimeZone tz = registry.getTimeZone(TIMEZONE_STRING).getVTimeZone();
            helperCalendar.add(tz);
            for (VEvent vEvent : helperCalendar.<VEvent>getComponents()) {
                parseVEvent(vEvent);
            }
            commandResult = new CommandResult(FEATURE_NOT_IMPLEMENTED_YET);
        } catch (FileNotFoundException fileNotFoundException) {
            commandResult = new CommandResult(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        } catch (ParserException parserException) {
            commandResult = new CommandResult(FILE_PARSE_EXCEPTION_MESSAGE);
        } catch (IOException ioe) {
            commandResult = new CommandResult(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()));
        } catch (RuntimeException featureNotImplmented) {
            commandResult = new CommandResult(featureNotImplmented.getMessage());
        }
        return commandResult;
    }

    private Event parseVEvent(VEvent vEvent) {
        DtStart<ZonedDateTime> start = vEvent.<ZonedDateTime>getDateTimeStart().get();
        DtEnd<ZonedDateTime> end = vEvent.<ZonedDateTime>getDateTimeEnd().get();
        start.setDefaultTimeZone(ZoneId.systemDefault());
        end.setDefaultTimeZone(ZoneId.systemDefault());
        Period<ZonedDateTime> currentYearPeriod = new Period<ZonedDateTime>(ZonedDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(0, 0), ZoneId.of("Z")),
                ZonedDateTime.of(LocalDate.of(2024, 1, 1), LocalTime.of(0, 0), ZoneId.of("Z")));
        vEvent.<ZonedDateTime>calculateRecurrenceSet(currentYearPeriod);
        throw new RuntimeException(FEATURE_NOT_IMPLEMENTED_YET);
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
    public ObservableList<Event> getEventList() {
        return model.getEventList();
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
