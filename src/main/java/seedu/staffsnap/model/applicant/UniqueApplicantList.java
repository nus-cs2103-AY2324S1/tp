package seedu.staffsnap.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.staffsnap.commons.core.LogsCenter;
import seedu.staffsnap.commons.util.StringUtil;
import seedu.staffsnap.model.applicant.exceptions.ApplicantNotFoundException;
import seedu.staffsnap.model.applicant.exceptions.DuplicateApplicantException;


/**
 * A list of applicants that enforces uniqueness between its elements and does not allow nulls.
 * An applicant is considered unique by comparing using {@code Applicant#isSameApplicant(Applicant)}. As such, adding \
 * and updating of applicants uses Applicant#isSameApplicant(Applicant) for equality so as to ensure that the applicant
 * being added or updated is unique in terms of identity in the UniqueApplicantList. However, the removal of a applicant
 * uses Applicant#equals(Object) to ensure that the applicant with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Applicant#isSameApplicant(Applicant)
 */
public class UniqueApplicantList implements Iterable<Applicant> {

    private static final Logger logger = LogsCenter.getLogger(UniqueApplicantList.class);

    private final ObservableList<Applicant> internalList = FXCollections.observableArrayList();
    private final ObservableList<Applicant> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent applicant as the given argument.
     */
    public boolean contains(Applicant toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameApplicant);
    }

    /**
     * Adds a Applicant to the list.
     * The Applicant must not already exist in the list.
     * catches {@code RuntimeException} such as:
     * ClassCastException, NullPointerException, IllegalArgumentException
     */
    public void add(Applicant toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateApplicantException();
        }
        try {
            internalList.add(toAdd);
        } catch (RuntimeException e) {
            logger.info("Error adding applicant: " + StringUtil.getDetails(e));
        }
    }

    /**
     * Replaces the applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the list.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the list.
     * catches {@code RuntimeException} such as:
     * ClassCastException, NullPointerException, IllegalArgumentException, IndexOutOfBoundsException
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ApplicantNotFoundException();
        }

        if (!target.isSameApplicant(editedApplicant) && contains(editedApplicant)) {
            throw new DuplicateApplicantException();
        }

        try {
            internalList.set(index, editedApplicant);
        } catch (RuntimeException e) {
            logger.info("Error editing applicant: " + target.getName() + " " + StringUtil.getDetails(e));
        }
    }

    /**
     * Removes the equivalent applicant from the list.
     * The applicant must exist in the list.
     */
    public void remove(Applicant toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ApplicantNotFoundException();
        }
    }

    public void setApplicants(UniqueApplicantList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     * Catches {@code RuntimeException} such as NullPointerException
     */
    public void setApplicants(List<Applicant> applicants) {
        requireAllNonNull(applicants);
        if (!applicantsAreUnique(applicants)) {
            throw new DuplicateApplicantException();
        }

        try {
            internalList.setAll(applicants);
        } catch (RuntimeException e) {
            logger.info("Error setting applicants: " + StringUtil.getDetails(e));
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Applicant> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Applicant> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueApplicantList)) {
            return false;
        }

        UniqueApplicantList otherUniqueApplicantList = (UniqueApplicantList) other;
        return internalList.equals(otherUniqueApplicantList.internalList);
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
     * Returns true if {@code applicants} contains only unique applicants.
     */
    private boolean applicantsAreUnique(List<Applicant> applicants) {
        for (int i = 0; i < applicants.size() - 1; i++) {
            for (int j = i + 1; j < applicants.size(); j++) {
                if (applicants.get(i).isSameApplicant(applicants.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
