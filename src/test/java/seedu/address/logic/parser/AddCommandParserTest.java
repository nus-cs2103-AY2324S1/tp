package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ANNUALLEAVE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ANNUALLEAVE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BANKACCOUNT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BANKACCOUNT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANNUALLEAVE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BANKACCOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOINDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOINDATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOINDATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANNUALLEAVE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BANKACCOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOINDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANNUAL_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANK_ACCOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOIN_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.BankAccount;
import seedu.address.model.person.Email;
import seedu.address.model.person.JoinDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB
                + ANNUALLEAVE_DESC_BOB, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB
                + ANNUALLEAVE_DESC_BOB;

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

        // multiple bank account
        assertParseFailure(parser, BANKACCOUNT_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BANK_ACCOUNT));

        // multiple join date
        assertParseFailure(parser, JOINDATE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOIN_DATE));

        // multiple salary
        assertParseFailure(parser, SALARY_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // multiple annual leave
        assertParseFailure(parser, ANNUALLEAVE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ANNUAL_LEAVE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + BANKACCOUNT_DESC_AMY + ANNUALLEAVE_DESC_AMY + SALARY_DESC_AMY + JOINDATE_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                        PREFIX_BANK_ACCOUNT, PREFIX_ANNUAL_LEAVE, PREFIX_JOIN_DATE, PREFIX_SALARY));

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

        // invalid bank account
        assertParseFailure(parser, INVALID_BANKACCOUNT_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BANK_ACCOUNT));

        // invalid join date
        assertParseFailure(parser, INVALID_JOINDATE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOIN_DATE));

        // invalid salary
        assertParseFailure(parser, INVALID_SALARY_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // invalid annual leave
        assertParseFailure(parser, INVALID_ANNUALLEAVE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ANNUAL_LEAVE));

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

        // invalid bank account
        assertParseFailure(parser, validExpectedPersonString + INVALID_BANKACCOUNT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BANK_ACCOUNT));

        // invalid join date
        assertParseFailure(parser, validExpectedPersonString + INVALID_JOINDATE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOIN_DATE));

        // invalid salary
        assertParseFailure(parser, validExpectedPersonString + INVALID_SALARY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // invalid annual leave
        assertParseFailure(parser, validExpectedPersonString + INVALID_ANNUALLEAVE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ANNUAL_LEAVE));
    }

    // This test case can be either deleted or kept. Keep this if we have any other fields that are optional.
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                expectedMessage);

        // missing bank prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + VALID_BANKACCOUNT_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                expectedMessage);

        // missing join date prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + BANKACCOUNT_DESC_BOB + VALID_JOINDATE_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                expectedMessage);

        // missing salary prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + VALID_SALARY_BOB + ANNUALLEAVE_DESC_BOB,
                expectedMessage);

        // missing annual leave prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + VALID_ANNUALLEAVE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_BANKACCOUNT_BOB + VALID_JOINDATE_BOB + VALID_SALARY_BOB + VALID_ANNUALLEAVE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid bank account
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_BANKACCOUNT_DESC + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                BankAccount.MESSAGE_CONSTRAINTS);

        // invalid join date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + INVALID_JOINDATE_DESC + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                JoinDate.MESSAGE_CONSTRAINTS);

        // invalid salary
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + INVALID_SALARY_DESC + ANNUALLEAVE_DESC_BOB,
                Salary.MESSAGE_CONSTRAINTS);

        // invalid annual leave
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + INVALID_ANNUALLEAVE_DESC,
                AnnualLeave.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + BANKACCOUNT_DESC_BOB + JOINDATE_DESC_BOB + SALARY_DESC_BOB + ANNUALLEAVE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
