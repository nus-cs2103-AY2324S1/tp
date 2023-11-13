package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.model.assignment.Assignment;
import seedu.address.testutil.AssignmentBuilder;



public class AddAssignmentCommandParserTest {
    private AddAssignmentCommandParser parser = new AddAssignmentCommandParser();

    @Test
    public void parse_assignmentPresent_success() {
        Assignment testAssignment = new AssignmentBuilder().build(VALID_ASSIGNMENT_TITLE);

        assertParseSuccess(parser, " assgn/" + VALID_ASSIGNMENT_TITLE, new AddAssignmentCommand(testAssignment));
    }

    @Test
    public void parse_failedInputs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);
        String emptyInput = " ";
        String nonsenseInput = "thism/";

        assertParseFailure(parser, emptyInput, expectedMessage);
        assertParseFailure(parser, nonsenseInput, expectedMessage);
    }
}
