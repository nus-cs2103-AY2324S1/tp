package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.letsgethired.model.application.exceptions.DuplicateInternApplicationException;
import seedu.letsgethired.model.application.exceptions.InternApplicationNotFoundException;


/**
 * A list of applications that enforces uniqueness between its elements and does not allow nulls.
 * An application is considered unique by comparing using {@code InternApplication#isSameApplication
 * (InternApplication)}. As such, adding and updating of applications uses InternApplication#isSameApplication
 * (InternApplication) for equality to ensure that the application being added or updated is
 * unique in terms of identity in the UniqueApplicationList. However, the removal of an application uses
 * InternApplication#equals(Object) to ensure that the application with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see InternApplication#isSameApplication(InternApplication)
 */
public class UniqueApplicationList implements Iterable<InternApplication> {

    private final ObservableList<InternApplication> internalList = FXCollections.observableArrayList();
    private final ObservableList<InternApplication> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent application as the given argument.
     */
    public boolean contains(InternApplication toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameApplication);
    }

    /**
     * Adds a InternApplication to the list.
     * The InternApplication must not already exist in the list.
     */
    public void add(InternApplication toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateInternApplicationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the application {@code target} in the list with {@code editedInternApplication}.
     * {@code target} must exist in the list.
     * The application identity of {@code editedInternApplication} must not be the same as another existing
     * application in
     * the list.
     */
    public void setApplication(InternApplication target, InternApplication editedInternApplication) {
        requireAllNonNull(target, editedInternApplication);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InternApplicationNotFoundException();
        }

        if (!target.isSameApplication(editedInternApplication) && contains(editedInternApplication)) {
            throw new DuplicateInternApplicationException();
        }

        internalList.set(index, editedInternApplication);
    }

    /**
     * Removes the equivalent application from the list.
     * The application must exist in the list.
     */
    public void remove(InternApplication toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new InternApplicationNotFoundException();
        }
    }

    public void setApplications(UniqueApplicationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code internApplications}.
     * {@code internApplications} must not contain duplicate applications.
     */
    public void setApplications(List<InternApplication> internApplications) {
        requireAllNonNull(internApplications);
        if (!applicationsAreUnique(internApplications)) {
            throw new DuplicateInternApplicationException();
        }

        internalList.setAll(internApplications);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<InternApplication> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<InternApplication> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueApplicationList)) {
            return false;
        }

        UniqueApplicationList otherUniqueApplicationList = (UniqueApplicationList) other;
        return internalList.equals(otherUniqueApplicationList.internalList);
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
     * Returns true if {@code internApplications} contains only unique applications.
     */
    private boolean applicationsAreUnique(List<InternApplication> internApplications) {
        for (int i = 0; i < internApplications.size() - 1; i++) {
            for (int j = i + 1; j < internApplications.size(); j++) {
                if (internApplications.get(i).isSameApplication(internApplications.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Creates a deep copy of this UniqueApplicationList.
     *
     * @return A new UniqueApplicationList that is a deep copy of this list.
     */
    public UniqueApplicationList clone() {
        UniqueApplicationList clonedList = new UniqueApplicationList();
        for (InternApplication originalApplication : internalList) {
            InternApplication copiedApplication = originalApplication.clone();
            clonedList.add(copiedApplication);
        }
        return clonedList;
    }
}
