package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Prescription;

/**
 * The API of the ModelPrescription component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Prescription> PREDICATE_SHOW_ALL_PRESCRIPTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' prescription list file path.
     */
    Path getPrescriptionListFilePath();

    /**
     * Sets the user prefs' prescription list file path.
     */
    void setPrescriptionListFilePath(Path prescriptionListFilePath);

    /**
     * Returns the user prefs' completed prescription list file path.
     */
    Path getCompletedPrescriptionListFilePath();

    /**
     * Sets the user prefs' completed prescription list file path.
     */
    void setCompletedPrescriptionListFilePath(Path completedPrescriptionListFilePath);

    /**
     * Replaces prescription list data with the data in {@code prescriptionList}.
     */
    void setPrescriptionList(ReadOnlyPrescriptionList prescriptionList);

    /** Returns the PrescriptionList */
    ReadOnlyPrescriptionList getPrescriptionList();

    /**
     * Returns true if a prescription with the same identity as {@code prescription} exists in the prescription list.
     */
    boolean hasPrescription(Prescription prescription);

    /**
     * Deletes the given prescription.
     * The prescription must exist in the prescription list.
     */
    void deletePrescription(Prescription target);

    /**
     * Adds the given prescription.
     * {@code prescription} must not already exist in the prescription list.
     */
    void addPrescription(Prescription prescription);

    /**
     * Replaces the given prescription {@code target} with {@code editedPrescription}.
     * {@code target} must exist in the prescription list.
     * The prescription identity of {@code editedPrescription} must not be the same as another
     * existing prescription in the prescription list.
     */
    void setPrescription(Prescription target, Prescription editedPrescription);

    /** Returns an unmodifiable view of the filtered prescription list */
    ObservableList<Prescription> getFilteredPrescriptionList();

    /**
     * Returns a prescription with the same name as {@code prescriptionName}
     */
    Prescription getPrescriptionByName(Name prescriptionName) throws CommandException;

    /**
     * Updates the filter of the filtered prescription list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPrescriptionList(Predicate<Prescription> predicate);

    /**
     * Replaces completed prescription list data with the data in {@code completedPrescriptionList}.
     */
    void setCompletedPrescriptionList(ReadOnlyPrescriptionList completedPrescriptionList);

    /** Returns the CompletedPrescriptionList */
    ReadOnlyPrescriptionList getCompletedPrescriptionList();

    /**
     * Returns true if a prescription with the same identity as {@code completedPrescription}
     * exists in the completed prescription list.
     */
    boolean hasCompletedPrescription(Prescription completedPrescription);

    /**
     * Deletes the given prescription.
     * The prescription must exist in the completed prescription list.
     */
    void deleteCompletedPrescription(Prescription target);

    /**
     * Adds the given completed prescription.
     * {@codecompletedPprescription} must not already exist in the completed prescription list.
     */
    void addCompletedPrescription(Prescription completedPrescription);

    /**
     * Replaces the given prescription {@code target} with {@code editedPrescription}.
     * {@code target} must exist in the completed prescription list.
     * The prescription identity of {@code editedPrescription} must not be the same as another
     * existing prescription in the completed prescription list.
     */
    void setCompletedPrescription(Prescription target, Prescription editedPrescription);

    /** Returns an unmodifiable view of the filtered completed prescription list */
    ObservableList<Prescription> getFilteredCompletedPrescriptionList();

    /**
     * Returns a prescription with the same name as {@code completedPrescriptionName}
     */
    Prescription getCompletedPrescriptionByName(Name completedPrescriptionName) throws CommandException;

    /**
     * Updates the filter of the filtered completed prescription list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompletedPrescriptionList(Predicate<Prescription> predicate);

    LocalDate getStoredDate();

    void setStoredDate(LocalDate storedDate);

    boolean hasDrugClash(Prescription toAdd);
}
