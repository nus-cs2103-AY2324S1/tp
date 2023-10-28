package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.UndoCommand.INVALID_STEPS_TO_UNDO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UndoCommand;

public class UndoCommandParserTest {

    private final UndoCommandParser parser = new UndoCommandParser();

    @Test
    public void parse_invalidSteps_throwsParseException() {
        assertParseFailure(parser, "-9", String.format(INVALID_STEPS_TO_UNDO));
        assertParseFailure(parser, "0", String.format(INVALID_STEPS_TO_UNDO));
    }

    @Test
    public void parse_nonNumericInput_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
    }

    //need add for positive
}
