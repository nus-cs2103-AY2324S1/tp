package seedu.address.logic.parser.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.finance.FilterClientNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.ClientNameContainsKeywordsPredicate;
class FilterClientNameCommandParserTest {
    private FilterClientNameCommandParser parser = new FilterClientNameCommandParser();

    @Test
    public void parse_validArgs_returnsFilterClientNameCommand() throws ParseException {
        String args = "keyword1 keyword2";
        FilterClientNameCommand expectedCommand = new FilterClientNameCommand(
                new ClientNameContainsKeywordsPredicate(Arrays.asList(args.split("\\s+"))));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterClientNameCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }

    @Test
    public void parse_whitespaceArgs_throwsParseException() {
        String args = "    ";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterClientNameCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }

}

