package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalApplicants.AMY_APPLICANT;
import static seedu.address.testutil.TypicalApplicants.BOB_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.testutil.ApplicantBuilder;

public class AddApplicantCommandParserTest {
    private AddApplicantCommandParser parser = new AddApplicantCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new ApplicantBuilder(BOB_APPLICANT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB,
                new AddApplicantCommand(expectedApplicant));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedApplicantString = NAME_DESC_BOB + PHONE_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedApplicantString + PHONE_DESC_AMY + NAME_DESC_AMY
                        + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedApplicantString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid phone
        assertParseFailure(parser, validExpectedApplicantString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Applicant expectedApplicant = new ApplicantBuilder(AMY_APPLICANT).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY,
                new AddApplicantCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                "Missing fields: %s is missing!\n" + AddApplicantCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB,
                String.format(expectedMessage, "Name"));

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB,
                String.format(expectedMessage, "Phone"));

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB,
                String.format(expectedMessage, "Name, Phone"));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        "Missing fields:  is missing!\n" + AddApplicantCommand.MESSAGE_USAGE));
    }
}
