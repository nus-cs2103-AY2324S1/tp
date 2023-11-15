package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ItemNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemName(null));
    }

    @Test
    public void constructor_invalidItemName_throwsIllegalArgumentException() {
        String invalidItemName = "";
        assertThrows(IllegalArgumentException.class, () -> new ItemName(invalidItemName));
    }

    @Test
    public void isValidItemName() {
        // null item name
        assertThrows(NullPointerException.class, () -> ItemName.isValidItemName(null));

        // invalid item name
        assertFalse(ItemName.isValidItemName("")); // empty string
        assertFalse(ItemName.isValidItemName(" ")); // spaces only

        // valid item name
        assertTrue(ItemName.isValidItemName("chicken rice")); // alphabets only
        assertTrue(ItemName.isValidItemName("12345")); // numbers only
        assertTrue(ItemName.isValidItemName("chicken rice 2")); // alphanumeric characters
        assertTrue(ItemName.isValidItemName("Chicken Rice")); // with capital letters
        assertTrue(ItemName.isValidItemName("Chicken noodle soup with a soda on the side")); // long names
        assertTrue(ItemName.isValidItemName("^")); // only non-alphanumeric characters
        assertTrue(ItemName.isValidItemName("chicken rice*")); // contains non-alphanumeric characters
    }

    @Test
    public void equals() {
        ItemName itemName = new ItemName("Chicken Rice");

        // same values -> returns true
        assertTrue(itemName.equals(new ItemName("Chicken Rice")));

        // same object -> returns true
        assertTrue(itemName.equals(itemName));

        // null -> returns false
        assertFalse(itemName.equals(null));

        // different types -> returns false
        assertFalse(itemName.equals(5.0f));

        // different values -> returns false
        assertFalse(itemName.equals(new ItemName("Nasi Lemak")));
    }
}
