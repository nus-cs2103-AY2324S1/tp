package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.SEQUENCE_ASC;
import static seedu.address.logic.commands.CommandTestUtil.SEQUENCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEQUENCE_ASC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.SortIn;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parseSortValidAscSuccess() throws ParseException {
        String sequence = "ASC";
        assert new SortCommand(new SortIn(sequence)).toString().equals(parser.parse(SEQUENCE_ASC).toString());
    }

    @Test
    public void parseSortValidDescSuccess() throws ParseException {
        String sequence = "DESC";
        assert new SortCommand(new SortIn(sequence)).toString().equals(parser.parse(SEQUENCE_DESC).toString());
    }

    @Test
    public void parseSortInvalidFailure() throws ParseException {
        // Invalid sequence, should throw a ParseException
        String invalidSequence = "INVALID";
        parser.parse(invalidSequence);
        // Use assertThrows to verify that a ParseException is thrown
        ParseException exception = assertThrows(ParseException.class, () -> {
            parser.parse(invalidSequence);
        });

        // Assert the exception message using assertEquals
        assertEquals(SortIn.MESSAGE_CONSTRAINTS, exception.getMessage());

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
