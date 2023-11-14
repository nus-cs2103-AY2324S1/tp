package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.UniqueLocalCourseList;

/**
 * Wraps local course data at the catalogue level
 * Duplicates are not allowed (by .isSameLocalCourse comparison)
 */
public class LocalCourseCatalogue implements ReadOnlyLocalCourseCatalogue {

    private final UniqueLocalCourseList localCourses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     * <p>
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */

    {
        localCourses = new UniqueLocalCourseList();
    }

    public LocalCourseCatalogue() {
    }

    /**
     * Creates a LocalCourseCatalogue using the LocalCourses in the {@code toBeCopied}
     */
    public LocalCourseCatalogue(ReadOnlyLocalCourseCatalogue toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the LocalCourse list with {@code localCourses}.
     * {@code localCourses} must not contain duplicate LocalCourses.
     */
    public void setLocalCourses(List<LocalCourse> localCourses) {
        this.localCourses.setLocalCourses(localCourses);
    }

    /**
     * Resets the existing data of this {@code LocalCourseCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyLocalCourseCatalogue newData) {
        requireNonNull(newData);
        setLocalCourses(newData.getLocalCourseList());
    }

    //// person-level operations

    /**
     * Returns true if a LocalCourse with the same identity as {@code localCourseQuery} exists in the catalogue.
     */
    public boolean hasLocalCourse(LocalCourse localCourseQuery) {
        requireNonNull(localCourseQuery);
        return localCourses.contains(localCourseQuery);
    }

    /**
     * Gets the local course if it exists.
     */
    public Optional<LocalCourse> getLocalCourseIfExists(LocalCode localCodeQuery) {
        requireNonNull(localCodeQuery);
        return localCourses.getLocalCourseIfExists(localCodeQuery);
    }

    public Optional<LocalCourse> getLocalCourseNameIfExists(LocalName localNameQuery) {
        requireNonNull(localNameQuery);
        return localCourses.getLocalCourseNameIfExists(localNameQuery);
    }

    /**
     * Adds a LocalCourse to the LocalCourseCatalogue.
     * The LocalCourse must not already exist in SEPlendid.
     */
    public void addLocalCourse(LocalCourse lc) {
        localCourses.add(lc);
    }

    /**
     * Replaces the given LocalCourse {@code target} in the list with {@code editedLocalCourse}.
     * {@code target} must exist in the LocalCourseCatalogue.
     * The LocalCourse identity of {@code editedLocalCourse} must not be the same as another existing LocalCourse.
     */
    public void setLocalCourse(LocalCourse target, LocalCourse editedLocalCourse) {
        requireNonNull(editedLocalCourse);

        localCourses.setLocalCourse(target, editedLocalCourse);
    }

    /**
     * Removes {@code toRemove} from this {@code LocalCourseCatalogue}.
     * {@code toRemove} must exist in the local course catalogue.
     */
    public void removeLocalCourse(LocalCourse toRemove) {
        localCourses.remove(toRemove);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("localcourses", localCourses)
                .toString();
    }

    @Override
    public ObservableList<LocalCourse> getLocalCourseList() {
        return localCourses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LocalCourseCatalogue)) {
            return false;
        }

        LocalCourseCatalogue otherLocalCourseCatalogue = (LocalCourseCatalogue) other;
        return localCourses.equals(otherLocalCourseCatalogue.localCourses);
    }

    @Override
    public int hashCode() {
        return localCourses.hashCode();
    }
}
