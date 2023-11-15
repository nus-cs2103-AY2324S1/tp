package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DATES_NOT_COMPATIBLE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELDS_FOR_ADD_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELDS_POLICY_FOR_ADD_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_PREAMBLE_DETECTED;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LICENCE_PLATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LICENCE_PLATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_EXPIRY_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_EXPIRY_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_ISSUE_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_ISSUE_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NO_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NO_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LICENCE_PLATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_ISSUE_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FRIEND + COMPANY_DESC_BOB + POLICY_NO_DESC_BOB
                + POLICY_ISSUE_DATE_DESC_BOB
                + POLICY_EXPIRY_DATE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB
                        + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                        + COMPANY_DESC_BOB + POLICY_NO_DESC_BOB + POLICY_ISSUE_DATE_DESC_BOB
                        + POLICY_EXPIRY_DATE_DESC_BOB,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;

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
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                        PREFIX_NRIC, PREFIX_LICENCE_PLATE));

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
    public void parse_allOptionalFieldsPresent_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + NRIC_DESC_AMY + LICENCE_PLATE_DESC_AMY + ADDRESS_DESC_AMY + COMPANY_DESC_AMY
                        + POLICY_NO_DESC_AMY + POLICY_ISSUE_DATE_DESC_AMY + POLICY_EXPIRY_DATE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_someButNotAllOptionalFieldsPresent_failure() {
        String errorMessage = MESSAGE_MISSING_FIELDS_POLICY_FOR_ADD_COMMAND;

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage);

        // missing company
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_FRIEND + POLICY_NO_DESC_BOB
                        + POLICY_ISSUE_DATE_DESC_BOB + POLICY_EXPIRY_DATE_DESC_BOB,
                expectedMessage + "- Company(c/) ");

        // missing policy number
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_FRIEND + COMPANY_DESC_BOB
                        + POLICY_ISSUE_DATE_DESC_BOB + POLICY_EXPIRY_DATE_DESC_BOB,
                expectedMessage + "- Policy Number(pn/) ");

        // missing policy issue date
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_FRIEND + COMPANY_DESC_BOB
                        + POLICY_NO_DESC_BOB + POLICY_EXPIRY_DATE_DESC_BOB,
                expectedMessage + "- Policy Issue Date(pi/) ");

        // missing policy expiry date
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_FRIEND + COMPANY_DESC_BOB
                        + POLICY_NO_DESC_BOB + POLICY_ISSUE_DATE_DESC_BOB,
                expectedMessage + "- Policy Expiry Date(pe/) ");
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String errorMessage = MESSAGE_MISSING_FIELDS_FOR_ADD_COMMAND;

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage);

        // missing name prefix
        assertParseFailure(parser,
                PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage + "- Name(n/) ");

        // missing phone prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage + "- Phone(p/) ");

        // missing email prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + NRIC_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage + "- Email(e/) ");

        // missing nric prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage + "- NRIC(i/) ");

        //missing licence plate prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage + "- Licence Plate(l/) ");

        // missing address prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + NRIC_DESC_BOB + EMAIL_DESC_BOB + LICENCE_PLATE_DESC_BOB,
                expectedMessage + "- Address(a/) ");

        // all prefixes missing
        assertParseFailure(parser, "",
                expectedMessage + "- Name(n/) - Phone(p/) - Email(e/) - NRIC(i/) - Licence Plate(l/) - Address(a/) ");
    }

    @Test
    public void parse_preambleExist_failure() {
        String validInput = NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NRIC_DESC_AMY + LICENCE_PLATE_DESC_AMY + ADDRESS_DESC_AMY + COMPANY_DESC_AMY
                + POLICY_NO_DESC_AMY + POLICY_ISSUE_DATE_DESC_AMY + POLICY_EXPIRY_DATE_DESC_AMY;

        String inputWithPreamble = "some words" + validInput;

        assertParseFailure(parser, inputWithPreamble,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_PREAMBLE_DETECTED));
    }

    @Test
    public void parse_incompatiblePolicyDates_failure() {
        String incompatiblePolicyDates = " " + PREFIX_POLICY_ISSUE_DATE + VALID_POLICY_EXPIRY_DATE_AMY + " "
                + PREFIX_POLICY_EXPIRY_DATE + VALID_POLICY_ISSUE_DATE_AMY;

        String incompatibleInput = NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NRIC_DESC_AMY + LICENCE_PLATE_DESC_AMY + ADDRESS_DESC_AMY + COMPANY_DESC_AMY
                + POLICY_NO_DESC_AMY + incompatiblePolicyDates;

        assertParseFailure(parser, incompatibleInput,
                MESSAGE_DATES_NOT_COMPATIBLE);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB
                + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + NRIC_DESC_BOB
                + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + NRIC_DESC_BOB
                + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB
                + LICENCE_PLATE_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB
                + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB
                + LICENCE_PLATE_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB
                + LICENCE_PLATE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_PREAMBLE_DETECTED));
    }
}
