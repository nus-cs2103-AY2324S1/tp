package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.finance.Finance;

/**
 * This class implements a Read only finance book that is unmodifiable.
 */
public interface ReadOnlyFinancesBook {
    /**
     * Returns an unmodifiable view of the finances list.
     * This list will not contain any duplicate finances.
     */
    ObservableList<Finance> getFinanceList();
}
