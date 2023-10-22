package seedu.ccacommander.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.ccacommander.commons.core.GuiSettings;
import seedu.ccacommander.logic.commands.CommandResult;
import seedu.ccacommander.logic.commands.exceptions.CommandException;
import seedu.ccacommander.logic.parser.exceptions.ParseException;
import seedu.ccacommander.model.ReadOnlyCcaCommander;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;

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
     * Returns the CcaCommander.
     *
     * @see seedu.ccacommander.model.Model#getCcaCommander()
     */
    ReadOnlyCcaCommander getCcaCommander();

    /** Returns an unmodifiable view of the filtered list of members*/
    ObservableList<Member> getFilteredMemberList();

    /** Returns an unmodifiable view of the filtered list of events*/
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns the user prefs' CcaCommander file path.
     */
    Path getCcaCommanderFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
