package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.mapping.Mapping;

/**
 * Unmodifiable view of local course catalogue.
 */
public interface ReadOnlyMappingCatalogue {

    /**
     * Returns an unmodifiable view of the mapping list.
     * This list will not contain any duplicate mapping.
     * ObservableList will aid interoperability.
     */
    ObservableList<Mapping> getMappingList();

}
