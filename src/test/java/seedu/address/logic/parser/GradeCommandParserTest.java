package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_ASSIGNMENT_NAME;
import static seedu.address.logic.Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class GradeCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE);

    private GradeCommandParser parser = new GradeCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // Test case 1: Missing index (preamble)
        String userInput1 = "n/Lab1";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_FORMAT);

        // Test case 2: Missing name
        String userInput2 = "1 g/10";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);

        // Test case 3: Missing grade
        String userInput3 = "1 n/Lab1";
        assertParseFailure(parser, userInput3, MESSAGE_INVALID_FORMAT);

        // Missing all index, assignment name and grade
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // Invalid index (not a positive integer)
        assertParseFailure(parser, "a n/Lab1 g/100", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidScore_failure() {
        // Invalid score (not a valid integer)
        assertParseFailure(parser, "1 n/Lab1 g/a", MESSAGE_INVALID_ASSIGNMENT_SCORE);

        // Invalid score (negative value less than zero)
        assertParseFailure(parser, "1 n/Lab1 g/-10", MESSAGE_INVALID_ASSIGNMENT_SCORE);

    }

    @Test
    public void parse_invalidAssignmentName_failure() {
        // Test case 1: Invalid name
        String userInput1 = "1 n/L)(&% g/1";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_ASSIGNMENT_NAME);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input
        assertParseSuccess(parser, "1 n/Lab1 g/100", new GradeCommand(Index.fromOneBased(1),
                new Name("Lab1"), 100));

        // Valid input with different indices and tutorial
        assertParseSuccess(parser, "2 n/Tutorial1 g/10", new GradeCommand(Index.fromOneBased(2),
                new Name("Tutorial1"), 10));
    }
}
