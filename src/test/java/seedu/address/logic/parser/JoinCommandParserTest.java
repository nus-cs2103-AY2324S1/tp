package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.JoinCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;

public class JoinCommandParserTest {

    private JoinCommandParser parser = new JoinCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        String userInput = " " + PREFIX_EMAIL + "johnd@u.nus.edu " + PREFIX_GROUP + "1";
        JoinCommand expectedCommand = new JoinCommand(new Email("johnd@u.nus.edu"), 1);
        assertEquals(expectedCommand, parser.parse(userInput));
    }

    @Test
    public void parse_missingEmailPrefix_throwsParseException() {
        String userInput = " johnd@u.nus.edu " + PREFIX_GROUP + "1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingGroupPrefix_throwsParseException() {
        String userInput = PREFIX_EMAIL + "johnd@u.nus.edu " + "1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidEmail_throwsParseException() {
        String userInput = PREFIX_EMAIL + "johnd" + " " + PREFIX_GROUP + "1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidGroupNumber_throwsParseException() {
        String userInput = PREFIX_EMAIL + "johnd@u.nus.edu " + PREFIX_GROUP + "one";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingGroupNumber_throwsParseException() {
        String userInput = PREFIX_EMAIL + "johnd@u.nus.edu " + PREFIX_GROUP;
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingEmail_throwsParseException() {
        String userInput = " " + PREFIX_EMAIL + " " + PREFIX_GROUP + "1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_emptyArguments_throwsParseException() {
        String userInput = "";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
