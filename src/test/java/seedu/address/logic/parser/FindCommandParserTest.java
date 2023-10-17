package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_hasPreamble_throwsParseException() {
        // just preamble
        assertThrows(ParseException.class, () -> parser.parse(" hello world"));

        // preamble with valid args after
        assertThrows(ParseException.class, () -> parser.parse(" hello world n/Alice"));
    }

    @Test
    public void parse_conditionNotJoinedWithBooleanOp_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice n/Bob"));
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice t/friends"));
    }

    @Test
    public void parse_conditionInvalidBooleanOp_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice & n/Bob"));
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice | t/Bob"));
    }

    @Test
    public void parse_invalidCondition_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" /Alice"));
        assertThrows(ParseException.class, () -> parser.parse(" n/"));
        assertThrows(ParseException.class, () -> parser.parse(" /"));
    }

    @Test
    public void parse_invalidBooleanExpression_throwsParseException() {
        // no conditions
        assertThrows(ParseException.class, () -> parser.parse(" &"));
        assertThrows(ParseException.class, () -> parser.parse(" !"));

        // no conditions after boolean op
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice &&"));
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice ||"));

        // no conditions before boolean op
        assertThrows(ParseException.class, () -> parser.parse(" && n/Alice"));
        assertThrows(ParseException.class, () -> parser.parse(" || n/Alice"));

        // no conditions between boolean op
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice && && n/Bob"));
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice || || n/Bob"));

        // no conditions after not op
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice ! "));
    }

    @Test
    public void parse_unmatchedParens_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" (n/Alice && t/friends"));
        assertThrows(ParseException.class, () -> parser.parse(" n/Alice && (t/friends || t/colleagues"));
        assertThrows(ParseException.class, () -> parser.parse(" )n/Alice && t/friends("));
        assertThrows(ParseException.class, () -> parser.parse(" (n/Alice && t/friends))"));
    }

    @Test
    public void parse_validOneArg_returnsFindCommand() throws ParseException {
        // name arg
        assertDoesNotThrow(() -> parser.parse(" n/Alice"));
        assertTrue(parser.parse(" n/Alice") instanceof FindCommand);

        // other arg
        assertDoesNotThrow(() -> parser.parse(" t/friends"));
        assertTrue(parser.parse(" t/friends") instanceof FindCommand);
    }

    @Test
    public void parse_validMultipleArgs_returnsFindCommand() throws ParseException {
        // name args
        assertDoesNotThrow(() -> parser.parse(" n/Alice && n/Li"));
        assertTrue(parser.parse(" n/Alice && n/Li") instanceof FindCommand);

        // multiple distinct args
        assertDoesNotThrow(() -> parser.parse(" n/Alice && t/friends"));
        assertTrue(parser.parse(" n/Alice && t/friends") instanceof FindCommand);

        // multiple distinct args with repeated fields
        assertDoesNotThrow(() -> parser.parse(" n/Alice && t/colleagues && t/friends"));
        assertTrue(parser.parse(" n/Alice && t/colleagues && t/friends") instanceof FindCommand);
    }

    @Test
    public void parse_validArgsWithNotOp_returnsFindCommand() throws ParseException {
        assertDoesNotThrow(() -> parser.parse(" !n/Alice"));
        assertTrue(parser.parse(" !n/Alice") instanceof FindCommand);

        // with space after NOT
        assertDoesNotThrow(() -> parser.parse(" ! t/friends"));
        assertTrue(parser.parse(" ! t/friends") instanceof FindCommand);
    }

    @Test
    public void parse_validArgsWithParens_returnFindCommand() throws ParseException {
        assertDoesNotThrow(() -> parser.parse(" (n/Alice)"));
        assertTrue(parser.parse(" (n/Alice)") instanceof FindCommand);

        // alters precedence
        assertDoesNotThrow(() -> parser.parse(" (n/Alice || t/friends) && a/street"));
        assertTrue(parser.parse(" (n/Alice || t/friends) && a/street") instanceof FindCommand);

        // does not alter precedence
        assertDoesNotThrow(() -> parser.parse(" (n/Alice && t/friends) && a/street"));
        assertTrue(parser.parse(" (n/Alice && t/friends) && a/street") instanceof FindCommand);

        // with space after parens
        assertDoesNotThrow(() -> parser.parse(" ( t/friends )"));
        assertTrue(parser.parse(" ( t/friends )") instanceof FindCommand);

        // with space before parens
        assertDoesNotThrow(() -> parser.parse(" (t/friends )"));
        assertTrue(parser.parse(" (t/friends )") instanceof FindCommand);

        // with nested parens
        assertDoesNotThrow(() -> parser.parse(" (n/Alice && (t/friends || t/colleagues))"));
        assertTrue(parser.parse(" (n/Alice && (t/friends || t/colleagues))") instanceof FindCommand);
        assertDoesNotThrow(() -> parser.parse(" (n/Alice && ((((t/friends || t/colleagues)))))"));
        assertTrue(parser.parse(" (n/Alice && ((((t/friends || t/colleagues)))))") instanceof FindCommand);
    }

}
