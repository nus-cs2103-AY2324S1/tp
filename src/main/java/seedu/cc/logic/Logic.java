package seedu.cc.logic;

import java.nio.file.Path;

import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import seedu.cc.commons.core.GuiSettings;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.model.ReadOnlyClinicBook;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;

/**
 * API of the NewLogic component
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
     * Returns the ClinicBook.
     *
     * @see seedu.cc.model.Model#getClinicBook()
     */
    ReadOnlyClinicBook getClinicBook();

    /** Returns an unmodifiable view of the filtered list of patients */
    ObservableList<Patient> getFilteredPatientList();

    ObservableList<MedicalHistoryEvent> getFilteredMedicalHistoryEventList();
    /**
     * Returns the user prefs' clinic book file path.
     */

    ObservableList<AppointmentEvent> getFilteredAppointmentList();
    Path getClinicBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    void setCurrentTab(int tabIndex);

    IntegerProperty currentTabProperty();

    int getCurrentTab();
}
