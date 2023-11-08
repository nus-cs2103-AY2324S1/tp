package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class RemarkTest {
    private static final String VALID_REMARK = "Likes to swim";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void toStringTest_success() {
        Remark remark = new Remark(VALID_REMARK);
        assert remark.toString().equals(VALID_REMARK);
    }

    @Test
    public void equals() {
        Remark remark = new Remark(VALID_REMARK);
        // different values -> returns false
        assertTrue(!remark.equals(new Remark("Other Valid Remark")));

        // same values -> returns true
        assertTrue(remark.equals(new Remark(VALID_REMARK)));

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // null -> returns false
        assertTrue(!remark.equals(null));
    }

}
