package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelehandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telehandle(null));
    }

    @Test
    public void constructor_invalidTelehandle_throwsIllegalArgumentException() {
        String invalidTelehandle = "amyyyy"; // no @ infront
        assertThrows(IllegalArgumentException.class, () -> new Telehandle(invalidTelehandle));
    }


    @Test
    public void isValidTelehandle() {
        // invalid telehandle
        assertFalse(Telehandle.isValidTelehandle("marylovejinheng")); // no @.
        assertFalse(Telehandle.isValidTelehandle("asdasd@asdasd")); // @ in the middle.
        assertFalse(Telehandle.isValidTelehandle("@marylove jinheng")); // white spaces.
        assertFalse(Telehandle.isValidTelehandle(" ")); // empty telehandle.
        assertFalse(Telehandle.isValidTelehandle("@")); // cannot consist of only @ sign.
        assertFalse(Telehandle.isValidTelehandle("@A*!@~")); // no other special characters apart from underscores.
        assertFalse(Telehandle.isValidTelehandle("@_")); // telehandle cannot consist of only underscores.
        assertFalse(Telehandle.isValidTelehandle("@111")); // telehandle cannot consist of only numbers.
        assertFalse(Telehandle.isValidTelehandle("@_11")); // cannot consist of only numbers and underscores.

        // valid telehandles
        assertTrue(Telehandle.isValidTelehandle("@1nice")); // numbers at the start of a telehandle
        assertTrue(Telehandle.isValidTelehandle("@nice_")); // underscore as a special character are allowed.
        assertTrue(Telehandle.isValidTelehandle("@HiIamACSstudenthihihiilonggggggname")); // long telehandle
        assertTrue(Telehandle.isValidTelehandle("@_nice_")); // underscore at the start of telehandle are allowed.
        assertTrue(Telehandle.isValidTelehandle("@_1nice_")); // combination of underscore and numbers are allowed.

        // Boundary Value Testing
        Telehandle minBoundaryTelehandle = new Telehandle("@A");
        assertTrue(Telehandle.isValidTelehandle(minBoundaryTelehandle.toString())); // Minimum length Telehandle

        Telehandle singleCharWithUnderscoreTelehandle = new Telehandle("@A_");
        assertTrue(Telehandle.isValidTelehandle(singleCharWithUnderscoreTelehandle
                .toString())); // Single character with underscore Telehandle

        Telehandle multipleCharTelehandle = new Telehandle("@HelloWorld_");
        assertTrue(Telehandle.isValidTelehandle(multipleCharTelehandle.toString())); // Multiple character Telehandle
    }

    @Test
    public void equals() {
        Telehandle telehandle = new Telehandle("@ValidTelehandle");

        // same values -> returns true
        assertTrue(telehandle.equals(new Telehandle("@ValidTelehandle")));

        // same object -> returns true
        assertTrue(telehandle.equals(telehandle));

        // null -> returns false
        assertFalse(telehandle.equals(null));

        // different types -> returns false
        assertFalse(telehandle.equals(5.0f));

        // different values -> returns false
        assertFalse(telehandle.equals(new Telehandle("@OtherValidTelehandle")));
    }
}
