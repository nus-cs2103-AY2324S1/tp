package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;

/**
 * Unmodifiable view of a consultation list
 */
public interface ReadOnlyConsultationList {

    /**
     * Returns an unmodifiable view of the consultation list.
     * This list will not contain any duplicate consultation.
     */
    ObservableList<Consultation> getConsultationList();

}
