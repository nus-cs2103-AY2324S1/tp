package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.DUCK_RICE;
import static seedu.address.testutil.TypicalItems.NASI_LEMAK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;

public class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "";
    private static final String INVALID_PRICE = "5";

    private static final String VALID_NAME = NASI_LEMAK.getName().toString();
    private static final String VALID_PRICE = NASI_LEMAK.getPrice().toString();


    // Heuristic: Each Valid Input at Least Once in a Positive Test Case
    @Test
    public void toModelType_validItemDetails_returnsItem() throws Exception {
        JsonAdaptedItem item = new JsonAdaptedItem(NASI_LEMAK);
        assertEquals(NASI_LEMAK, item.toModelType());
    }

    @Test
    public void toModelType_validItemDetailsWithReviews_returnsItem() throws Exception {
        JsonAdaptedItem item = new JsonAdaptedItem(DUCK_RICE);
        assertEquals(DUCK_RICE, item.toModelType());
    }


    // Heuristic: No More Than One Invalid Input In A Test Case
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItem item =
                new JsonAdaptedItem(INVALID_NAME, VALID_PRICE, null);
        String expectedMessage = ItemName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null, VALID_PRICE, null);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                ItemName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedItem item =
                new JsonAdaptedItem(VALID_NAME, INVALID_PRICE, null);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, null, null);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }
}
