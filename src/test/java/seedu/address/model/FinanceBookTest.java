package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.finance.Finance;

public class FinanceBookTest {
    private final FinancesBook financesBook = new FinancesBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), financesBook.getFinanceList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financesBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFinancesBook_replacesData() {
        FinancesBook newData = getTypicalFinancesBook();
        financesBook.resetData(newData);
        assertEquals(newData, financesBook);
    }

    @Test
    public void hasFinance_nullFinance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financesBook.hasFinance(null));
    }

    @Test
    public void hasFinance_financeNotInFinancesBook_returnsFalse() {
        assertFalse(financesBook.hasFinance(COMMISSION_FROM_ALICE));
    }

    @Test
    public void hasFinance_financeInFinancesBook_returnsTrue() {
        financesBook.addFinance(COMMISSION_FROM_ALICE);
        assertTrue(financesBook.hasFinance(COMMISSION_FROM_ALICE));
    }
    @Test
    public void getFinanceList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> financesBook.getFinanceList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = FinancesBook.class.getCanonicalName() + "{finances=" + financesBook.getFinanceList() + "}";
        assertEquals(expected, financesBook.toString());
    }

    /**
     * A stub ReadOnlyFinancesBook whose finances list can violate interface constraints.
     */
    private static class FinancesBookStub implements ReadOnlyFinancesBook {
        private final ObservableList<Finance> finances = FXCollections.observableArrayList();

        FinancesBookStub(Collection<Finance> finances) {
            this.finances.setAll(finances);
        }

        @Override
        public ObservableList<Finance> getFinanceList() {
            return finances;
        }
    }
}
