package seedu.staffsnap.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.UniqueApplicantList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameApplicant comparison)
 */
public class ApplicantBook implements ReadOnlyApplicantBook {

    private final UniqueApplicantList applicants;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applicants = new UniqueApplicantList();
    }

    public ApplicantBook() {}

    /**
     * Creates an ApplicantBook using the Applicants in the {@code toBeCopied}
     */
    public ApplicantBook(ReadOnlyApplicantBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Resets the existing data of this {@code ApplicantBook} with {@code newData}.
     */
    public void resetData(ReadOnlyApplicantBook newData) {
        requireNonNull(newData);

        setApplicants(newData.getApplicantList());
    }

    //// applicant-level operations

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the applicant book.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Returns true if there are more than one applicant with the same identity as {@code applicant}
     */
    public boolean isDuplicateApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.numberOfDuplicates(applicant) > 1;
    }

    /**
     * Adds an applicant to the applicant book.
     * The applicant must not already exist in the applicant book.
     */
    public void addApplicant(Applicant e) {
        applicants.add(e);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the applicant book.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the
     * applicant book.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);

        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code ApplicantBook}.
     * {@code key} must exist in the applicant book.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("applicants", applicants)
                .toString();
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicantBook)) {
            return false;
        }

        ApplicantBook otherApplicantBook = (ApplicantBook) other;
        return applicants.equals(otherApplicantBook.applicants);
    }

    @Override
    public int hashCode() {
        return applicants.hashCode();
    }
}
