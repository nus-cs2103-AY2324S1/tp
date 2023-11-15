package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_NASI_LEMAK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_2;
import static seedu.address.testutil.TypicalItems.FRIED_RICE;
import static seedu.address.testutil.TypicalItems.NASI_LEMAK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class ItemTest {
    @Test
    public void isSameItem() {
        // same object -> returns true
        assertTrue(FRIED_RICE.isSameItem(FRIED_RICE));

        // null -> returns false
        assertFalse(FRIED_RICE.isSameItem(null));

        // different name, all other attributes same -> returns false
        Item editedAlice = new ItemBuilder(FRIED_RICE).withName(VALID_ITEM_NAME_NASI_LEMAK).build();
        assertFalse(FRIED_RICE.isSameItem(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Item editedBob = new ItemBuilder(NASI_LEMAK).withName(VALID_ITEM_NAME_NASI_LEMAK.toLowerCase()).build();
        assertTrue(NASI_LEMAK.isSameItem(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_ITEM_NAME_NASI_LEMAK + " ";
        editedBob = new ItemBuilder(NASI_LEMAK).withName(nameWithTrailingSpaces).build();
        assertFalse(NASI_LEMAK.isSameItem(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Item friedRiceCopy = new ItemBuilder(FRIED_RICE).build();
        assertTrue(FRIED_RICE.equals(friedRiceCopy));

        // same object -> returns true
        assertTrue(FRIED_RICE.equals(FRIED_RICE));

        // null -> returns false
        assertFalse(FRIED_RICE.equals(null));

        // different type -> returns false
        assertFalse(FRIED_RICE.equals(5));

        // different item -> returns false
        assertFalse(FRIED_RICE.equals(NASI_LEMAK));

        // different name -> returns false
        Item editedFriedRice = new ItemBuilder(FRIED_RICE).withName(VALID_ITEM_NAME_NASI_LEMAK).build();
        assertFalse(FRIED_RICE.equals(editedFriedRice));

        // different price -> returns false
        editedFriedRice = new ItemBuilder(FRIED_RICE).withPrice(VALID_ITEM_PRICE_2).build();
        assertFalse(FRIED_RICE.equals(editedFriedRice));
    }

    @Test
    public void toStringMethod() {
        String expected = Item.class.getCanonicalName() + "{itemName=" + FRIED_RICE.getName() + ", price="
                + FRIED_RICE.getPrice() + "}";
        assertEquals(expected, FRIED_RICE.toString());
    }

}
