package seedu.address.logic;

import java.nio.file.Path;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.prescription.Prescription;

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
     * Returns the PrescriptionList.
     *
     * @see seedu.address.model.Model#getPrescriptionList()
     */
    ReadOnlyPrescriptionList getPrescriptionList();

    /**
     * Returns the CompletedPrescriptionList.
     *
     * @see seedu.address.model.Model#getPrescriptionList()
     */
    ReadOnlyPrescriptionList getCompletedPrescriptionList();

    /** Returns an unmodifiable view of the filtered list of prescriptions */
    ObservableList<Prescription> getFilteredPrescriptionList();

    /** Returns an unmodifiable view of the filtered list of completed prescriptions */
    ObservableList<Prescription> getFilteredCompletedPrescriptionList();

    /**
     * Returns the user prefs' prescription list file path.
     */
    Path getPrescriptionListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    LocalDate getStoredDate();

    void setStoredDate(LocalDate storedDate);

    /**
     * Deletes prescriptions that are past the end date and stores them in the completed prescription list.
     */
    void checkAndMoveEndedPrescriptions();

    void checkAndResetConsumptionCount();
}
