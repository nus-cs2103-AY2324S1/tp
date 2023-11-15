package seedu.address.model.displayable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HouseInfoTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HouseInfo(null));
    }

    @Test
    public void constructor_invalidInfo_throwsIllegalArgumentException() {
        String invalidInfo = " ";
        assertThrows(IllegalArgumentException.class, () -> new HouseInfo(invalidInfo));
    }

    @Test
    public void isValidInfo() {
        // null info
        assertThrows(NullPointerException.class, () -> HouseInfo.isValidHouseInfo(null));

        // invalid info
        assertFalse(HouseInfo.isValidHouseInfo("")); // empty string
        assertFalse(HouseInfo.isValidHouseInfo(" ")); // spaces only

        // valid info
        assertTrue(HouseInfo.isValidHouseInfo("Nice view"));
        assertTrue(HouseInfo.isValidHouseInfo("-")); // one character
        assertTrue(HouseInfo
                .isValidHouseInfo("Leng Inc; 1234 Market St, San Francisco CA "
                        + "2349879; USA, very nice view")); // long address
    }

    @Test
    public void equals() {
        HouseInfo houseInfo = new HouseInfo("Valid houseInfo");
        // same values -> returns true
        assertTrue(houseInfo.equals(new HouseInfo("Valid houseInfo")));

        // same object -> returns true
        assertTrue(houseInfo.equals(houseInfo));

        // null -> returns false
        assertFalse(houseInfo.equals(null));

        // different types -> returns false
        assertFalse(houseInfo.equals(5.0f));

        // different values -> returns false
        assertFalse(houseInfo.equals(new HouseInfo("Other Valid ")));
    }
}
