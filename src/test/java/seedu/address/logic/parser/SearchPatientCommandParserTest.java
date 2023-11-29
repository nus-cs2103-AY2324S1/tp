package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class SearchPatientCommandParserTest {
    private SearchPatientCommandParser parser = new SearchPatientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SearchPatientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSearchByName() throws ParseException {
        String args = "Lim";
        SearchPatientCommand expectedCommand = new SearchPatientCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("Lim")));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_validSearchByNameArgs_returnsSearchDentistCommand() throws ParseException {
        String args = "Lim Ken";
        SearchPatientCommand expectedCommand = new SearchPatientCommand(
            new NameContainsKeywordsPredicate(Arrays.asList("Lim", "Ken")));
        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validSearchById() throws ParseException {
        String args = "2";
        SearchPatientCommand expectedCommand = new SearchPatientCommand(2);
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_validSearchByIdArgs_returnsSearchPatientCommand() throws ParseException {
        String args = "123";
        SearchPatientCommand expectedCommand = new SearchPatientCommand(123);
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
        SearchPatientCommand expectedCommand = new SearchPatientCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("jOhN")));
        assertEquals(expectedCommand, parser.parse(args));
    }
}
