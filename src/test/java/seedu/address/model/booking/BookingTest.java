package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_PERIOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.booking.exceptions.BookingPeriodNotFoundException;
import seedu.address.model.booking.exceptions.RoomNotFoundException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.EmailNotFoundException;
import seedu.address.model.person.exceptions.NameNotFoundException;
import seedu.address.model.person.exceptions.PhoneNotFoundException;
import seedu.address.testutil.BookingBuilder;

public class BookingTest {

    @Test
    public void equals() {
        // same values -> returns true
        Booking aliceCopy = new BookingBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Booking editedAlice = new BookingBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new BookingBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new BookingBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new BookingBuilder(ALICE).withBookingPeriod(VALID_BOOKING_PERIOD_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Booking.class.getCanonicalName() + "{room=" + ALICE.getRoom()
                + ", booking period=" + ALICE.getBookingPeriod()
                + ", name=" + ALICE.getName()
                + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail()
                + ", remark=" + ALICE.getRemark()
                + ", room type=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void testHashCodeEqualObjects() {
        // Create two equal Booking objects
        Booking booking1 = new BookingBuilder(ALICE).build();
        Booking booking2 = new BookingBuilder(ALICE).build();

        // Check that their hash codes are equal
        assertEquals(booking1.hashCode(), booking2.hashCode());
    }

    @Test
    public void testHashCodeNotEqualObjects() {
        // Create two different Booking objects
        Booking booking1 = new BookingBuilder(ALICE).build();
        Booking booking2 = new BookingBuilder(BOB).build();

        // Check that their hash codes are not equal
        assertNotEquals(booking1.hashCode(), booking2.hashCode());
    }

    @Test
    public void testRoomNotFoundException() {
        Room room = null;
        BookingPeriod bookingPeriod = new BookingPeriod("2023-10-11 12:00 to 2023-11-11 13:00");
        Name name = new Name("John Doe"); // Create Name object
        Phone phone = new Phone("1234567890"); // Create Phone object
        Email email = new Email("john@gmail.com"); // Create Email object
        Remark remark = new Remark("Test remark"); // Create Remark object

        assertThrows(RoomNotFoundException.class, () -> new Booking(room, bookingPeriod, name, phone, email, remark));
    }

    @Test
    public void testBookingPeriodNotFoundException() {
        Room room = new Room("101"); // Create Room object
        BookingPeriod bookingPeriod = null;
        Name name = new Name("John Doe"); // Create Name object
        Phone phone = new Phone("1234567890"); // Create Phone object
        Email email = new Email("john@gmail.com"); // Create Email object
        Remark remark = new Remark("Test remark"); // Create Remark object

        assertThrows(BookingPeriodNotFoundException.class, () ->
                new Booking(room, bookingPeriod, name, phone, email, remark));
    }

    @Test
    public void testNameNotFoundException() {
        Room room = new Room("101"); // Create Room object
        BookingPeriod bookingPeriod = new BookingPeriod("2023-10-11 12:00 to 2023-11-11 13:00");
        Name name = null;
        Phone phone = new Phone("1234567890"); // Create Phone object
        Email email = new Email("john@gmail.com"); // Create Email object
        Remark remark = new Remark("Test remark"); // Create Remark object

        assertThrows(NameNotFoundException.class, () ->
                new Booking(room, bookingPeriod, name, phone, email, remark));
    }

    @Test
    public void testEmailNotFoundException() {
        Room room = new Room("101"); // Create Room object
        BookingPeriod bookingPeriod = new BookingPeriod("2023-10-11 12:00 to 2023-11-11 13:00");
        Name name = new Name("John Doe"); // Create Name object
        Phone phone = new Phone("1234567890"); // Create Phone object
        Email email = null;
        Remark remark = new Remark("Test remark"); // Create Remark object

        assertThrows(EmailNotFoundException.class, () ->
                new Booking(room, bookingPeriod, name, phone, email, remark));
    }

    @Test
    public void testPhoneNotFoundException() {
        Room room = new Room("101"); // Create Room object
        BookingPeriod bookingPeriod = new BookingPeriod("2023-10-11 12:00 to 2023-11-11 13:00");
        Name name = new Name("John Doe"); // Create Name object
        Phone phone = null;
        Email email = new Email("john@gmail.com"); // Create Email object
        Remark remark = new Remark("Test remark"); // Create Remark object

        assertThrows(PhoneNotFoundException.class, () ->
                new Booking(room, bookingPeriod, name, phone, email, remark));
    }
}
