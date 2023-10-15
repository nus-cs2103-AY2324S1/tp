package seedu.staffsnap.model;

import javafx.collections.ObservableList;
import seedu.staffsnap.model.applicant.Applicant;

/**
 * Unmodifiable view of an applicant book
 */
public interface ReadOnlyApplicantBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Applicant> getApplicantList();

}
