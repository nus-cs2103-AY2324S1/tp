package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECRUITER_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.RECRUITER_NAME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.RECRUITER_NAME_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECRUITER_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_TIKTOK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECRUITER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCompanies.GOOGLE;
import static seedu.address.testutil.TypicalCompanies.TIKTOK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Company;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Role;
import seedu.address.testutil.CompanyBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Company expectedCompany = new CompanyBuilder(TIKTOK).withRemark("No remarks").build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + PRIORITY_DESC_TIKTOK, new AddCommand(expectedCompany));


    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedCompanyString = NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK + PRIORITY_DESC_TIKTOK;

        // multiple names
        assertParseFailure(parser, NAME_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple roles
        assertParseFailure(parser, ROLE_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // multiple deadlines
        assertParseFailure(parser, DEADLINE_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // multiple statuses
        assertParseFailure(parser, STATUS_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // multiple recruiter names
        assertParseFailure(parser, RECRUITER_NAME_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_RECRUITER_NAME));

        // multiple priorities
        assertParseFailure(parser, PRIORITY_DESC_GOOGLE + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // multiple fields repeated
        assertParseFailure(parser, NAME_DESC_GOOGLE + PHONE_DESC_GOOGLE + EMAIL_DESC_GOOGLE + ROLE_DESC_GOOGLE
                        + DEADLINE_DESC_GOOGLE + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                        + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ROLE, PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_RECRUITER_NAME));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid deadline
        assertParseFailure(parser, INVALID_DEADLINE_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // invalid status
        assertParseFailure(parser, INVALID_STATUS_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // invalid recruiter name
        assertParseFailure(parser, INVALID_RECRUITER_NAME_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_RECRUITER_NAME));

        // invalid priority
        assertParseFailure(parser, INVALID_PRIORITY_DESC + validExpectedCompanyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedCompanyString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedCompanyString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedCompanyString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid role
        assertParseFailure(parser, validExpectedCompanyString + INVALID_ROLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid deadline
        assertParseFailure(parser, validExpectedCompanyString + INVALID_DEADLINE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // invalid status
        assertParseFailure(parser, validExpectedCompanyString + INVALID_STATUS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // invalid recruiter name
        assertParseFailure(parser, validExpectedCompanyString + INVALID_RECRUITER_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_RECRUITER_NAME));

        // invalid priority
        assertParseFailure(parser, validExpectedCompanyString + INVALID_PRIORITY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));
    }

    @Test
    public void parse_optionalremarkMissing_success() {
        Company expectedCompany = new CompanyBuilder(GOOGLE).withRemark("No remarks").build();
        assertParseSuccess(parser, NAME_DESC_GOOGLE + PHONE_DESC_GOOGLE + EMAIL_DESC_GOOGLE + ROLE_DESC_GOOGLE
                        + DEADLINE_DESC_GOOGLE + STATUS_DESC_GOOGLE + RECRUITER_NAME_DESC_GOOGLE + PRIORITY_DESC_GOOGLE,
                new AddCommand(expectedCompany));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + VALID_PHONE_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + VALID_EMAIL_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing role prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + VALID_ROLE_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + VALID_DEADLINE_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing status prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + VALID_STATUS_TIKTOK + RECRUITER_NAME_DESC_TIKTOK, expectedMessage);

        // missing recruiter name prefix
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ROLE_DESC_TIKTOK
                + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + VALID_RECRUITER_NAME_TIKTOK, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_TIKTOK + VALID_PHONE_TIKTOK + VALID_EMAIL_TIKTOK + VALID_ROLE_TIKTOK
                + VALID_DEADLINE_TIKTOK + VALID_STATUS_TIKTOK + VALID_RECRUITER_NAME_TIKTOK, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + PRIORITY_DESC_TIKTOK, Name.MESSAGE_CONSTRAINTS_INVALID_REGEX);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_TIKTOK + INVALID_PHONE_DESC + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + PRIORITY_DESC_TIKTOK, Phone.MESSAGE_CONSTRAINTS_VALID_REGEX);

        // invalid email
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + INVALID_EMAIL_DESC
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + PRIORITY_DESC_TIKTOK, Email.MESSAGE_CONSTRAINTS_VALID_REGEX);

        // invalid role
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + INVALID_ROLE_DESC + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + PRIORITY_DESC_TIKTOK, Role.MESSAGE_CONSTRAINTS_INVALID_REGEX);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + INVALID_DEADLINE_DESC + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + PRIORITY_DESC_TIKTOK, Deadline.MESSAGE_CONSTRAINTS_WRONG_FORMAT);

        // invalid status
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + INVALID_STATUS_DESC + RECRUITER_NAME_DESC_TIKTOK
                + PRIORITY_DESC_TIKTOK, ApplicationStatus.MESSAGE_CONSTRAINTS_VALID_STATUS);

        // invalid recruiter name
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + INVALID_RECRUITER_NAME_DESC
                + PRIORITY_DESC_TIKTOK, RecruiterName.MESSAGE_CONSTRAINTS_INVALID_REGEX);

        // invalid priority
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK
                + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS_VALID_REGEX);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_PHONE_DESC + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK
                + RECRUITER_NAME_DESC_TIKTOK + PRIORITY_DESC_TIKTOK, Name.MESSAGE_CONSTRAINTS_INVALID_REGEX);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                        + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK + PRIORITY_DESC_TIKTOK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parsePrefixPriority_notPresent_returnsPriorityNoneObject() {
        String userInput = NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ROLE_DESC_TIKTOK + DEADLINE_DESC_TIKTOK + STATUS_DESC_TIKTOK + RECRUITER_NAME_DESC_TIKTOK;
        assertParseSuccess(parser, userInput, new AddCommand(
                new CompanyBuilder(TIKTOK)
                        .withPriority("NONE")
                        .withRemark("No remarks")
                        .build()
        ));
    }

}
