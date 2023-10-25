package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_LOGISTIC;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LEAVE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LEAVE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LEAVE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEmployees.AMY;
import static seedu.address.testutil.TypicalEmployees.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Salary;
import seedu.address.testutil.EmployeeBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Employee expectedEmployee = new EmployeeBuilder(BOB).withDepartments(VALID_DEPARTMENT_INVESTMENT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB + DEPARTMENT_DESC_INVESTMENT,
                new AddCommand(expectedEmployee));


        // multiple departments - all accepted
        Employee expectedEmployeeMultipleDepartments = new EmployeeBuilder(BOB).withDepartments(
                VALID_DEPARTMENT_INVESTMENT, VALID_DEPARTMENT_LOGISTIC)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + SALARY_DESC_BOB
                + LEAVE_DESC_BOB + DEPARTMENT_DESC_LOGISTIC + DEPARTMENT_DESC_INVESTMENT,
                new AddCommand(expectedEmployeeMultipleDepartments));
    }

    @Test
    public void parse_repeatedNonDepartmentValue_failure() {
        String validExpectedEmployeeString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB + DEPARTMENT_DESC_INVESTMENT;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple salaries
        assertParseFailure(parser, SALARY_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // multiple leaves
        assertParseFailure(parser, LEAVE_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LEAVE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedEmployeeString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + SALARY_DESC_AMY + LEAVE_DESC_AMY + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL,
                        PREFIX_SALARY, PREFIX_LEAVE, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid salary
        assertParseFailure(parser, INVALID_SALARY_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // invalid leave
        assertParseFailure(parser, INVALID_LEAVE_DESC + validExpectedEmployeeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LEAVE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid salary
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_SALARY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // invalid leave
        assertParseFailure(parser, validExpectedEmployeeString + INVALID_LEAVE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LEAVE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero department
        Employee expectedEmployee = new EmployeeBuilder(AMY).withDepartments().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + SALARY_DESC_AMY + LEAVE_DESC_AMY, new AddCommand(expectedEmployee));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_ADDRESS_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB, expectedMessage);

        // missing salary prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + VALID_SALARY_BOB + LEAVE_DESC_BOB, expectedMessage);

        // missing leave prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + VALID_LEAVE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_ADDRESS_BOB + VALID_SALARY_BOB + VALID_LEAVE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB
                + DEPARTMENT_DESC_LOGISTIC + DEPARTMENT_DESC_INVESTMENT, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB
                + DEPARTMENT_DESC_LOGISTIC + DEPARTMENT_DESC_INVESTMENT, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB
                + DEPARTMENT_DESC_LOGISTIC + DEPARTMENT_DESC_INVESTMENT, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + SALARY_DESC_BOB + LEAVE_DESC_BOB
                + DEPARTMENT_DESC_LOGISTIC + DEPARTMENT_DESC_INVESTMENT, Address.MESSAGE_CONSTRAINTS);

        // invalid salary
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_SALARY_DESC + LEAVE_DESC_BOB
                + DEPARTMENT_DESC_LOGISTIC + DEPARTMENT_DESC_INVESTMENT, Salary.MESSAGE_CONSTRAINTS);

        // invalid leave
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + INVALID_LEAVE_DESC
                + DEPARTMENT_DESC_LOGISTIC + DEPARTMENT_DESC_INVESTMENT, Leave.MESSAGE_CONSTRAINTS);

        // invalid department
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB
                + INVALID_DEPARTMENT_DESC + VALID_DEPARTMENT_INVESTMENT, Department.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_ADDRESS_DESC + SALARY_DESC_BOB + LEAVE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + LEAVE_DESC_BOB
                + DEPARTMENT_DESC_LOGISTIC + DEPARTMENT_DESC_INVESTMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
