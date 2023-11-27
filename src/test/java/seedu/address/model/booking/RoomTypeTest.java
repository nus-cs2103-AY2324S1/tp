package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomTypeTest {
    @Test
    public void constructor_givesCorrectType() {
        Room normalRoom = new Room("100");
        Room studioRoom = new Room("200");
        Room dexluxeRoom = new Room("300");
        Room suite = new Room("400");
        Room presidentialSuite = new Room("500");
        assertEquals(normalRoom.getType(), Room.RoomType.NORMAL);
        assertEquals(studioRoom.getType(), Room.RoomType.STUDIO);
        assertEquals(dexluxeRoom.getType(), Room.RoomType.DELUXE);
        assertEquals(suite.getType(), Room.RoomType.SUITES);
        assertEquals(presidentialSuite.getType(), Room.RoomType.PRESIDENTIAL_SUITE);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomTypeTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new RoomTypeTag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> RoomTypeTag.isValidRoomTypeTagName(null));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        RoomTypeTag roomTypeTag = new RoomTypeTag("NORMAL");
        assertTrue(roomTypeTag.equals(roomTypeTag));
    }

    @Test
    public void equals_null_returnsFalse() {
        RoomTypeTag roomTypeTag = new RoomTypeTag("test");
        assertFalse(roomTypeTag.equals(null));
    }
}
