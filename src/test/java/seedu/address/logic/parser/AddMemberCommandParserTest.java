package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMembers.BOB_MEMBER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.model.person.Member;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.MemberBuilder;

public class AddMemberCommandParserTest {
    private AddMemberCommandParser parser = new AddMemberCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Member expectedMember = new MemberBuilder(BOB_MEMBER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddMemberCommand(expectedMember));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedMemberString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_BOB + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_BOB + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_BOB + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple telegrams
        assertParseFailure(parser, TELEGRAM_DESC_BOB + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedMemberString + PHONE_DESC_BOB + NAME_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                        + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid phone
        assertParseFailure(parser, PHONE_DESC_BOB + INVALID_NAME_DESC + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PHONE));

        // invalid email
        assertParseFailure(parser, EMAIL_DESC_BOB + INVALID_NAME_DESC + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_EMAIL));

        // invalid telegram
        assertParseFailure(parser, TELEGRAM_DESC_BOB + INVALID_NAME_DESC + validExpectedMemberString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_TELEGRAM));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedMemberString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid phone
        assertParseFailure(parser, validExpectedMemberString + PHONE_DESC_BOB + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PHONE));

        // invalid email
        assertParseFailure(parser, validExpectedMemberString + EMAIL_DESC_BOB + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_EMAIL));

        // invalid telegram
        assertParseFailure(parser, validExpectedMemberString + TELEGRAM_DESC_BOB + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_TELEGRAM));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Member expectedMember = new MemberBuilder(BOB_MEMBER).withTags().build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddMemberCommand(expectedMember));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                "Missing fields: %s is missing!\n" + AddMemberCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TAG_FRIEND + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB,
                String.format(expectedMessage, "Name"));

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_TAG_FRIEND + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB,
                String.format(expectedMessage, "Phone"));

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_TAG_FRIEND + TELEGRAM_DESC_BOB,
                String.format(expectedMessage, "Email"));

        // missing telegram prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_TAG_FRIEND,
                String.format(expectedMessage, "Telegram"));

        // all prefixes missing
        assertParseFailure(parser, VALID_TAG_FRIEND + VALID_TAG_HUSBAND,
                String.format(expectedMessage, "Name, Phone, Email, Telegram"));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + TELEGRAM_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid telegram
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_TELEGRAM_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Telegram.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TELEGRAM_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        "Missing fields:  is missing!\n" + AddMemberCommand.MESSAGE_USAGE));
    }
}
