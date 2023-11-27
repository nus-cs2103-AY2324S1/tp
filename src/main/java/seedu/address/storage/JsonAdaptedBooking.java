package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingPeriod;
import seedu.address.model.booking.Remark;
import seedu.address.model.booking.Room;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Booking}.
 */
class JsonAdaptedBooking {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final String room;
    private final String name;
    private final String phone;
    private final String email;
    private final String remark;
    private final String bookingPeriod;
    /**
     * Constructs a {@code JsonAdaptedBooking} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("room") String room, @JsonProperty("name") String name,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("bookingPeriod") String bookingPeriod,
                              @JsonProperty("remark") String remark) {
        this.room = room;
        this.bookingPeriod = bookingPeriod;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source) {
        room = String.valueOf(source.getRoom().getRoomNumber());
        bookingPeriod = source.getBookingPeriod().value;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Booking toModelType() throws IllegalValueException {
        if (room == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Room.class.getSimpleName()));
        }
        if (!Room.isValidRoom(room)) {
            throw new IllegalValueException(Room.MESSAGE_CONSTRAINTS);
        }
        final Room modelRoom = new Room(room);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (bookingPeriod == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BookingPeriod.class.getSimpleName()));
        }
        if (!BookingPeriod.isValidBookingPeriod(bookingPeriod)) {
            throw new IllegalValueException(BookingPeriod.MESSAGE_CONSTRAINTS);
        }
        final BookingPeriod modelBookingPeriod = new BookingPeriod(bookingPeriod);
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        final Remark modelRemark = new Remark(remark);
        Booking booking = new Booking(modelRoom, modelBookingPeriod, modelName, modelPhone, modelEmail, modelRemark);
        return booking;
    }
}
