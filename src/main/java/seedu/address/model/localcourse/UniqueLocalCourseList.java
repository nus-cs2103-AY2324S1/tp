package seedu.address.model.localcourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.localcourse.exceptions.DuplicateLocalCourseException;
import seedu.address.model.localcourse.exceptions.LocalCourseNotFoundException;

/**
 * A list of local courses that enforces uniqueness between its elements and does not allow nulls.
 * Note that a LocalCourse is considered unique by {@code LocalCourse#isSameLocalCourse(LocalCourse)},
 * which concerns adding and updating. On the other hand, the removal of a LocalCourse uses
 * LocalCourse#equals(Object) to ensure that the local course with exact matching fields is
 * removed.
 * <p>
 * Implements {@code Iterable<LocalCourse>} amd thus needs an iterator. Able to utilise in for-each.
 * <p>
 * Note that this is the list containing the records, and the internal list must be an
 * {@code ObservableList<LocalCourse>} to return in ReadOnlyLocalCourseCatalogue#getLocalCourseList.
 */
public class UniqueLocalCourseList implements Iterable<LocalCourse> {
    private final ObservableList<LocalCourse> internalList = FXCollections.observableArrayList();
    private final ObservableList<LocalCourse> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent LocalCourse as the given argument.
     */
    public boolean contains(LocalCourse toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLocalCourse);
    }

    /**
     * Returns an Optional containing first occurrence of desired LocalCourse.
     * An empty Optional is returned if LocalCourse is not found.
     *
     * @param localCode This is the LocalCode to check.
     * @return Optional containing first occurrence of desired LocalCourse, if any.
     */
    public Optional<LocalCourse> getLocalCourseIfExists(LocalCode localCode) {
        requireNonNull(localCode);
        return internalList.stream().filter(lc -> lc.getLocalCode().equals(localCode))
                .findFirst();
    }

    /**
     * Adds a LocalCourse to the list.
     * The LocalCourse must not already exist in the list.
     */
    public void add(LocalCourse toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLocalCourseException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the LocalCourse {@code target} in the list with {@code editedLocalCourse}.
     * {@code target} must exist in the list.
     * The LocalCourse identity of {@code editedLocalCourse} must not be the same as another
     * existing LocalCourse in the list.
     */
    public void setLocalCourse(LocalCourse target, LocalCourse editedLocalCourse) {
        requireAllNonNull(target, editedLocalCourse);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LocalCourseNotFoundException();
        }

        if (!target.isSameLocalCourse(editedLocalCourse) && contains(editedLocalCourse)) {
            throw new DuplicateLocalCourseException();
        }

        internalList.set(index, editedLocalCourse);
    }

    /**
     * Removes the equivalent (as per {@code LocalCourse#equals(Object)})LocalCourse from the list.
     * The LocalCourse must exist in the list.
     */
    public void remove(LocalCourse toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LocalCourseNotFoundException();
        }
    }

    public void setLocalCourses(UniqueLocalCourseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code localCourses}.
     * {@code localCourses} must not contain duplicate LocalCourses.
     */
    public void setLocalCourses(List<LocalCourse> localCourses) {
        requireAllNonNull(localCourses);
        if (!localCoursesAreUnique(localCourses)) {
            throw new DuplicateLocalCourseException();
        }

        internalList.setAll(localCourses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<LocalCourse> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<LocalCourse> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueLocalCourseList)) {
            return false;
        }

        UniqueLocalCourseList otherUniqueLocalCourseList = (UniqueLocalCourseList) other;
        return internalList.equals(otherUniqueLocalCourseList.internalList);
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
    private boolean localCoursesAreUnique(List<LocalCourse> localCourses) {
        // Last element would be second to last
        for (int i = 0; i < localCourses.size() - 1; i++) {
            // Starts from i + 1, to last element
            for (int j = i + 1; j < localCourses.size(); j++) {
                if (localCourses.get(i).isSameLocalCourse(localCourses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Optional<LocalCourse> getLocalCourseNameIfExists(LocalName localNameQuery) {
        requireNonNull(localNameQuery);
        return internalList.stream().filter(ln -> ln.getLocalName().equals(localNameQuery))
                .findFirst();
    }
}
