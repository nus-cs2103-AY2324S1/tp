package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_MALE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_CHERYL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SURGEON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDoctor.DEREK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.DoctorBuilder;

public class AddDoctorCommandParserTest {
    private AddDoctorCommandParser parser = new AddDoctorCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + ADDRESS_DESC_DEREK,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_DEREK + VALID_PHONE_DEREK + EMAIL_DESC_DEREK + ADDRESS_DESC_DEREK,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_DEREK + PHONE_DESC_DEREK + VALID_EMAIL_DEREK + ADDRESS_DESC_DEREK,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + VALID_ADDRESS_DEREK,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_DEREK + VALID_PHONE_DEREK + VALID_EMAIL_DEREK + VALID_ADDRESS_DEREK,
                expectedMessage);
    }
    @Test
    public void parse_allFieldsPresent_success() {
        Doctor expectedDoctor = new DoctorBuilder(DEREK).withTags("Surgeon").build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK
                        + ADDRESS_DESC_DEREK + GENDER_DESC_MALE + NRIC_DESC_DEREK +,
                new AddDoctorCommand(expectedDoctor));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedDoctorString = NAME_DESC_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + ADDRESS_DESC_DEREK
                + GENDER_DESC_MALE + NRIC_DESC_DEREK;

        // multiple names
        assertParseFailure(parser, NAME_DESC_DEREK + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_DEREK + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_DEREK + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_DEREK + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedDoctorString + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + NAME_DESC_DEREK + ADDRESS_DESC_DEREK
                        + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_NRIC, PREFIX_GENDER,
                        PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedDoctorString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
        // valid value followed by invalid value
        // invalid name
        assertParseFailure(parser, validExpectedDoctorString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedDoctorString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedDoctorString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedDoctorString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Doctor expectedDoctor = new DoctorBuilder(DEREK).withTags().build();
        assertParseSuccess(parser, NAME_DESC_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + ADDRESS_DESC_DEREK
                        + NRIC_DESC_DEREK + GENDER_DESC_MALE,
                new AddDoctorCommand(expectedDoctor));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + ADDRESS_DESC_DEREK
                + NRIC_DESC_DEREK + GENDER_DESC_MALE, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_DEREK + INVALID_PHONE_DESC + EMAIL_DESC_DEREK + ADDRESS_DESC_DEREK
                + NRIC_DESC_DEREK + GENDER_DESC_MALE, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_DEREK + PHONE_DESC_DEREK + INVALID_EMAIL_DESC + ADDRESS_DESC_DEREK
                + NRIC_DESC_DEREK + GENDER_DESC_MALE, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + INVALID_ADDRESS_DESC
                + NRIC_DESC_DEREK + GENDER_DESC_MALE, Address.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_DEREK + EMAIL_DESC_DEREK
                        + INVALID_ADDRESS_DESC + NRIC_DESC_DEREK + GENDER_DESC_MALE,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK
                        + ADDRESS_DESC_DEREK + NRIC_DESC_DEREK + GENDER_DESC_MALE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE));
    }
}
