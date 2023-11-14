package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        // empty string
        assertThrows(IllegalArgumentException.class, () -> new Title(""));
        // spaces only
        assertThrows(IllegalArgumentException.class, () -> new Title(" "));
        // starts with a whitespace
        assertThrows(IllegalArgumentException.class, () -> new Title(" do work"));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // invalid titles
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only
        assertFalse(Title.isValidTitle(" do work")); // starts with a whitespace

        // valid titles
        assertTrue(Title.isValidTitle("^")); // only non-alphanumeric characters
        assertTrue(Title.isValidTitle("SELECT*")); // contains non-alphanumeric characters
        assertTrue(Title.isValidTitle("do work")); // alphabets only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("submit by the 2nd")); // alphanumeric characters
        assertTrue(Title.isValidTitle("CS2103")); // with capital letters
        assertTrue(Title.isValidTitle("CoordiMate for Event Planners of CS2103")); // long titles
        assertTrue(Title.isValidTitle("\uD83D\uDC4B\uD83C\uDFFB \uD83C\uDDF8\uD83C\uDDEC")); // emojis
        assertTrue(Title.isValidTitle("(ノಠ益ಠ)ノ彡┻━┻")); // non-English characters
    }

    @Test
    public void equals() {
        Title title = new Title("Valid Title");

        // same values -> returns true
        assertEquals(title, new Title("Valid Title"));

        // same object -> returns true
        assertEquals(title, title);

        // null -> returns false
        // assertNotEquals calls the 1st argument's equals method
        assertNotEquals(title, null);

        // different types -> returns false
        assertNotEquals(title, 5.0f);

        // different values -> returns false
        assertNotEquals(title, new Title("Other Valid Title"));
    }

    @Test
    public void toStringMethod() {
        Title title = new Title("Valid Title");
        assertEquals("Valid Title", title.toString());
    }

    @Test
    public void hashCodeTest() {
        Title title = new Title("Valid Title");

        // same title -> same hashcode
        assertEquals(title.hashCode(), new Title("Valid Title").hashCode());

        // different title -> different hashcode
        assertNotEquals(title.hashCode(), new Title("Other Valid Title").hashCode());

        // different types -> different hashcode
        assertNotEquals(title.hashCode(), "Test Title".hashCode());

    }
}
