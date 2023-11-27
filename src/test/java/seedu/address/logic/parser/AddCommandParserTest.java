package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_PERIOD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_PERIOD_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BOOKING_PERIOD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_PERIOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Room;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.BookingBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Booking expectedBooking = new BookingBuilder(BOB).build();

        // whitespace only preamble

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ROOM_DESC_BOB + BOOKING_PERIOD_DESC_BOB
                + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB, new AddCommand(expectedBooking));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedPersonString = ROOM_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + BOOKING_PERIOD_DESC_BOB;

        //multiple rooms
        assertParseFailure(parser, ROOM_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROOM));

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, BOOKING_PERIOD_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BOOKING_PERIOD));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + ROOM_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + BOOKING_PERIOD_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROOM, PREFIX_NAME,
                        PREFIX_BOOKING_PERIOD, PREFIX_EMAIL,
                        PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid room
        assertParseFailure(parser, INVALID_ROOM_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROOM));
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_BOOKING_PERIOD_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BOOKING_PERIOD));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_ROOM_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROOM));

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + INVALID_BOOKING_PERIOD_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BOOKING_PERIOD));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Booking expectedBooking = new BookingBuilder(AMY).build();

        assertParseSuccess(parser, ROOM_DESC_AMY + NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + BOOKING_PERIOD_DESC_AMY,
                new AddCommand(expectedBooking));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing room prefix
        assertParseFailure(parser, VALID_ROOM_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BOOKING_PERIOD_DESC_BOB,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, ROOM_DESC_BOB + VALID_NAME_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + BOOKING_PERIOD_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, ROOM_DESC_BOB + NAME_DESC_BOB + VALID_PHONE_BOB
                        + EMAIL_DESC_BOB + BOOKING_PERIOD_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, ROOM_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB
                        + VALID_EMAIL_BOB + BOOKING_PERIOD_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, ROOM_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + VALID_BOOKING_PERIOD_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, ROOM_DESC_BOB + VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_BOOKING_PERIOD_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid room
        assertParseFailure(parser, INVALID_ROOM_DESC + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + BOOKING_PERIOD_DESC_BOB, Room.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, ROOM_DESC_BOB + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + BOOKING_PERIOD_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, ROOM_DESC_BOB + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + BOOKING_PERIOD_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, ROOM_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + BOOKING_PERIOD_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, ROOM_DESC_BOB + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_BOOKING_PERIOD_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ROOM_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + BOOKING_PERIOD_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
