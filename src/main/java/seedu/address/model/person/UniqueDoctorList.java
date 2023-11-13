package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of Doctors that enforces uniqueness between its elements and does not allow nulls.
 * A x is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * Doctors uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniqueDoctorList. However, the removal of a Doctor uses Person#equals(Object) so
 * as to ensure that the Doctor with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 */
public class UniqueDoctorList extends UniqueObjectList<Doctor> {

    protected final ObservableList<Doctor> internalList = FXCollections.observableArrayList();
    protected final ObservableList<Doctor> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Replaces the current list of doctors with a new list provided in the {@code replacement} parameter.
     */
    public void setDoctors(UniqueDoctorList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns true if the list contains an equivalent doctor as the given argument.
     */
    @Override
    public boolean contains(Doctor toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }
    /**
     * Returns true if the list contains the same Ic as the given argument.
     */
    public boolean containsIc(Ic nric) {
        requireNonNull(nric);
        return internalList.stream().anyMatch(x -> x.getIc().equals(nric));
    }

    /**
     * Adds a doctor to the list.
     * The doctor must not already exist in the list.
     */
    @Override
    public void add(Doctor toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Doctor {@code target} in the list with {@code editedDoctor}.
     * {@code target} must exist in the list.
     * The Doctor identity of {@code editedDoctor} must not be the same as another existing Doctor in the list.
     */
    @Override
    public void setObject(Doctor target, Doctor editedDoctor) {
        requireAllNonNull(target, editedDoctor);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedDoctor) && contains(editedDoctor)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedDoctor);
    }

    /**
     * Removes the equivalent doctor from the list.
     * The doctor must exist in the list.
     */
    @Override
    public void remove(Doctor toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code doctors}.
     * {@code doctors} must not contain duplicate doctors.
     */
    @Override
    public void setObjects(List<Doctor> doctors) {
        requireAllNonNull(doctors);
        if (!objectsAreUnique(doctors)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(doctors);
    }

    @Override
    public ObservableList<Doctor> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns true if {@code doctors} contains only unique doctors.
     */
    @Override
    protected boolean objectsAreUnique(List<Doctor> doctors) {
        for (int i = 0; i < doctors.size() - 1; i++) {
            for (int j = i + 1; j < doctors.size(); j++) {
                if (doctors.get(i).isSamePerson(doctors.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

