package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_ASSIGNMENT_NAME;
import static seedu.address.logic.Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GradeGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Group;
import seedu.address.model.person.Name;

public class GradeGroupCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeGroupCommand.MESSAGE_USAGE);

    private GradeGroupCommandParser parser = new GradeGroupCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // Test case 1: Missing group (preamble)
        String userInput1 = "n/Lab1";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_FORMAT);

        // Test case 2: Invalid name
        String userInput2 = "group n/L)(&% g/sads";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_ASSIGNMENT_NAME);

        // Test case 3: Invalid score
        String userInput3 = "1 n/Lab1 g/sads";
        assertParseFailure(parser, userInput3, MESSAGE_INVALID_ASSIGNMENT_SCORE);

        // Test case 4: Missing name
        String userInput4 = "group g/10";
        assertParseFailure(parser, userInput4, MESSAGE_INVALID_FORMAT);

        // Test case 5: Missing grade
        String userInput5 = "group n/Lab1";
        assertParseFailure(parser, userInput5, MESSAGE_INVALID_FORMAT);

        // Missing all index, assignment name and grade
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // Invalid group (not alphanumeric)
        assertParseFailure(parser, "$ n/Lab1 g/100", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidScore_failure() {
        // Invalid score (not a valid integer)
        assertParseFailure(parser, "group n/Lab1 g/a", MESSAGE_INVALID_ASSIGNMENT_SCORE);

        // Invalid score (negative value less than zero)
        assertParseFailure(parser, "group n/Lab1 g/-10", MESSAGE_INVALID_ASSIGNMENT_SCORE);

    }

    @Test
    public void parse_invalidAssignmentName_failure() {
        // Test case 1: Invalid name
        String userInput1 = "group n/L)(&% g/1";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_ASSIGNMENT_NAME);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input
        Group group = new Group("group");
        assertParseSuccess(parser, "group n/Lab1 g/100", new GradeGroupCommand(group,
                new Name("Lab1"), 100));

        // Valid input with different indices and tutorial
        assertParseSuccess(parser, "group n/Tutorial1 g/10", new GradeGroupCommand(group,
                new Name("Tutorial1"), 10));
    }
}
