package seedu.address.testutil;

import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingPeriod;
import seedu.address.model.booking.Remark;
import seedu.address.model.booking.Room;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building Person objects.
 */
public class BookingBuilder {
    public static final String DEFAULT_ROOM = "10";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_BOOKING_PERIOD = "2023-01-01 08:00 to 2023-01-02 12:00";
    public static final String DEFAULT_REMARK = "N/A";

    private Room room;
    private Name name;
    private Phone phone;
    private Email email;
    private BookingPeriod bookingPeriod;

    private Remark remark;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public BookingBuilder() {
        room = new Room(DEFAULT_ROOM);
        bookingPeriod = new BookingPeriod(DEFAULT_BOOKING_PERIOD);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        room = bookingToCopy.getRoom();
        bookingPeriod = bookingToCopy.getBookingPeriod();
        name = bookingToCopy.getName();
        phone = bookingToCopy.getPhone();
        email = bookingToCopy.getEmail();
        remark = bookingToCopy.getRemark();
    }

    /**
     * Sets the {@code Room} of the {@code Person} that we are building.
     */
    public BookingBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public BookingBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Booking Period} of the {@code Person} that we are building.
     */
    public BookingBuilder withBookingPeriod(String bookingPeriod) {
        this.bookingPeriod = new BookingPeriod(bookingPeriod);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public BookingBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public BookingBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public BookingBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Booking build() {
        return new Booking(room, bookingPeriod, name, phone, email, remark);
    }

}
