package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.UniquePrescriptionList;

/**
 * Wraps all data at the prescription-list level
 * Duplicates are not allowed (by .isSamePrescription comparison)
 */
public class PrescriptionList implements ReadOnlyPrescriptionList {

    private final UniquePrescriptionList prescriptions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        prescriptions = new UniquePrescriptionList();
    }

    public PrescriptionList() {}

    /**
     * Creates a PrescriptionList using the Prescriptions in the {@code toBeCopied}
     */
    public PrescriptionList(ReadOnlyPrescriptionList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the prescription list with {@code prescriptions}.
     * {@code prescriptions} must not contain duplicate prescriptions.
     */
    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions.setPrescriptions(prescriptions);
    }

    /**
     * Resets the existing data of this {@code PrescriptionList} with {@code newData}.
     */
    public void resetData(ReadOnlyPrescriptionList newData) {
        requireNonNull(newData);

        setPrescriptions(newData.getPrescriptionList());
    }

    //// prescription-level operations

    /**
     * Returns true if a prescription with the same identity as {@code prescription} exists in the prescription list.
     */
    public boolean hasPrescription(Prescription prescription) {
        requireNonNull(prescription);
        return prescriptions.contains(prescription);
    }

    /**
     * Adds a prescription to the prescription list.
     * The prescription must not already exist in the prescription list.
     */
    public void addPrescription(Prescription p) {
        prescriptions.add(p);
    }

    /**
     * Replaces the given prescription {@code target} in the list with {@code editedPrescription}.
     * {@code target} must exist in the prescription list.
     * The prescription identity of {@code editedPrescription} must not be the same as another
     * existing prescription in the prescription list.
     */
    public void setPrescription(Prescription target, Prescription editedPrescription) {
        requireNonNull(editedPrescription);

        prescriptions.setPrescription(target, editedPrescription);
    }

    /**
     * Removes {@code key} from this {@code PrescriptionList}.
     * {@code key} must exist in the prescription list.
     */
    public void removePrescription(Prescription key) {
        prescriptions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("prescriptions", prescriptions)
                .toString();
    }

    @Override
    public ObservableList<Prescription> getPrescriptionList() {
        return prescriptions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PrescriptionList)) {
            return false;
        }

        PrescriptionList otherPrescriptionList = (PrescriptionList) other;
        return prescriptions.equals(otherPrescriptionList.prescriptions);
    }

    @Override
    public int hashCode() {
        return prescriptions.hashCode();
    }
}
