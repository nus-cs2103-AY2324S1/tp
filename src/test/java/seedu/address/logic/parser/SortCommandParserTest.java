package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.SortIn;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_sort_valid_ASC_success() throws ParseException {
        String sequence = "ASC";
        assert new SortCommand(new SortIn(sequence)).toString().equals(parser.parse(SEQUENCE_ASC).toString());
    }

    @Test
    public void parse_sort_valid_DESC_success() throws ParseException {
        String sequence = "DESC";
        assert new SortCommand(new SortIn(sequence)).toString().equals(parser.parse(SEQUENCE_DESC).toString());
    }

    @Test
    public void parse_sort_invalid_failure() throws ParseException {
        // Invalid sequence, should throw a ParseException
        String invalidSequence = "INVALID";
        try {
            parser.parse(invalidSequence);
            // The line above should throw a ParseException, if not, the test fails
            assert false : "Expected ParseException was not thrown";
        } catch (ParseException e) {
            // Expected ParseException, test passes
            assert true;
        }
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // missing sort in prefix
        assertParseFailure(parser, VALID_SEQUENCE_ASC, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SEQUENCE_ASC, expectedMessage);
    }

}
