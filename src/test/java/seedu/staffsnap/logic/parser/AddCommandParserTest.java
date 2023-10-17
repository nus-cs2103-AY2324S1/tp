package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INTERVIEW_DESC_FRIEND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.POSITION_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.staffsnap.testutil.TypicalApplicants.AMY;
import static seedu.staffsnap.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.AddCommand;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Email;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.testutil.ApplicantBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicantWithWhitespace = new ApplicantBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB, new AddCommand(expectedApplicantWithWhitespace));

        // without whitespace preamble
        Applicant expectedApplicantWithoutWhitespace = new ApplicantBuilder(BOB).build();
        assertParseSuccess(parser, NAME_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB,
                new AddCommand(expectedApplicantWithoutWhitespace));
    }

    @Test
    public void parse_repeatedNonInterviewValue_failure() {
        String validExpectedApplicantString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + INTERVIEW_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple positions
        assertParseFailure(parser, POSITION_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_POSITION));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedApplicantString + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + NAME_DESC_AMY + POSITION_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_NAME, PREFIX_POSITION, PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid position
        assertParseFailure(parser, INVALID_POSITION_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_POSITION));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedApplicantString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedApplicantString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedApplicantString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid position
        assertParseFailure(parser, validExpectedApplicantString + INVALID_POSITION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_POSITION));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero interviews
        Applicant expectedApplicant = new ApplicantBuilder(AMY).withInterviews().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + POSITION_DESC_AMY,
                new AddCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + POSITION_DESC_BOB,
                expectedMessage);

        // missing position prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_POSITION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_POSITION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB
                + INVALID_PHONE_DESC + EMAIL_DESC_BOB + POSITION_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB
                + PHONE_DESC_BOB + INVALID_EMAIL_DESC + POSITION_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_POSITION_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
