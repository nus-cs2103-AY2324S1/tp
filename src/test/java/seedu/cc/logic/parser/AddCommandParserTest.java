package seedu.cc.logic.parser;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.AGE_DESC_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_AGE_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.cc.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.cc.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.cc.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_ASTHMA;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_HYPERTENSION;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cc.testutil.TypicalPatients.AMY;
import static seedu.cc.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.AddCommand;
import seedu.cc.model.patient.Patient;
import seedu.cc.model.person.Age;
import seedu.cc.model.person.Email;
import seedu.cc.model.person.Name;
import seedu.cc.model.person.Phone;
import seedu.cc.model.tag.Tag;
import seedu.cc.testutil.PatientBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).withTags(VALID_TAG_HYPERTENSION).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + NRIC_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + AGE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPatient));


        // multiple tags - all accepted
        Patient expectedPatientMultipleTags = new PatientBuilder(BOB).withTags(VALID_TAG_HYPERTENSION, VALID_TAG_ASTHMA)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + AGE_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedPatientMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPatientString = NAME_DESC_BOB + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + AGE_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, AGE_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPatientString + NRIC_DESC_BOB + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + AGE_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_AGE, PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_AGE_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPatientString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPatientString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPatientString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPatientString + INVALID_AGE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Patient expectedPatient = new PatientBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + NRIC_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + AGE_DESC_AMY, new AddCommand(expectedPatient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + AGE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + AGE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + AGE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_AGE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_AGE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + AGE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + NRIC_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + AGE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + NRIC_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + AGE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_AGE_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Age.MESSAGE_CONSTRAINTS);

        // invalid tag`
        assertParseFailure(parser, NAME_DESC_BOB + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + AGE_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_HYPERTENSION, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + NRIC_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_AGE_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
