package seedu.address.model.month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DELETE_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELETE_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELETE_MONTH_2;

import org.junit.jupiter.api.Test;

public class DeleteMonthTest {
    @Test
    public void isValidDeleteMonth() {
        assertFalse(DeleteMonth.isValidDeleteMonth(INVALID_DELETE_MONTH));
    }

    @Test
    public void equals() {
        DeleteMonth month1 = new DeleteMonth(VALID_DELETE_MONTH);
        DeleteMonth sameAsMonth1 = new DeleteMonth(VALID_DELETE_MONTH);

        assertTrue(month1.equals(month1));
        assertTrue(month1.equals(sameAsMonth1));

        DeleteMonth month2 = new DeleteMonth(VALID_DELETE_MONTH_2);

        assertFalse(month1.equals(null));
        assertFalse(month1.equals(month2));
    }

    @Test
    public void toStringMethod() {
        DeleteMonth month = new DeleteMonth(VALID_DELETE_MONTH);

        assertEquals(VALID_DELETE_MONTH, month.toString());
    }
}
