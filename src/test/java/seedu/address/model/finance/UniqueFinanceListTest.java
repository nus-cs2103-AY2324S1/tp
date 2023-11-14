package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;
import static seedu.address.testutil.TypicalFinances.EXPENSE_THIRTY_TO_K;

import org.junit.jupiter.api.Test;

import seedu.address.model.finance.exceptions.CommissionNotFoundException;
import seedu.address.model.finance.exceptions.ExpenseNotFoundException;

class UniqueFinanceListTest {
    private final UniqueFinanceList uniqueFinanceList = new UniqueFinanceList();
    @Test
    public void contains_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFinanceList.contains(null));
    }
    @Test
    public void contains_commissionNotInList_returnsFalse() {
        assertFalse(uniqueFinanceList.contains(COMMISSION_FROM_ALICE));
    }
    @Test
    public void contains_commissionInList_returnsTrue() {
        uniqueFinanceList.add(COMMISSION_FROM_ALICE);
        assertTrue(uniqueFinanceList.contains(COMMISSION_FROM_ALICE));
    }
    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueFinanceList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void setExpense_nullTargetExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFinanceList.setExpense(null, EXPENSE_THIRTY_TO_K));
    }

    @Test
    public void setExpense_nullEditedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFinanceList.setExpense(EXPENSE_THIRTY_TO_K,
                null));
    }

    @Test
    public void setExpense_targetExpenseNotInList_throwsExpenseNotFoundException() {
        assertThrows(ExpenseNotFoundException.class, () -> uniqueFinanceList.setExpense(EXPENSE_THIRTY_TO_K,
                EXPENSE_THIRTY_TO_K));
    }

    @Test
    public void setExpense_editedExpenseIsSameExpense_success() {
        uniqueFinanceList.add(EXPENSE_THIRTY_TO_K);
        uniqueFinanceList.setExpense(EXPENSE_THIRTY_TO_K, EXPENSE_THIRTY_TO_K);
        UniqueFinanceList expectedUniqueFinanceList = new UniqueFinanceList();
        expectedUniqueFinanceList.add(EXPENSE_THIRTY_TO_K);
        assertEquals(expectedUniqueFinanceList, uniqueFinanceList);
    }

    @Test
    public void setCommission_nullTargetCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFinanceList.setCommission(null,
                COMMISSION_FROM_ALICE));
    }

    @Test
    public void setCommission_nullEditedCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFinanceList.setCommission(COMMISSION_FROM_ALICE,
                null));
    }

    @Test
    public void setCommission_targetCommissionNotInList_throwsCommissionNotFoundException() {
        assertThrows(CommissionNotFoundException.class, () -> uniqueFinanceList.setCommission(COMMISSION_FROM_ALICE,
                COMMISSION_FROM_ALICE));
    }

    @Test
    public void setCommission_editedCommissionIsSameCommission_success() {
        uniqueFinanceList.add(COMMISSION_FROM_ALICE);
        uniqueFinanceList.setCommission(COMMISSION_FROM_ALICE, COMMISSION_FROM_ALICE);
        UniqueFinanceList expectedUniqueFinanceList = new UniqueFinanceList();
        expectedUniqueFinanceList.add(COMMISSION_FROM_ALICE);
        assertEquals(expectedUniqueFinanceList, uniqueFinanceList);
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueFinanceList.asUnmodifiableObservableList().toString(), uniqueFinanceList.toString());
    }
}

