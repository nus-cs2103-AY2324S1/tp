package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
