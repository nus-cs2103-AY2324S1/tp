package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.UniqueInternshipList;

/**
 * Wraps all data at the internship-book level
 * Duplicates are not allowed (by .isSameInternship comparison)
 */
public class InternshipBook implements ReadOnlyInternshipBook {


    private final UniqueInternshipList internships;

    {
        internships = new UniqueInternshipList();
    }

    public InternshipBook() {}

    /**
     * Creates an InternshipBook using the Internships in the {@code toBeCopied}
     */
    public InternshipBook(ReadOnlyInternshipBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the internship list with {@code internships}.
     * {@code internships} must not contain duplicate internships.
     */
    public void setInternships(List<Internship> internships) {
        this.internships.setInternships(internships);
    }

    /**
     * Resets the existing data of this {@code InternshipBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInternshipBook newData) {
        requireNonNull(newData);

        setInternships(newData.getInternshipList());
    }


    /**
     * Returns true if an internship with the same identity as {@code internship} exists in the internship book.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return this.internships.contains(internship);
    }


    /**
     * Creates an internship in Flagship
     * The internship must not already exist in Flagship.
     */
    public void addInternship(Internship i) {
        this.internships.create(i);
    }

    /**
     * Removes {@code key} from this {@code InternshipBook}.
     * {@code key} must exist in the internship book.
     */

    public void removeInternship(Internship key) {
        this.internships.remove(key);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("internships", internships)
                .toString();
    }

    public void createInternship(Internship i) {
        this.internships.create(i);
    }


    public void setInternship(Internship target, Internship editedInternship) {
        requireNonNull(editedInternship);

        internships.setInternship(target, editedInternship);
    }

    public ObservableList<Internship> getInternshipList() {
        return this.internships.asUnmodifiableObservableList();
    }

    /**
     * Sorts the list of internships based on the provided comparator.
     *
     * @param comparator The comparator to determine the order of internships.
     * @throws NullPointerException if the provided comparator is null.
     */
    public void sortInternships(Comparator<Internship> comparator) {
        requireNonNull(comparator);
        internships.sortInternships(comparator);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipBook)) {
            return false;
        }

        InternshipBook otherInternshipBook = (InternshipBook) other;
        return internships.equals(otherInternshipBook.internships);
    }

    @Override
    public int hashCode() {
        return internships.hashCode();
    }

}
