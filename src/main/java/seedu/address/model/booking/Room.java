package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;

/**
 * This class represents the hotel room that the client is staying in.
 */
public class Room {
    public final Integer value;

    /**
     * This is the constructor of the room
     * @param value
     */
    public Room(String value) {
        requireNonNull(value);
        this.value = Integer.parseInt(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Room // instanceof handles nulls
                && value.equals(((Room) other).value)); // state check
    }
    public String toString() {
        return "" + value;
    }

    /**
     * Checks if the room number is between 1 and 500 inclusive
     * @param number
     * @return
     */
    public static boolean isValidRoom(String number) {
        int roomNumber = Integer.parseInt(number);
        if (roomNumber <= 0 | roomNumber > 500) {
            return false;
        }
        return true;
    }


}
