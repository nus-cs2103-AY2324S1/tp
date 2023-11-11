package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedBooking.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.BookingPeriod;
import seedu.address.model.booking.Room;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedBookingTest {
    private static final String INVALID_ROOM_WITH_3_DIGITS = "501";
    private static final String INVALID_ROOM_WITH_4_DIGITS = "1501";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_REMARK = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String INVALID_TAG = "#friend";
    private static final String VALID_ROOM = BENSON.getRoom().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getBookingPeriod().toString();

    private static final String VALID_REMARK = BENSON.getRemark().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedBooking person = new JsonAdaptedBooking(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidRoomWith3Digits_throwsIllegalValueException() {
        JsonAdaptedBooking person =
                new JsonAdaptedBooking(INVALID_ROOM_WITH_3_DIGITS, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK);
        String expectedMessage = Room.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidRoomWith4Digits_throwsIllegalValueException() {
        JsonAdaptedBooking person =
                new JsonAdaptedBooking(INVALID_ROOM_WITH_4_DIGITS, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK);
        String expectedMessage = Room.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBooking person =
                new JsonAdaptedBooking(VALID_ROOM, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedBooking person = new JsonAdaptedBooking(VALID_ROOM, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedBooking person =
                new JsonAdaptedBooking(VALID_ROOM, VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedBooking person = new JsonAdaptedBooking(VALID_ROOM, VALID_NAME, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedBooking person =
                new JsonAdaptedBooking(VALID_ROOM, VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_REMARK);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedBooking person = new JsonAdaptedBooking(VALID_ROOM, VALID_NAME,
                VALID_PHONE, null, VALID_ADDRESS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedBooking person =
                new JsonAdaptedBooking(VALID_ROOM, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_REMARK);
        String expectedMessage = BookingPeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedBooking person = new JsonAdaptedBooking(VALID_ROOM, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BookingPeriod.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRemark_throwsIllegalValueException() {
        JsonAdaptedBooking person =
                new JsonAdaptedBooking(VALID_ROOM, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_REMARK);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
