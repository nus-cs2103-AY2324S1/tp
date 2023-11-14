package seedu.spendnsplit.logic.parser;

import static seedu.spendnsplit.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.INVALID_TELEGRAM_HANDLE_DESC;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.RESERVED_NAME_DESC_OTHERS;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.RESERVED_NAME_DESC_SELF;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static seedu.spendnsplit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.spendnsplit.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.spendnsplit.testutil.TypicalPersons.AMY;
import static seedu.spendnsplit.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.spendnsplit.logic.Messages;
import seedu.spendnsplit.logic.commands.AddPersonCommand;
import seedu.spendnsplit.model.person.Address;
import seedu.spendnsplit.model.person.Email;
import seedu.spendnsplit.model.person.Name;
import seedu.spendnsplit.model.person.Person;
import seedu.spendnsplit.model.person.Phone;
import seedu.spendnsplit.model.person.TelegramHandle;
import seedu.spendnsplit.model.tag.Tag;
import seedu.spendnsplit.testutil.PersonBuilder;

public class AddPersonCommandParserTest {
    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // whitespace only preamble
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // no phone
        expectedPerson = new PersonBuilder(BOB).withPhone(null).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // whitespace only preamble
        expectedPerson = new PersonBuilder(BOB).withTelegramHandle(null).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // no email
        expectedPerson = new PersonBuilder(BOB).withEmail(null).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // no address
        expectedPerson = new PersonBuilder(BOB).withAddress(null).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddPersonCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;

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
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY + EMAIL_DESC_AMY
                        + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL,
                        PREFIX_PHONE, PREFIX_TELEGRAM_HANDLE));

        // invalid value followed by valid value

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
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // valid value followed by invalid value

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
        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddPersonCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + TELEGRAM_HANDLE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid telegramHandle
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_TELEGRAM_HANDLE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, TelegramHandle.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_reservedValue_failure() {
        // self
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESERVED_NAME_DESC_SELF + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, String.format(Name.RESERVED_CONSTRAINTS, "Self"));

        // others
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESERVED_NAME_DESC_OTHERS + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, String.format(Name.RESERVED_CONSTRAINTS, "Others"));
    }
}
