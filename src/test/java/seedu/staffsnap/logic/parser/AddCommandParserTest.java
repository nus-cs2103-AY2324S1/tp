package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DEPARTMENT_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DEPARTMENT_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INTERVIEW_DESC_FRIEND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INTERVIEW_DESC_HUSBAND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_INTERVIEW_DESC;
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
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_FRIEND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_HUSBAND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
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
import seedu.staffsnap.model.applicant.Department;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.testutil.ApplicantBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new ApplicantBuilder(BOB).withInterviews(VALID_INTERVIEW_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB
                + POSITION_DESC_BOB + INTERVIEW_DESC_FRIEND, new AddCommand(expectedApplicant));

        // multiple interviews - all accepted
        Applicant expectedApplicantMultipleInterviews = new ApplicantBuilder(BOB)
                .withInterviews(VALID_INTERVIEW_FRIEND, VALID_INTERVIEW_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB
                        + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + POSITION_DESC_BOB
                        + INTERVIEW_DESC_HUSBAND + INTERVIEW_DESC_FRIEND,
                new AddCommand(expectedApplicantMultipleInterviews));
    }

    @Test
    public void parse_repeatedNonInterviewValue_failure() {
        String validExpectedApplicantString = NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB
                + POSITION_DESC_BOB + INTERVIEW_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple departments
        assertParseFailure(parser, DEPARTMENT_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEPARTMENT));

        // multiple positions
        assertParseFailure(parser, POSITION_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_POSITION));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedApplicantString + PHONE_DESC_AMY + DEPARTMENT_DESC_AMY + NAME_DESC_AMY + POSITION_DESC_AMY
                        + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_NAME, PREFIX_POSITION, PREFIX_DEPARTMENT, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid department
        assertParseFailure(parser, INVALID_DEPARTMENT_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEPARTMENT));

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

        // invalid department
        assertParseFailure(parser, validExpectedApplicantString + INVALID_DEPARTMENT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEPARTMENT));

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
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + DEPARTMENT_DESC_AMY + POSITION_DESC_AMY,
                new AddCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + POSITION_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + DEPARTMENT_DESC_BOB + POSITION_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_DEPARTMENT_BOB + POSITION_DESC_BOB,
                expectedMessage);

        // missing position prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + VALID_POSITION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_DEPARTMENT_BOB + VALID_POSITION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + POSITION_DESC_BOB
                + INTERVIEW_DESC_HUSBAND + INTERVIEW_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + DEPARTMENT_DESC_BOB + POSITION_DESC_BOB
                + INTERVIEW_DESC_HUSBAND + INTERVIEW_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_DEPARTMENT_DESC + POSITION_DESC_BOB
                + INTERVIEW_DESC_HUSBAND + INTERVIEW_DESC_FRIEND, Department.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + INVALID_POSITION_DESC
                + INTERVIEW_DESC_HUSBAND + INTERVIEW_DESC_FRIEND, Position.MESSAGE_CONSTRAINTS);

        // invalid interview
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + POSITION_DESC_BOB
                + INVALID_INTERVIEW_DESC + VALID_INTERVIEW_FRIEND, Interview.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + INVALID_POSITION_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB
                + POSITION_DESC_BOB + INTERVIEW_DESC_HUSBAND + INTERVIEW_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
