package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchDentistCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class SearchDentistCommandParserTest {
    private SearchDentistCommandParser parser = new SearchDentistCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SearchDentistCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSearchByName() throws ParseException {
        String args = "John";
        SearchDentistCommand expectedCommand = new SearchDentistCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("John")));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_validSearchByNameArgs_returnsSearchDentistCommand() throws ParseException {
        String args = "John Tan";
        SearchDentistCommand expectedCommand = new SearchDentistCommand(
            new NameContainsKeywordsPredicate(Arrays.asList("John", "Tan")));
        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validSearchById() throws ParseException {
        String args = "2";
        SearchDentistCommand expectedCommand = new SearchDentistCommand(2);
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_validSearchByIdArgs_returnsSearchDentistCommand() throws ParseException {
        String args = "123";
        SearchDentistCommand expectedCommand = new SearchDentistCommand(123);
        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_invalidFormat() {
        String args = "";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_validSearchWithMixedCase() throws ParseException {
        String args = "jOhN";
        SearchDentistCommand expectedCommand = new SearchDentistCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("jOhN")));
        assertEquals(expectedCommand, parser.parse(args));
    }
}
