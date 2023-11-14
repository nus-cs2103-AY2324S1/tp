package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;



public class LeaveCommandParserTest {

    private LeaveCommandParser parser = new LeaveCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input with email and group number
        String userInput = " " + PREFIX_EMAIL + "johnd@u.nus.edu " + PREFIX_GROUP + "1";
        LeaveCommand expectedCommand = new LeaveCommand(new Email("johnd@u.nus.edu"), 1);
        assertEquals(expectedCommand, parser.parse(userInput));
    }

    @Test
    public void parse_missingEmailPrefix_failure() {
        // Missing email prefix
        String userInput = PREFIX_GROUP + "1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingGroupPrefix_failure() {
        // Missing group prefix
        String userInput = PREFIX_EMAIL + "johnd@u.nus.edu";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidEmail_failure() {
        // Invalid email
        String userInput = PREFIX_EMAIL + "johnd" + " " + PREFIX_GROUP + "1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidGroupNumber_failure() {
        // Invalid group number
        String userInput = PREFIX_EMAIL + "johnd@u.nus.edu " + PREFIX_GROUP + "group";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidEmailFormat_throwsParseExceptionWithProperMessage() {
        LeaveCommandParser parser = new LeaveCommandParser();
        String userInput = PREFIX_EMAIL + "invalid_email_format " + PREFIX_GROUP + "1";

        assertThrows(ParseException.class, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, LeaveCommand.MESSAGE_USAGE), () -> {
                parser.parse(userInput);
                });
    }
}
