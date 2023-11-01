package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void equals() {
        Remark remark = new Remark("remark_test");

        // same values -> return true
        assertTrue(remark.equals(new Remark("remark_test")));

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // null -> returns false
        assertFalse(remark.equals(null));

        // different types -> return false
        assertFalse(remark.equals(0.4));

        // different values -> returns false
        assertFalse(remark.equals(new Remark("other_remark_that_is_different")));
    }
}
