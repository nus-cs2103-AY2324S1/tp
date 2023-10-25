package seedu.address.model.booking;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.booking.exceptions.BookingPeriodNotFoundException;
import seedu.address.model.booking.exceptions.RoomNotFoundException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.EmailNotFoundException;
import seedu.address.model.person.exceptions.NameNotFoundException;
import seedu.address.model.person.exceptions.PhoneNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {

    // Identity fields
    private final Room room;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final BookingPeriod bookingPeriod;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a Booking with the specified details.
     *
     * @param room The room associated with the booking.
     * @param bookingPeriod The booking period.
     * @param name The name of the person making the booking.
     * @param phone The phone number of the person making the booking.
     * @param email The email of the person making the booking.
     * @param tags The tags associated with the booking.
     * @throws RoomNotFoundException If the room is null.
     * @throws BookingPeriodNotFoundException If the booking period is null.
     * @throws NameNotFoundException If the name is null.
     * @throws PhoneNotFoundException If the phone is null.
     * @throws EmailNotFoundException If the email is null.
     */
    public Booking(Room room, BookingPeriod bookingPeriod, Name name, Phone phone, Email email, Set<Tag> tags) {
        if (room == null) {
            throw new RoomNotFoundException();
        }
        if (bookingPeriod == null) {
            throw new BookingPeriodNotFoundException();
        }
        if (name == null) {
            throw new NameNotFoundException();
        }
        if (phone == null) {
            throw new PhoneNotFoundException();
        }
        if (email == null) {
            throw new EmailNotFoundException();
        }

        this.room = room;
        this.bookingPeriod = bookingPeriod;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
    }

    public Room getRoom() {
        return this.room;
    }

    public Name getName() {
        return this.name;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Email getEmail() {
        return this.email;
    }

    public BookingPeriod getBookingPeriod() {
        return this.bookingPeriod;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    /**
     * Returns true if both bookings have the same name and room.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameBooking(Booking otherBooking) {
        return otherBooking != null
                && otherBooking.getRoom().equals(this.getRoom())
                && otherBooking.getName().equals(this.getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (other instanceof Booking) {
            Booking otherBooking = (Booking) other;
            return this.room.equals(otherBooking.room)
                    && this.bookingPeriod.equals(otherBooking.bookingPeriod)
                    && this.name.equals(otherBooking.name)
                    && this.phone.equals(otherBooking.phone)
                    && this.email.equals(otherBooking.email)
                    && this.tags.equals(otherBooking.tags);
        }
        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.room, this.bookingPeriod, this.name, this.phone, this.email, this.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("room", this.room)
                .add("booking period", this.bookingPeriod)
                .add("name", this.name)
                .add("phone", this.phone)
                .add("email", this.email)
                .add("tags", this.tags)
                .toString();
    }
}
