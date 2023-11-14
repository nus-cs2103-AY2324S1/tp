package seedu.lovebook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.logic.commands.CommandResult;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.logic.parser.exceptions.ParseException;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.date.Date;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the LoveBook.
     *
     * @see seedu.lovebook.model.Model#getLoveBook()
     */
    ReadOnlyLoveBook getLoveBook();

    /** Returns an unmodifiable view of the filtered list of dates */
    ObservableList<Date> getFilteredPersonList();

    /**
     * Returns the user prefs' LoveBook file path.
     */
    Path getLoveBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    String getWelcomeMessage();
}
