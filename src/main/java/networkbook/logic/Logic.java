package networkbook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import networkbook.commons.core.GuiSettings;
import networkbook.logic.commands.CommandResult;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.logic.parser.exceptions.ParseException;
import networkbook.model.Model;
import networkbook.model.ReadOnlyNetworkBook;
import networkbook.model.person.Person;

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
     * Returns the NetworkBook.
     *
     * @see Model#getNetworkBook()
     */
    ReadOnlyNetworkBook getNetworkBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' network book file path.
     */
    Path getNetworkBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
