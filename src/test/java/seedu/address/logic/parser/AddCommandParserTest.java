package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Salary;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB,
                new AddCommand(expectedPerson));


        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB)
                .build();
        assertParseSuccess(parser, ID_DESC_BOB + NAME_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    // public void parse_repeatedNonTagValue_failure() {
    public void parse_repeatedValue_failure() {
        String validExpectedPersonString = ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB;

        // multiple IDs
        assertParseFailure(parser, ID_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple departments
        assertParseFailure(parser, DEPARTMENT_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEPARTMENT));

        // multiple roles
        assertParseFailure(parser, ROLE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // multiple salaries
        assertParseFailure(parser, SALARY_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + ID_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + DEPARTMENT_DESC_AMY + ROLE_DESC_AMY + SALARY_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID, PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE,
                        PREFIX_DEPARTMENT, PREFIX_ROLE, PREFIX_SALARY));

        // invalid value followed by valid value

        // invalid ID
        assertParseFailure(parser, INVALID_ID_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid department
        assertParseFailure(parser, INVALID_DEPARTMENT_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEPARTMENT));

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid salary
        assertParseFailure(parser, INVALID_SALARY_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // valid value followed by invalid value

        // invalid ID
        assertParseFailure(parser, validExpectedPersonString + INVALID_ID_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid department
        assertParseFailure(parser, validExpectedPersonString + INVALID_DEPARTMENT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEPARTMENT));

        // invalid role
        assertParseFailure(parser, validExpectedPersonString + INVALID_ROLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid salary
        assertParseFailure(parser, validExpectedPersonString + INVALID_SALARY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing ID prefix
        assertParseFailure(parser, VALID_ID_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, ID_DESC_BOB + VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB, expectedMessage);

        // missing department prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_DEPARTMENT_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB, expectedMessage);

        // missing role prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB + VALID_ROLE_BOB + SALARY_DESC_BOB, expectedMessage);

        // missing salary prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + VALID_SALARY_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ID_BOB + VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_DEPARTMENT_BOB + VALID_ROLE_BOB + VALID_SALARY_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid ID
        assertParseFailure(parser, INVALID_ID_DESC + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB
                + ROLE_DESC_BOB + SALARY_DESC_BOB, Id.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, ID_DESC_BOB + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB
                + ROLE_DESC_BOB + SALARY_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB
                + ROLE_DESC_BOB + SALARY_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + DEPARTMENT_DESC_BOB
                + ROLE_DESC_BOB + SALARY_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid department
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_DEPARTMENT_DESC
                + ROLE_DESC_BOB + SALARY_DESC_BOB, Department.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB
                + INVALID_ROLE_DESC + SALARY_DESC_BOB, Role.MESSAGE_CONSTRAINTS);

        // invalid salary
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB
                + ROLE_DESC_BOB + INVALID_SALARY_DESC, Salary.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_ID_DESC + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_DEPARTMENT_DESC
                + ROLE_DESC_BOB + SALARY_DESC_BOB, Id.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB
                + DEPARTMENT_DESC_BOB + ROLE_DESC_BOB + SALARY_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
