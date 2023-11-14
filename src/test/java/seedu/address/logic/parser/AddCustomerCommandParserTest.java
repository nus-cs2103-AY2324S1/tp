package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BUDGET_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BUDGET_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BUDGET_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BIG;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SQUARE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BIG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SQUARE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCustomers.AMY;
import static seedu.address.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CustomerBuilder;

public class AddCustomerCommandParserTest {
    private AddCustomerCommandParser parser = new AddCustomerCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Customer expectedCustomer = new CustomerBuilder(BOB).withTags(VALID_TAG_SQUARE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + BUDGET_DESC_BOB + TAG_DESC_SQUARE, new AddCustomerCommand(expectedCustomer));


        // multiple tags - all accepted
        Customer expectedCustomerMultipleTags = new CustomerBuilder(BOB).withTags(VALID_TAG_SQUARE, VALID_TAG_BIG)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BUDGET_DESC_BOB + TAG_DESC_BIG + TAG_DESC_SQUARE,
                new AddCustomerCommand(expectedCustomerMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedCustomerString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + BUDGET_DESC_BOB + TAG_DESC_SQUARE;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple budgets
        assertParseFailure(parser, BUDGET_DESC_AMY + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BUDGET));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedCustomerString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + BUDGET_DESC_AMY
                        + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_BUDGET, PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid budget
        assertParseFailure(parser, INVALID_BUDGET_DESC + validExpectedCustomerString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BUDGET));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedCustomerString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedCustomerString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedCustomerString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid budget
        assertParseFailure(parser, validExpectedCustomerString + INVALID_BUDGET_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BUDGET));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Customer expectedCustomer = new CustomerBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + BUDGET_DESC_AMY,
                new AddCustomerCommand(expectedCustomer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCustomerCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BUDGET_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + BUDGET_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + BUDGET_DESC_BOB,
                expectedMessage);

        // missing budget prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_BUDGET_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_BUDGET_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + BUDGET_DESC_BOB
                + TAG_DESC_BIG + TAG_DESC_SQUARE, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + BUDGET_DESC_BOB
                + TAG_DESC_BIG + TAG_DESC_SQUARE, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + BUDGET_DESC_BOB
                + TAG_DESC_BIG + TAG_DESC_SQUARE, Email.MESSAGE_CONSTRAINTS);

        // invalid budget
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_BUDGET_DESC
                + TAG_DESC_BIG + TAG_DESC_SQUARE, Budget.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BUDGET_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_BIG, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_BUDGET_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + BUDGET_DESC_BOB + TAG_DESC_BIG + TAG_DESC_SQUARE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCustomerCommand.MESSAGE_USAGE));
    }
}
