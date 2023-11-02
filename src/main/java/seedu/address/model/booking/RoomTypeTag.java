package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a RoomType in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidRoomTypeTagName(String)}
 */
public class RoomTypeTag {

    public static final String MESSAGE_CONSTRAINTS = "Room type names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String roomTypeTagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param roomTypeName A valid tag name.
     */
    public RoomTypeTag(String roomTypeName) {
        requireNonNull(roomTypeName);
        checkArgument(isValidRoomTypeTagName(roomTypeName), MESSAGE_CONSTRAINTS);
        this.roomTypeTagName = roomTypeName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     *
     * @param test The string to test for validity as a tag name.
     * @return True if the string is a valid tag name, false otherwise.
     */
    public static boolean isValidRoomTypeTagName(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            return true;
        }

        for (Room.RoomType roomType : Room.RoomType.values()) {
            if (test.equalsIgnoreCase(roomType.name())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Indicates whether some other object is "equal to" this tag.
     *
     * @param other The reference object with which to compare.
     * @return True if the other object is equal to this tag, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoomTypeTag)) {
            return false;
        }

        RoomTypeTag otherTag = (RoomTypeTag) other;
        return roomTypeTagName.equals(otherTag.roomTypeTagName);
    }

    /**
     * Returns a hash code value for this room type.
     *
     * @return A hash code value for this room type.
     */
    @Override
    public int hashCode() {
        return roomTypeTagName.hashCode();
    }

    /**
     * Returns a string representation of this room type.
     *
     * @return A string representation of this room type, enclosed in square brackets.
     */
    public String toString() {
        return '[' + roomTypeTagName + ']';
    }

}
