package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CommissionBuilder;

public class CommissionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Commission(null, null, null, null));
    }
    @Test
    public void equals() {
        Commission commission = new CommissionBuilder().build();
        Commission similarCommission = new CommissionBuilder().build();

        // same object -> returns true
        assertTrue(commission.equals(commission));

        // same values -> returns false
        assertFalse(commission.equals(similarCommission));

        // null -> returns false
        assertFalse(commission.equals(null));

        // different types -> returns false
        assertFalse(commission.equals(5.0f));

        // different Amount -> returns false
        Commission editedCommission = new CommissionBuilder().withAmount("90").build();
        assertFalse(commission.equals(editedCommission));

        // different Description -> returns false
        editedCommission = new CommissionBuilder().withDescription("Diff").build();
        assertFalse(commission.equals(editedCommission));

        // different Person -> returns false
        editedCommission = new CommissionBuilder().withPerson("BOB").build();
        assertFalse(commission.equals(editedCommission));
    }
    @Test
    public void toStringMethod() {
        String expected = Commission.class.getCanonicalName() + "{client=" + COMMISSION_FROM_ALICE.getClient()
                + ", amount=" + COMMISSION_FROM_ALICE.getAmount()
                + ", description=" + COMMISSION_FROM_ALICE.getDescription()
                + ", timeDue=" + COMMISSION_FROM_ALICE.getTimeDue() + "}";
        assertEquals(expected, COMMISSION_FROM_ALICE.toString());
    }
}
