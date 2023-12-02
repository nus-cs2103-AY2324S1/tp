package seedu.address.logic.parser.user;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PASSWORD_DESC_AARON;
import static seedu.address.logic.commands.CommandTestUtil.PASSWORD_DESC_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_AARON;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_AARON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_AARON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalUsers.AARON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.user.UserLoginCommand;
import seedu.address.model.user.Password;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;
import seedu.address.testutil.UserBuilder;

public class UserLoginCommandParserTest {

    private UserLoginCommandParser parser = new UserLoginCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        User expectedUser = new UserBuilder(AARON).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + USERNAME_DESC_AARON
                + PASSWORD_DESC_AARON, new UserLoginCommand(expectedUser));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedUserString = USERNAME_DESC_FOODBEAR + PASSWORD_DESC_FOODBEAR;

        // multiple usernames
        assertParseFailure(parser, USERNAME_DESC_AARON + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_USER));

        // multiple password
        assertParseFailure(parser, PASSWORD_DESC_AARON + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PASSWORD));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedUserString + USERNAME_DESC_AARON + PASSWORD_DESC_AARON
                        + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_USER, PREFIX_PASSWORD));

        // invalid value followed by valid value

        // invalid username
        assertParseFailure(parser, INVALID_USERNAME_DESC + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_USER));

        // invalid password
        assertParseFailure(parser, INVALID_PASSWORD_DESC + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PASSWORD));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedUserString + INVALID_USERNAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_USER));

        // invalid email
        assertParseFailure(parser, validExpectedUserString + INVALID_PASSWORD_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PASSWORD));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserLoginCommand.MESSAGE_USAGE);

        // missing username prefix
        assertParseFailure(parser, VALID_PASSWORD_AARON, expectedMessage);

        // missing password prefix
        assertParseFailure(parser, VALID_USERNAME_AARON, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_USERNAME_AARON + VALID_PASSWORD_AARON,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid username
        assertParseFailure(parser, INVALID_USERNAME_DESC + PASSWORD_DESC_AARON, Username.MESSAGE_CONSTRAINTS);

        // invalid password
        assertParseFailure(parser, USERNAME_DESC_AARON + INVALID_PASSWORD_DESC, Password.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_USERNAME_DESC + INVALID_PASSWORD_DESC,
                Username.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + USERNAME_DESC_AARON + PASSWORD_DESC_AARON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserLoginCommand.MESSAGE_USAGE));
    }
}
