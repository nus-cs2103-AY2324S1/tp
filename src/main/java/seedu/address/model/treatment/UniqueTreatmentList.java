package seedu.address.model.treatment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.TreatmentNotFoundException;

/**
 * A list of unique treatments
 */
public class UniqueTreatmentList implements Iterable<Treatment> {

    private final ObservableList<Treatment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Treatment> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Treatment as the given argument.
     */
    public boolean contains(Treatment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTreatment);
    }

    /**
     * Returns true if the list contains an equivalent TreatmentName as the given argument.
     */
    public boolean contains(TreatmentName toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().map(Treatment::getName).anyMatch(toCheck::isSameTreatmentName);
    }

    /**
     * Adds a Treatment to the list. The Treatment must not already exist in the list.
     */
    public void add(Treatment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the treatment {@code target} in the list with {@code editedTreatment}. {@code target} must
     * exist in the list. The treatment identity of {@code editedTreatment} must not be the same as
     * another existing treatment in the list.
     */
    public void setTreatment(Treatment target, Treatment editedTreatment) {
        requireAllNonNull(target, editedTreatment);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TreatmentNotFoundException();
        }

        if (!target.isSameTreatment(editedTreatment) && contains(editedTreatment)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTreatment);
    }

    /**
     * Removes the equivalent treatment from the list. The treatment must exist in the list.
     */
    public void remove(Treatment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TreatmentNotFoundException();
        }
    }

    public void setTreatments(UniqueTreatmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code treatments}. {@code treatments} must not contain
     * duplicate treatments.
     */
    public void setTreatments(List<Treatment> treatments) {
        requireAllNonNull(treatments);
        if (!treatmentsAreUnique(treatments)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(treatments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Treatment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Treatment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTreatmentList)) {
            return false;
        }

        UniqueTreatmentList otherUniquePatientList = (UniqueTreatmentList) other;
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
     * Returns true if {@code treatments} contains only unique treatments.
     */
    private boolean treatmentsAreUnique(List<Treatment> treatments) {
        for (int i = 0; i < treatments.size() - 1; i++) {
            for (int j = i + 1; j < treatments.size(); j++) {
                if (treatments.get(i).isSameTreatment(treatments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
