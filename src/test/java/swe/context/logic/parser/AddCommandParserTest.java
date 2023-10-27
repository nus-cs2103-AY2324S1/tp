package swe.context.logic.parser;

import static swe.context.logic.parser.CliSyntax.PREFIX_EMAIL;
import static swe.context.logic.parser.CliSyntax.PREFIX_NAME;
import static swe.context.logic.parser.CliSyntax.PREFIX_NOTE;
import static swe.context.logic.parser.CliSyntax.PREFIX_PHONE;
import static swe.context.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swe.context.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.logic.commands.AddCommand;
import swe.context.model.contact.Contact;
import swe.context.testutil.ContactBuilder;
import swe.context.testutil.TestData;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // Various whitespace in front
        assertParseSuccess(
            parser,
                TestData.WHITESPACE
                    + TestData.Valid.NAME_DESC_AMY
                    + TestData.Valid.PHONE_DESC_AMY
                    + TestData.Valid.EMAIL_DESC_AMY
                    + TestData.Valid.NOTE_DESC_AMY
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
            new AddCommand(TestData.Valid.Contact.AMY)
        );

        // Multiple tags, "different" specified order
        assertParseSuccess(
                parser,
                TestData.Valid.NAME_DESC_BOB
                    + TestData.Valid.PHONE_DESC_BOB
                    + TestData.Valid.EMAIL_DESC_BOB
                    + TestData.Valid.NOTE_DESC_BOB
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC_SPACES
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
            new AddCommand(TestData.Valid.Contact.BOB)
        );
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = TestData.Valid.NAME_DESC_BOB + TestData.Valid.PHONE_DESC_BOB
                + TestData.Valid.EMAIL_DESC_BOB + TestData.Valid.NOTE_DESC_BOB + TestData.Valid.Tag.FLAG_ALPHANUMERIC;

        // multiple names
        assertParseFailure(parser, TestData.Valid.NAME_DESC_AMY + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, TestData.Valid.PHONE_DESC_AMY + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, TestData.Valid.EMAIL_DESC_AMY + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, TestData.Valid.NOTE_DESC_AMY + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString
                        + TestData.Valid.PHONE_DESC_AMY
                        + TestData.Valid.EMAIL_DESC_AMY
                        + TestData.Valid.NAME_DESC_AMY
                        + TestData.Valid.NOTE_DESC_AMY
                        + validExpectedPersonString,
                        ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_NOTE,
                                PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, TestData.Invalid.NAME_DESC + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, TestData.Invalid.EMAIL_DESC + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, TestData.Invalid.PHONE_DESC + validExpectedPersonString,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + TestData.Invalid.NAME_DESC,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + TestData.Invalid.EMAIL_DESC,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + TestData.Invalid.PHONE_DESC,
                ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Contact expectedContact = new ContactBuilder(TestData.Valid.Contact.AMY).withTags().build();
        assertParseSuccess(parser,
                TestData.Valid.NAME_DESC_AMY
                        + TestData.Valid.PHONE_DESC_AMY
                        + TestData.Valid.EMAIL_DESC_AMY
                        + TestData.Valid.NOTE_DESC_AMY,
                new AddCommand(expectedContact));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = Messages.commandInvalidFormat(AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                TestData.Valid.NAME_BOB
                        + TestData.Valid.PHONE_DESC_BOB
                        + TestData.Valid.EMAIL_DESC_BOB
                        + TestData.Valid.NOTE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
                TestData.Valid.NAME_DESC_BOB
                        + TestData.Valid.PHONE_BOB
                        + TestData.Valid.EMAIL_DESC_BOB
                        + TestData.Valid.NOTE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
                TestData.Valid.NAME_DESC_BOB
                        + TestData.Valid.PHONE_DESC_BOB
                        + TestData.Valid.EMAIL_BOB
                        + TestData.Valid.NOTE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser,
                TestData.Valid.NAME_DESC_BOB
                        + TestData.Valid.PHONE_DESC_BOB
                        + TestData.Valid.EMAIL_DESC_BOB
                        + TestData.Valid.NOTE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                TestData.Valid.NAME_BOB
                        + TestData.Valid.PHONE_BOB
                        + TestData.Valid.EMAIL_BOB
                        + TestData.Valid.NOTE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                TestData.Invalid.NAME_DESC
                        + TestData.Valid.PHONE_DESC_BOB
                        + TestData.Valid.EMAIL_DESC_BOB
                        + TestData.Valid.NOTE_DESC_BOB
                        + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
                Messages.NAME_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
                TestData.Valid.NAME_DESC_BOB
                        + TestData.Invalid.PHONE_DESC
                        + TestData.Valid.EMAIL_DESC_BOB
                        + TestData.Valid.NOTE_DESC_BOB
                        + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
                Messages.PHONE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
                TestData.Valid.NAME_DESC_BOB
                        + TestData.Valid.PHONE_DESC_BOB
                        + TestData.Invalid.EMAIL_DESC
                        + TestData.Valid.NOTE_DESC_BOB
                        + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
                Messages.EMAIL_INVALID);

        // invalid tag
        assertParseFailure(
            parser,
                TestData.Valid.NAME_DESC_BOB
                    + TestData.Valid.PHONE_DESC_BOB
                    + TestData.Valid.EMAIL_DESC_BOB
                    + TestData.Valid.NOTE_DESC_BOB
                    + TestData.Invalid.Tag.FLAG_HASHTAG,
            Messages.tagInvalid(TestData.Invalid.Tag.HASHTAG)
        );

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                TestData.Invalid.NAME_DESC
                        + TestData.Invalid.PHONE_DESC
                        + TestData.Valid.EMAIL_DESC_BOB
                        + TestData.Valid.NOTE_DESC_BOB,
                Messages.NAME_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                TestData.EXTRA_WORDS
                        + TestData.Valid.NAME_DESC_BOB
                        + TestData.Valid.PHONE_DESC_BOB
                        + TestData.Valid.EMAIL_DESC_BOB
                        + TestData.Valid.NOTE_DESC_BOB
                        + TestData.Valid.Tag.FLAG_ALPHANUMERIC,
                Messages.commandInvalidFormat(AddCommand.MESSAGE_USAGE));
    }
}
