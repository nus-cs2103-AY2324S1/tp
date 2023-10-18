package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestData.Valid.EMAIL_DESC_AMY;
import static seedu.address.testutil.TestData.Valid.EMAIL_DESC_BOB;
import static seedu.address.testutil.TestData.Invalid.EMAIL_DESC;
import static seedu.address.testutil.TestData.Invalid.NAME_DESC;
import static seedu.address.testutil.TestData.Invalid.PHONE_DESC;
import static seedu.address.testutil.TestData.Valid.NAME_DESC_AMY;
import static seedu.address.testutil.TestData.Valid.NAME_DESC_BOB;
import static seedu.address.testutil.TestData.Valid.NOTE_DESC_AMY;
import static seedu.address.testutil.TestData.Valid.NOTE_DESC_BOB;
import static seedu.address.testutil.TestData.Valid.PHONE_DESC_AMY;
import static seedu.address.testutil.TestData.Valid.PHONE_DESC_BOB;
import static seedu.address.testutil.TestData.Valid.EMAIL_BOB;
import static seedu.address.testutil.TestData.Valid.NAME_BOB;
import static seedu.address.testutil.TestData.Valid.NOTE_BOB;
import static seedu.address.testutil.TestData.Valid.PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.TestData;



public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // Various whitespace in front
        assertParseSuccess(
            parser,
            TestData.WHITESPACE
                    + NAME_DESC_AMY
                    + PHONE_DESC_AMY
                    + EMAIL_DESC_AMY
                    + NOTE_DESC_AMY
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
            new AddCommand(TestData.Valid.Contact.AMY)
        );

        // Multiple tags, "different" specified order
        assertParseSuccess(parser,
            NAME_DESC_BOB
                    + PHONE_DESC_BOB
                    + EMAIL_DESC_BOB
                    + NOTE_DESC_BOB
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC_SPACES
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
            new AddCommand(TestData.Valid.Contact.BOB)
        );
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NOTE_DESC_BOB + TestData.Valid.Tag.FLAG_ALPHANUMERIC;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, NOTE_DESC_AMY + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + NOTE_DESC_AMY
                        + validExpectedPersonString,
                        ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_NOTE,
                                PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, NAME_DESC + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, EMAIL_DESC + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, PHONE_DESC + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + NAME_DESC,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + EMAIL_DESC,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + PHONE_DESC,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Contact expectedContact = new ContactBuilder(TestData.Valid.Contact.AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + NOTE_DESC_AMY,
                new AddCommand(expectedContact));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NOTE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_BOB + EMAIL_DESC_BOB + NOTE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_BOB + NOTE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NOTE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, NAME_BOB + PHONE_BOB + EMAIL_BOB + NOTE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + NOTE_DESC_BOB
                + TestData.Valid.Tag.FLAG_ALPHANUMERIC, Messages.MESSAGE_NAME_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC + EMAIL_DESC_BOB + NOTE_DESC_BOB
                + TestData.Valid.Tag.FLAG_ALPHANUMERIC, Messages.MESSAGE_PHONE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC + NOTE_DESC_BOB
                + TestData.Valid.Tag.FLAG_ALPHANUMERIC, Messages.MESSAGE_EMAIL_CONSTRAINTS);

        // invalid tag
        assertParseFailure(
            parser,
            NAME_DESC_BOB
                    + PHONE_DESC_BOB
                    + EMAIL_DESC_BOB
                    + NOTE_DESC_BOB
                    + TestData.Invalid.Tag.FLAG_HASHTAG,
            Messages.tagInvalid(TestData.Invalid.Tag.HASHTAG)
        );

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, NAME_DESC + PHONE_DESC + EMAIL_DESC_BOB + NOTE_DESC_BOB,
                Messages.MESSAGE_NAME_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, TestData.EXTRA_WORDS + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NOTE_DESC_BOB + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
