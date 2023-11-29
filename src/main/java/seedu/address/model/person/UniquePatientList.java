package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PatientNotFoundException;
import seedu.address.model.person.patients.Patient;


/**
 * A list of patients which is unique
 */
public class UniquePatientList implements Iterable<Patient> {

    private final ObservableList<Patient> internalList = FXCollections.observableArrayList();
    private final ObservableList<Patient> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Patient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list. The person must not already exist in the list.
     */
    public void add(Patient toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}. {@code target} must
     * exist in the list. The person identity of {@code editedPerson} must not be the same as
     * another existing person in the list.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PatientNotFoundException();
        }

        if (!target.isSamePerson(editedPatient) && contains(editedPatient)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPatient);
    }


    /**
     * Removes the equivalent person from the list. The person must exist in the list.
     */
    public void remove(Patient toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PatientNotFoundException();
        }
    }

    public void setPatients(UniquePatientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}. {@code persons} must not contain
     * duplicate persons.
     */
    public void setPatients(List<Patient> patients) {
        requireAllNonNull(patients);
        if (!patientsAreUnique(patients)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(patients);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Patient> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Patient> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePatientList)) {
            return false;
        }

        UniquePatientList otherUniquePatientList = (UniquePatientList) other;
        return internalList.equals(otherUniquePatientList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean patientsAreUnique(List<Patient> patients) {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).isSamePatient(patients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
