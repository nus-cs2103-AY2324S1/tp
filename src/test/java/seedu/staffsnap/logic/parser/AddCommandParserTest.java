package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DEPARTMENT_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DEPARTMENT_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.JOB_TITLE_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.JOB_TITLE_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.staffsnap.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.staffsnap.testutil.TypicalApplicants.AMY;
import static seedu.staffsnap.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.AddCommand;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Department;
import seedu.staffsnap.model.applicant.JobTitle;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.tag.Tag;
import seedu.staffsnap.testutil.ApplicantBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new ApplicantBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedApplicant));

        // multiple tags - all accepted
        Applicant expectedApplicantMultipleTags = new ApplicantBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB
                        + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + JOB_TITLE_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicantMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedApplicantString = NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple departments
        assertParseFailure(parser, DEPARTMENT_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEPARTMENT));

        // multiple jobTitles
        assertParseFailure(parser, JOB_TITLE_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TITLE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedApplicantString + PHONE_DESC_AMY + DEPARTMENT_DESC_AMY + NAME_DESC_AMY + JOB_TITLE_DESC_AMY
                        + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_NAME, PREFIX_JOB_TITLE, PREFIX_DEPARTMENT, PREFIX_PHONE));

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

        // invalid jobTitle
        assertParseFailure(parser, INVALID_JOB_TITLE_DESC + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TITLE));

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

        // invalid jobTitle
        assertParseFailure(parser, validExpectedApplicantString + INVALID_JOB_TITLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_TITLE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Applicant expectedApplicant = new ApplicantBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + DEPARTMENT_DESC_AMY + JOB_TITLE_DESC_AMY,
                new AddCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + DEPARTMENT_DESC_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_DEPARTMENT_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing jobTitle prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + VALID_JOB_TITLE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_DEPARTMENT_BOB + VALID_JOB_TITLE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + DEPARTMENT_DESC_BOB + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_DEPARTMENT_DESC + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Department.MESSAGE_CONSTRAINTS);

        // invalid jobTitle
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + INVALID_JOB_TITLE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, JobTitle.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + JOB_TITLE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB + INVALID_JOB_TITLE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + DEPARTMENT_DESC_BOB
                + JOB_TITLE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
