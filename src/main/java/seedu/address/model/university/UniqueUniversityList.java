package seedu.address.model.university;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.university.exceptions.DuplicateUniversityException;
import seedu.address.model.university.exceptions.UniversityNotFoundException;

/**
 * A list of universities that enforces uniqueness between its elements and does not allow nulls.
 * Note that a University is considered unique by {@code University#isSameUniversity(University)},
 * which concerns adding and updating. On the other hand, the removal of a University uses
 * University#equals(Object) to ensure that the university with exact matching fields is
 * removed.
 * <p>
 * Implements {@code Iterable<University>} amd thus needs an iterator. Able to utilise in for-each.
 * <p>
 * Note that this is the list containing the records, and the internal list must be an
 * {@code ObservableList<University>} to return in ReadOnlyUniversityCatalogue#getUniversityList.
 */
public class UniqueUniversityList implements Iterable<University> {
    private final ObservableList<University> internalList = FXCollections.observableArrayList();
    private final ObservableList<University> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent University as the given argument.
     */
    public boolean contains(University toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameUniversity);
    }

    /**
     * Returns an Optional containing first occurrence of desired University.
     * An empty Optional is returned if University is not found.
     *
     * @param universityName This is the UniversityName to check.
     * @return Optional containing first occurrence of desired University, if any.
     */
    public Optional<University> getUniversityIfExists(UniversityName universityName) {
        requireNonNull(universityName);
        return internalList.stream().filter(u -> u.getUniversityName().equals(universityName))
                .findFirst();
    }

    /**
     * Removes the equivalent (as per {@code University#equals(Object)})University from the list.
     * The University must exist in the list.
     */
    public void remove(University toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new UniversityNotFoundException();
        }
    }

    public void setUniversities(UniqueUniversityList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setUniversities(List<University> universities) {
        requireAllNonNull(universities);
        if (!universitiesAreUnique(universities)) {
            throw new DuplicateUniversityException();
        }

        internalList.setAll(universities);
    }

    public ObservableList<University> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<University> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueUniversityList)) {
            return false;
        }

        UniqueUniversityList otherUniqueUniversity = (UniqueUniversityList) other;
        return internalList.equals(otherUniqueUniversity.internalList);
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
     * Returns true if {@code localCourses} contains only unique LocalCourses.
     */
    private boolean universitiesAreUnique(List<University> universities) {
        // Last element would be second to last
        for (int i = 0; i < universities.size() - 1; i++) {
            // Starts from i + 1, to last element
            for (int j = i + 1; j < universities.size(); j++) {
                if (universities.get(i).isSameUniversity(universities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Adds a university to the list.
     * The University must not already exist in the list.
     */
    public void add(University toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateUniversityException();
        }
        internalList.add(toAdd);
    }

    public void setUniversity(University target, University editedUniversity) {
        requireAllNonNull(target, editedUniversity);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new UniversityNotFoundException();
        }

        if (!target.isSameUniversity(editedUniversity) && contains(editedUniversity)) {
            throw new DuplicateUniversityException();
        }

        internalList.set(index, editedUniversity);
    }
}
