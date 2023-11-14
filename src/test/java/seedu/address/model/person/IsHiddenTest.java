package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsHiddenTest {

    @Test
    public void equals() {
        IsHidden isHidden = new IsHidden(true);
        assertTrue(isHidden.equals(new IsHidden(true)));
        assertTrue(isHidden.equals(isHidden));
        assertFalse(isHidden.equals(null));
        assertFalse(isHidden.equals("Test"));
        assertFalse(isHidden.equals(new IsHidden(false)));
    }
}
