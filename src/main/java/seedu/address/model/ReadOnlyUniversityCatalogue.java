package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.university.University;

/**
 * Unmodifiable view of a university catalogue.
 */
public interface ReadOnlyUniversityCatalogue {

    ObservableList<University> getUniversityList();

}
