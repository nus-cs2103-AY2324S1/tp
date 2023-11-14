package seedu.address.model.partnercourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.partnercourse.exceptions.DuplicatePartnerCourseException;
import seedu.address.model.partnercourse.exceptions.PartnerCourseNotFoundException;
import seedu.address.model.university.UniversityName;

/**
 * A list of partner courses that enforces uniqueness between its elements and does not allow nulls.
 * Note that a PartnerCourse is considered unique by {@code PartnerCourse#isSamePartnerCourse(PartnerCourse)},
 * which concerns adding and updating. On the other hand, the removal of a PartnerCourse uses
 * PartnerCourse#equals(Object) to ensure that the local course with exact matching fields is
 * removed.
 * <p>
 * Implements {@code Iterable<PartnerCourse>} amd thus needs an iterator. Able to utilise in for-each.
 * <p>
 * Note that this is the list containing the records, and the internal list must be an
 * {@code ObservableList<PartnerCourse>} to return in ReadOnlyLocalCourseCatalogue#getPartnerCourseList.
 */
public class UniquePartnerCourseList implements Iterable<PartnerCourse> {
    private final ObservableList<PartnerCourse> internalList = FXCollections.observableArrayList();
    private final ObservableList<PartnerCourse> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent PartnerCourse as the given argument.
     */
    public boolean contains(PartnerCourse toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePartnerCourse);
        //why not equal?
    }

    /**
     * Returns an Optional containing first occurrence of desired PartnerCourse.
     * An empty Optional is returned if PartnerCourse is not found.
     *
     * @param partnerCode This is the PartnerCode to check.
     * @return Optional containing first occurrence of desired PartnerCourse, if any.
     */
    public Optional<PartnerCourse> getPartnerCourseIfExists(PartnerCode partnerCode, UniversityName universityName) {
        requireAllNonNull(partnerCode, universityName);
        return internalList
                .stream()
                .filter(pc -> pc.getPartnerCode().equals(partnerCode)
                        && pc.getPartnerUniversity().getUniversityName().equals(universityName))
                .findFirst();
    }
    /**
     * Adds a PartnerCourse to the list.
     * The PartnerCourse must not already exist in the list.
     */
    public void add(PartnerCourse toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePartnerCourseException();
        }
        internalList.add(toAdd);
    }

    public void setPartnerCourse(PartnerCourse target, PartnerCourse editedPartnerCourse) {
        requireAllNonNull(target, editedPartnerCourse);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PartnerCourseNotFoundException();
        }

        if (!target.isSamePartnerCourse(editedPartnerCourse) && contains(editedPartnerCourse)) {
            throw new DuplicatePartnerCourseException();
        }

        internalList.set(index, editedPartnerCourse);
    }

    /**
     * Removes the equivalent (as per {@code PartnerCourse#equals(Object)})PartnerCourse from the list.
     * The PartnerCourse must exist in the list.
     */
    public void remove(PartnerCourse toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PartnerCourseNotFoundException();
        }
    }

    public void setPartnerCourses(UniquePartnerCourseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setPartnerCourses(List<PartnerCourse> partnerCourses) {
        requireNonNull(partnerCourses);
        if (!partnerCoursesAreUnique(partnerCourses)) {
            throw new DuplicatePartnerCourseException();
        }

        internalList.setAll(partnerCourses);
    }

    public ObservableList<PartnerCourse> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PartnerCourse> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UniquePartnerCourseList)) {
            return false;
        }

        UniquePartnerCourseList otherUniquePartnerCourseList = (UniquePartnerCourseList) other;
        return internalList.equals(otherUniquePartnerCourseList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    private boolean partnerCoursesAreUnique(List<PartnerCourse> partnerCourses) {
        for (int i = 0; i < partnerCourses.size() - 1; i++) {
            for (int j = i + 1; j < partnerCourses.size(); j++) {
                if (partnerCourses.get(i).isSamePartnerCourse(partnerCourses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
