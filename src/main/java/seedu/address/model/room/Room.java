package seedu.address.model.room;


/**
 * Represents a room.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Room {
    private final int roomNumber;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Return the room number of this room.
     *
     * @return roomNumber.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns true if both rooms have the same roomNumber.
     */
    public boolean isSameRoom(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null
                && otherRoom.getRoomNumber() == getRoomNumber();
    }

    @Override
    public String toString() {
        return "Room " + roomNumber;
    }
}
