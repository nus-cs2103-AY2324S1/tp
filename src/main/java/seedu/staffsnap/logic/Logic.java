package seedu.staffsnap.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.staffsnap.commons.core.GuiSettings;
import seedu.staffsnap.logic.commands.CommandResult;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.ReadOnlyApplicantBook;
import seedu.staffsnap.model.applicant.Applicant;

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
     * Returns the ApplicantBook.
     *
     * @see seedu.staffsnap.model.Model#getApplicantBook()
     */
    ReadOnlyApplicantBook getApplicantBook();

    /** Returns an unmodifiable view of the filtered list of applicants */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Returns the user prefs' applicant book file path.
     */
    Path getApplicantBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
