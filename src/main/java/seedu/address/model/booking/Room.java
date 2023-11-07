package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a hotel room that a client is staying in.
 */
public class Room {
    public static final String VALIDATION_REGEX = "\\d{1,3}";
    public static final String MESSAGE_CONSTRAINTS = "Room numbers are between 1 and 500 inclusive.";
    public static final int NUMBER_OF_NORMAL_ROOMS = 100;
    public static final int NUMBER_OF_STUDIO_ROOMS = 100;
    public static final int NUMBER_OF_DELUXE_ROOMS = 100;
    public static final int NUMBER_OF_SUITES = 100;
    public static final int NUMBER_OF_PRESIDENTIAL_SUITES = 100;

    /**
     * Represents the different types of rooms available in the hotel.
     * <p>
     * The hotel provides various room types ranging from standard rooms (NORMAL) to more luxurious and exclusive
     * options like the Presidential Suites. Each room type is associated with a specific range of room numbers.
     * This mapping ensures that, based on a room number, one can determine the category of the room.
     * </p>
     * <ul>
     *     <li>NORMAL rooms are numbered from 1 to 100.</li>
     *     <li>STUDIO rooms range from 101 to 200.</li>
     *     <li>DELUXE rooms span from 201 to 300.</li>
     *     <li>SUITES are between 301 and 400.</li>
     *     <li>PRESIDENTIAL_SUITE rooms range from 401 to 500.</li>
     * </ul>
     */
    public enum RoomType {
        NORMAL,
        STUDIO,
        DELUXE,
        SUITES,
        PRESIDENTIAL_SUITE;

        private static final Map<Integer, RoomType> ROOM_MAP = new HashMap<>();

        static { // The room types are organised by their room numbers.
            for (int i = 1; i <= 100; i++) { //Rooms 1-100 are normal rooms.
                ROOM_MAP.put(i, NORMAL);
            }
            for (int i = 101; i <= 200; i++) { //Rooms 101 to 200 are studio rooms.
                ROOM_MAP.put(i, STUDIO);
            }
            for (int i = 201; i <= 300; i++) { //Rooms 201 to 300 are deluxe rooms.
                ROOM_MAP.put(i, DELUXE);
            }
            for (int i = 301; i <= 400; i++) { //Rooms 301 to 400 are suites.
                ROOM_MAP.put(i, SUITES);
            }
            for (int i = 401; i <= 500; i++) { //Rooms 401 to 500 are presidential suites.
                ROOM_MAP.put(i, PRESIDENTIAL_SUITE);
            }
        }

        /**
         * Returns the {@code RoomType} corresponding to the provided room number.
         *
         * @param roomNumber The room number whose type is to be determined.
         * @return The type of the room or {@code null} if the room number does not correspond to any type.
         */
        public static RoomType getRoomTypeByNumber(int roomNumber) {
            return ROOM_MAP.getOrDefault(roomNumber, null);
        }
    }
    public final RoomTypeTag roomTypeTag;
    private final Integer value;
    private final RoomType type;

    /**
     * Constructs a Room object with the specified room number.
     *
     * @param value The room number.
     */
    public Room(String value) {
        requireNonNull(value);
        this.value = Integer.parseInt(value);
        this.type = RoomType.getRoomTypeByNumber(this.value);
        this.roomTypeTag = new RoomTypeTag(this.type.name());
    }

    /**
     * Checks if the room number is valid, which means it should be between 1 and 500 inclusive.
     *
     * @param test The room number to validate.
     * @return True if the room number is within the valid range, false otherwise.
     */
    public static boolean isValidRoom(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            int roomNumber = Integer.parseInt(test);
            return roomNumber >= 1 && roomNumber <= 500;
        }
        return false;
    }

    /**
     * This method returns the room type of the current room object.
     * @return The enum room type/
     */
    public RoomType getType() {
        return this.type;
    }

    /**
     * Checks if this Room is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if the same object
                || (other instanceof Room // instanceof handles nulls
                && value.equals(((Room) other).value)); // state check
    }
    /**
     * Method to get the room number
     * @return the room number
     */
    public int getRoomNumber() {
        return this.value;
    }

    /**
     * Returns a string representation of the room number.
     *
     * @return The room number as a string.
     */
    public String toString() {
        return String.valueOf(value);
    }
}
