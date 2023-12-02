package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void isValidRemark() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid remark
        assertFalse(Remark.isValidRemark("")); // empty string
        assertFalse(Remark.isValidRemark(" ")); // spaces only


        // valid remark
        assertTrue(Remark.isValidRemark("Google")); // alphabets only
        assertTrue(Remark.isValidRemark("12345")); // numbers only
        assertTrue(Remark.isValidRemark("3M@#$%^&{}")); // other characters
        assertTrue(Remark.isValidRemark("Interview@idk-where(11/12/23 1500), Java & python")); // long remark
    }

    @Test
    public void equals() {
        Remark remark = new Remark("Hello");

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // same values -> returns true
        Remark remarkCopy = new Remark(remark.value);
        assertTrue(remark.equals(remarkCopy));

        // different types -> returns false
        assertFalse(remark.equals(1));

        // null -> returns false
        assertFalse(remark.equals(null));

        // different remark -> returns false
        Remark differentRemark = new Remark("Bye");
        assertFalse(remark.equals(differentRemark));
    }
}

