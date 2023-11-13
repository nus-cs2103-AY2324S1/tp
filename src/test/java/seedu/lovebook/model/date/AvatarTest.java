package seedu.lovebook.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AvatarTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Avatar(null));
    }

    @Test
    public void constructor_invalidAvatar_throwsIllegalArgumentException() {
        String invalidAvatar = "";
        assertThrows(IllegalArgumentException.class, () -> new Avatar(invalidAvatar));
    }

    @Test
    public void isValidAvatarTest() {
        // null avatar
        assertThrows(NullPointerException.class, () -> Avatar.isValidAvatar(null));

        // blank/non-numeric avatar values
        assertThrows(NumberFormatException.class, () -> Avatar.isValidAvatar("")); // empty string
        assertThrows(NumberFormatException.class, () -> Avatar.isValidAvatar(" ")); // spaces only
        assertThrows(NumberFormatException.class, () -> Avatar.isValidAvatar("hello")); // Non-numeric

        // Invalid Values [Applying Equivalence Partitioning]
        assertFalse(Avatar.isValidAvatar("12")); // Above upper bound
        assertFalse(Avatar.isValidAvatar("0")); // Below lower bound

        // Boundary Values [Applying Boundary Value Analysis]
        assertTrue(Avatar.isValidAvatar("1")); // Lower bound (inclusive)
        assertTrue(Avatar.isValidAvatar("9")); // Upper bound (inclusive)
    }

    @Test
    public void randomAvatarGeneratorTest() {
        // Test that the random avatar generator generates a valid avatar
        Avatar avatar = new Avatar();
        assertTrue(Integer.parseInt(avatar.getAvatarNumber()) >= 1
                && Integer.parseInt(avatar.getAvatarNumber()) <= 9);

        avatar = new Avatar();
        assertTrue(Integer.parseInt(avatar.getAvatarNumber()) >= 1
                && Integer.parseInt(avatar.getAvatarNumber()) <= 9);

        avatar = new Avatar();
        assertTrue(Integer.parseInt(avatar.getAvatarNumber()) >= 1
                && Integer.parseInt(avatar.getAvatarNumber()) <= 9);

        avatar = new Avatar();
        assertTrue(Integer.parseInt(avatar.getAvatarNumber()) >= 1
                && Integer.parseInt(avatar.getAvatarNumber()) <= 9);

        avatar = new Avatar();
        assertTrue(Integer.parseInt(avatar.getAvatarNumber()) >= 1
                && Integer.parseInt(avatar.getAvatarNumber()) <= 9);
    }

    @Test
    public void equals() {
        Avatar avatar = new Avatar("1");

        // same values -> returns true
        assertTrue(avatar.equals(new Avatar("1")));

        // same object -> returns true
        assertTrue(avatar.equals(avatar));

        // null -> returns false
        assertFalse(avatar.equals(null));

        // different types -> returns false
        assertFalse(avatar.equals(5.0f));

        // different values -> returns false
        assertFalse(avatar.equals(new Avatar("2")));
    }

    @Test
    public void hashCodeTest() {
        Avatar avatar = new Avatar("1");
        assertTrue(avatar.hashCode() == new Avatar("1").hashCode());
    }

}
