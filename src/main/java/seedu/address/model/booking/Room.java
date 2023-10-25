package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;

/**
 * Represents a hotel room that a client is staying in.
 */
public class Room {
    public static final String MESSAGE_CONSTRAINTS = "Room must be a number between 1 and 500 inclusive.";

    public final Integer value;

    /**
     * Constructs a Room object with the specified room number.
     *
     * @param value The room number.
     */
    public Room(String value) {
        requireNonNull(value);
        this.value = Integer.parseInt(value);
    }

    /**
     * Checks if the room number is valid, which means it should be between 1 and 500 inclusive.
     *
     * @param number The room number to validate.
     * @return True if the room number is within the valid range, false otherwise.
     */
    public static boolean isValidRoom(String number) {
        int roomNumber = Integer.parseInt(number);
        return roomNumber < 1 || roomNumber > 500;
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
     * Returns a string representation of the room number.
     *
     * @return The room number as a string.
     */
    public String toString() {
        return String.valueOf(value);
    }
}
