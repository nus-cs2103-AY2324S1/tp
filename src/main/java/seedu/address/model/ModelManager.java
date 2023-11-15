package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.TakeCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Prescription;

/**
 * Represents the in-memory model of the prescription list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PrescriptionList prescriptionList;
    private final PrescriptionList completedPrescriptionList;
    private final UserPrefs userPrefs;
    private final FilteredList<Prescription> filteredPrescriptions;
    private final FilteredList<Prescription> filteredCompletedPrescriptions;

    /**
     * Initializes a ModelManagerPrescription with the given prescriptionList and userPrefs.
     */
    public ModelManager(ReadOnlyPrescriptionList prescriptionList, ReadOnlyPrescriptionList completedPrescriptionList,
        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(prescriptionList, userPrefs);

        logger.fine("Initializing with prescription list: " + prescriptionList
                + ", completed prescription list: " + completedPrescriptionList
                + " and user prefs " + userPrefs);

        this.prescriptionList = new PrescriptionList(prescriptionList);
        this.completedPrescriptionList = new PrescriptionList(completedPrescriptionList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPrescriptions = new FilteredList<>(this.prescriptionList.getPrescriptionList());
        filteredCompletedPrescriptions = new FilteredList<>(this.completedPrescriptionList.getPrescriptionList());
    }

    public ModelManager() {
        this(new PrescriptionList(), new PrescriptionList(), new UserPrefs());
    }

    //=========== UserPrefsPrescription ===============================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPrescriptionListFilePath() {
        return userPrefs.getPrescriptionListFilePath();
    }

    @Override
    public void setPrescriptionListFilePath(Path prescriptionListFilePath) {
        requireNonNull(prescriptionListFilePath);
        userPrefs.setPrescriptionListFilePath(prescriptionListFilePath);
    }

    @Override
    public Path getCompletedPrescriptionListFilePath() {
        return userPrefs.getCompletedPrescriptionListFilePath();
    }

    @Override
    public void setCompletedPrescriptionListFilePath(Path completedPrescriptionListFilePath) {
        requireNonNull(completedPrescriptionListFilePath);
        userPrefs.setCompletedPrescriptionListFilePath(completedPrescriptionListFilePath);
    }

    @Override
    public LocalDate getStoredDate() {
        return userPrefs.getStoredDate();
    }

    @Override
    public void setStoredDate(LocalDate storedDate) {
        requireNonNull(storedDate);
        userPrefs.setStoredDate(storedDate);
    }

    //=========== PrescriptionList ================================================================================

    @Override
    public void setPrescriptionList(ReadOnlyPrescriptionList prescriptionList) {
        this.prescriptionList.resetData(prescriptionList);
    }

    @Override
    public ReadOnlyPrescriptionList getPrescriptionList() {
        return prescriptionList;
    }

    @Override
    public boolean hasPrescription(Prescription prescription) {
        requireNonNull(prescription);
        return prescriptionList.hasPrescription(prescription);
    }

    @Override
    public void deletePrescription(Prescription target) {
        prescriptionList.removePrescription(target);
    }

    @Override
    public void addPrescription(Prescription prescription) {
        prescriptionList.addPrescription(prescription);
        updateFilteredPrescriptionList(PREDICATE_SHOW_ALL_PRESCRIPTIONS);
    }

    @Override
    public void setPrescription(Prescription target, Prescription editedPrescription) {
        requireAllNonNull(target, editedPrescription);

        prescriptionList.setPrescription(target, editedPrescription);
    }

    @Override
    public Prescription getPrescriptionByName(Name prescriptionName) throws CommandException {
        requireNonNull(prescriptionName);

        for (Prescription prescription : prescriptionList.getPrescriptionList()) {
            if (prescription.getName().equals(prescriptionName)) {
                return prescription;
            }
        }
        throw new CommandException(TakeCommand.MESSAGE_PRESCRIPTION_NOT_FOUND);
    }

    //=========== Filtered Prescription List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Prescription} backed by the internal list of
     * {@code versionedPrescriptionList}
     */
    @Override
    public ObservableList<Prescription> getFilteredPrescriptionList() {
        return filteredPrescriptions;
    }

    @Override
    public void updateFilteredPrescriptionList(Predicate<Prescription> predicate) {
        requireNonNull(predicate);
        filteredPrescriptions.setPredicate(predicate);
    }

    //=========== CompletedPrescriptionList =========================================================================

    @Override
    public void setCompletedPrescriptionList(ReadOnlyPrescriptionList completedPrescriptionList) {
        this.completedPrescriptionList.resetData(completedPrescriptionList);
    }

    @Override
    public ReadOnlyPrescriptionList getCompletedPrescriptionList() {
        return completedPrescriptionList;
    }

    @Override
    public boolean hasCompletedPrescription(Prescription completedPrescription) {
        requireNonNull(completedPrescription);
        return completedPrescriptionList.hasPrescription(completedPrescription);
    }

    @Override
    public void deleteCompletedPrescription(Prescription target) {
        completedPrescriptionList.removePrescription(target);
    }

    @Override
    public void addCompletedPrescription(Prescription completedPrescription) {
        completedPrescriptionList.addPrescription(completedPrescription);
        updateFilteredCompletedPrescriptionList(PREDICATE_SHOW_ALL_PRESCRIPTIONS);
    }

    @Override
    public void setCompletedPrescription(Prescription target, Prescription editedPrescription) {
        requireAllNonNull(target, editedPrescription);

        completedPrescriptionList.setPrescription(target, editedPrescription);
    }

    @Override
    public Prescription getCompletedPrescriptionByName(Name completedPrescriptionName) throws CommandException {
        requireNonNull(completedPrescriptionName);

        for (Prescription completedPrescription : completedPrescriptionList.getPrescriptionList()) {
            if (completedPrescription.getName().equals(completedPrescriptionName)) {
                return completedPrescription;
            }
        }
        throw new CommandException(TakeCommand.MESSAGE_PRESCRIPTION_NOT_FOUND);
    }

    //=========== Filtered Completed Prescription List Accessors ====================================================

    /**
     * Returns an unmodifiable view of the list of {@code Prescription} backed by the internal list of
     * {@code versionedPrescriptionList}
     */
    @Override
    public ObservableList<Prescription> getFilteredCompletedPrescriptionList() {
        return filteredCompletedPrescriptions;
    }

    @Override
    public void updateFilteredCompletedPrescriptionList(Predicate<Prescription> predicate) {
        requireNonNull(predicate);
        filteredCompletedPrescriptions.setPredicate(predicate);
    }

    @Override
    public boolean hasDrugClash(Prescription toAdd) {
        requireNonNull(toAdd);

        boolean result = false;
        for (Prescription prescription : prescriptionList.getPrescriptionList()) {
            if (prescription.hasDrugClash(toAdd)) {
                toAdd.addConflictingDrug(prescription.getDrug());
                result = true;
            }
        }

        Set<Name> drugSet = toAdd.getConflictingDrugs();
        for (Name drug : drugSet) {
            for (Prescription prescription : prescriptionList.getPrescriptionList()) {
                if (prescription.getDrug().equals(drug)) {
                    prescription.addConflictingDrug(toAdd.getDrug());
                    result = true;
                }
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return prescriptionList.equals(otherModelManager.prescriptionList)
                && completedPrescriptionList.equals(otherModelManager.completedPrescriptionList)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPrescriptions.equals(otherModelManager.filteredPrescriptions)
                && filteredCompletedPrescriptions.equals(otherModelManager.filteredCompletedPrescriptions);
    }

}
