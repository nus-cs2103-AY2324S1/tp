package seedu.address.model.booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.person.Person;
import seedu.address.model.room.Room;

/**
 * Booking that links a person and a room.
 */
public class Booking {
    private final Room room;
    private final Person person;
    private final LocalDateTime dateTime;
    private final long id;

    /**
     * Constructor for Booking.
     *
     * @param room     associated with the booking.
     * @param person   associated with the booking.
     * @param dateTime of the booking.
     */
    public Booking(Room room, Person person, LocalDateTime dateTime) {
        this.room = room;
        this.person = person;
        this.dateTime = dateTime;
        this.id = generateId();

    }

    /**
     * Generates a unique booking id using
     * the details.
     */
    private long generateId() {
        int roomNumber = room.getRoomNumber();

        // Convert LocalDateTime to a unique string, e.g., YYYYMMDDHHMM
        String dateTimeStr = String.format("%04d%02d%02d%02d%02d",
                dateTime.getYear(),
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getHour(),
                dateTime.getMinute());

        // Concatenate room number and dateTimeStr
        String uniqueIdStr = String.valueOf(roomNumber) + dateTimeStr;

        // Convert to long
        long uniqueId = Long.parseLong(uniqueIdStr);

        return uniqueId;
    }

    /**
     * Returns the date and time in the format "yyyy-MM-dd hh:mm a".
     *
     * @return A string representing the formatted date and time.
     */
    public String getDateTime() {
        // Create a DateTimeFormatter object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        // Format the LocalDateTime to a string
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }

    /**
     * Check if two bookings are the same using id.
     */
    public boolean isSameBooking(Booking otherBooking) {
        if (otherBooking == this) {
            return true;
        }

        return otherBooking != null
                && otherBooking.id == id;
    }

    @Override
    public String toString() {
        return "Booking " + id + "\nBy " + person.getName() + "\nAt " + getDateTime();
    }
}
