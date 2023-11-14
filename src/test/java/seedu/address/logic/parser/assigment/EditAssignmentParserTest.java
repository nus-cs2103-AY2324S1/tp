package seedu.address.logic.parser.assigment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.assignment.EditAssignmentParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fields.Description;

public class EditAssignmentParserTest {

    private final EditAssignmentParser parser = new EditAssignmentParser();

    private final String duplicatePrefix = "Multiple values specified for the following single-valued field(s): ";

    @Test
    public void parse_duplicateDescriptionPrefix_failure() {
        String userInput = " i/i d/desc d/valid input";
        assertParseFailure(parser, userInput, duplicatePrefix + "d/");
    }

    @Test
    public void parse_duplicateIndexPrefix_failure() {
        String userInput = " i/i i/2 d/valid input";
        assertParseFailure(parser, userInput, duplicatePrefix + "i/");
    }

    @Test
    public void parse_missingIndexPrefix_failure() {
        String userInput = "  d/valid input";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingDescriptionPrefix_failure() {
        String userInput = "  i/1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyInput_failure() {
        String userInput = " ";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        String userInput = " i/1 1 d/valid input";
        assertParseFailure(parser, userInput, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidIndex2_failure() {
        String userInput = " i/0 d/valid input";
        assertParseFailure(parser, userInput, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidIndex3_failure() {
        String userInput = " i/-1 d/valid input";
        assertParseFailure(parser, userInput, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        String userInput = " i/2 d/valid input";
        EditAssignmentCommand c = new EditAssignmentCommand(ParserUtil.parseIndex("2"),
                new Description("valid input"));
        assertParseSuccess(parser, userInput, c);
    }
}
