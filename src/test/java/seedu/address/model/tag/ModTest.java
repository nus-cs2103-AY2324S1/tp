package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mod(null));
    }

    @Test
    public void constructor_invalidModName_throwsIllegalArgumentException() {
        String invalidModName = "";
        assertThrows(IllegalArgumentException.class, () -> new Mod(invalidModName));
    }

    @Test
    public void isValidModName() {
        // null mod name
        assertThrows(NullPointerException.class, () -> Mod.isValidModName(null));
    }
}
