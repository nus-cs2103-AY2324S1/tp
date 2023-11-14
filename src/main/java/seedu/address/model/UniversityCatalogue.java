package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.university.UniqueUniversityList;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;

/**
 * Wraps universities data at the catalogue level.
 * Duplicates are not allowed (by .isSameUniversities comparison).
 */

public class UniversityCatalogue implements ReadOnlyUniversityCatalogue {

    private final UniqueUniversityList universities;

    {
        universities = new UniqueUniversityList();
    }

    public UniversityCatalogue() {
    }

    /**
     * Creates an UniversityCatalogue using the Universities in the {@code toBeCopied}.
     */
    public UniversityCatalogue(ReadOnlyUniversityCatalogue toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Returns true if a University with the same identity as {@code universityQuery} exists in the catalogue.
     */
    public boolean hasUniversity(University universityQuery) {
        requireNonNull(universityQuery);
        return universities.contains(universityQuery);
    }

    /**
     * Gets the university if it exists
     *
     * @param universityNameQuery This is the UniversityName to check.
     * @return Optional containing first occurrence of desired University, if any.
     */
    public Optional<University> getUniversityIfExists(UniversityName universityNameQuery) {
        requireNonNull(universityNameQuery);
        return universities.getUniversityIfExists(universityNameQuery);
    }

    /**
     * Adds university to university list.
     * @param uN university to add.
     */
    public void addUniversity(University uN) {
        universities.add(uN);
    }

    public void removeUniversity(University uN) {
        universities.remove(uN);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("university", universities)
                .toString();
    }

    @Override
    public ObservableList<University> getUniversityList() {
        return universities.asUnmodifiableObservableList();
    }

    public void setUniversities(List<University> universities) {
        this.universities.setUniversities(universities);
    }

    /**
     * Resets the existing data of this {@code UniversityeCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyUniversityCatalogue newData) {
        requireNonNull(newData);
        setUniversities(newData.getUniversityList());
    }

    /**
     * Replaces the given University {@code target} in the list with {@code editedUniversity}.
     * {@code target} must exist in the UniversityCatalogue.
     * The University identity of {@code editedUniversity} must not be the same as another existing University.
     */
    public void setUniversity(University target, University editedUniversity) {
        requireNonNull(editedUniversity);

        universities.setUniversity(target, editedUniversity);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniversityCatalogue)) {
            return false;
        }

        UniversityCatalogue otherUniversityCatalogue = (UniversityCatalogue) other;
        return universities.equals(otherUniversityCatalogue.universities);
    }

    @Override
    public int hashCode() {
        return universities.hashCode();
    }
}
