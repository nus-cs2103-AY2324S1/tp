package seedu.ccacommander.model.enrolment;

import static java.util.Objects.requireNonNull;
import static seedu.ccacommander.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ccacommander.model.enrolment.exceptions.DuplicateEnrolmentException;
import seedu.ccacommander.model.enrolment.exceptions.EnrolmentNotFoundException;

/**
 * A list of enrolment that enforces uniqueness between its elements and does not allow nulls.
 * An enrolment is considered unique by comparing using {@code Enrolment#isSameEnrolment(Object)}. As such, adding
 * and updating of enrolments uses Enrolment#isSameEnrolment(Enrolment) for equality so as to ensure that the
 * enrolment being added or updated is unique in terms of identity in the UniqueEnrolmentList. However, the removal of
 * an enrolment uses Enrolment#equals(Object) so as to ensure that the enrolment with exactly the same fields will be
 * removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Enrolment#isSameEnrolment(Enrolment)
 */
public class UniqueEnrolmentList implements Iterable<Enrolment> {

    private final ObservableList<Enrolment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Enrolment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent enrolment as the given argument.
     */
    public boolean contains(Enrolment enrolmentToCheck) {
        requireNonNull(enrolmentToCheck);
        return internalList.stream().anyMatch(enrolmentToCheck::isSameEnrolment);
    }

    /**
     * Adds an enrolment to the list.
     * The enrolment must not already exist in the list.
     */
    public void createEnrolment(Enrolment enrolmentToCreate) {
        requireNonNull(enrolmentToCreate);
        if (contains(enrolmentToCreate)) {
            throw new DuplicateEnrolmentException();
        }
        internalList.add(enrolmentToCreate);
    }

    /**
     * Replaces the enrolment {@code target} in the list with {@code editedEnrolment}.
     * {@code targetEnrolment} must exist in the list.
     * The {@code editedEnrolment} must not be the same as another enrolment existing  in the list.
     */
    public void setEnrolment(Enrolment targetEnrolment, Enrolment editedEnrolment) {
        requireAllNonNull(targetEnrolment, editedEnrolment);

        int index = internalList.indexOf(targetEnrolment);
        if (index == -1) {
            throw new EnrolmentNotFoundException();
        }

        if (!targetEnrolment.isSameEnrolment(editedEnrolment) && contains(editedEnrolment)) {
            throw new DuplicateEnrolmentException();
        }

        internalList.set(index, editedEnrolment);
    }

    /**
     * Removes the equivalent enrolment from the list.
     * The enrolment must exist in the list.
     */
    public void remove(Enrolment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EnrolmentNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     */
    public void setEnrolments(UniqueEnrolmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code enrolments}.
     * {@code enrolments} must not contain duplicate enrolments.
     */
    public void setEnrolments(List<Enrolment> enrolments) {
        requireAllNonNull(enrolments);
        if (!enrolmentsAreUnique(enrolments)) {
            throw new DuplicateEnrolmentException();
        }

        internalList.setAll(enrolments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Enrolment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Enrolment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueEnrolmentList)) {
            return false;
        }

        UniqueEnrolmentList otherUniqueEnrolmentList = (UniqueEnrolmentList) other;
        return internalList.equals(otherUniqueEnrolmentList.internalList);
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
     * Returns true if {@code enrolments} contains only unique enrolments.
     */
    private boolean enrolmentsAreUnique(List<Enrolment> enrolments) {
        for (int i = 0; i < enrolments.size() - 1; i++) {
            for (int j = i + 1; j < enrolments.size(); j++) {
                if (enrolments.get(i).isSameEnrolment(enrolments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
