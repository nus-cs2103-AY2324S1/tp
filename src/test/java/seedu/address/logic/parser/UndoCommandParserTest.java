package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.UndoCommand.INVALID_STEPS_TO_UNDO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

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

    @Test
    public void parse_validSteps_success() {
        UndoCommand expectedUndoCommand = new UndoCommand(3);
        assertParseSuccess(parser, "3", expectedUndoCommand);

        expectedUndoCommand = new UndoCommand(999);
        assertParseSuccess(parser, "999", expectedUndoCommand);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        UndoCommand command = new UndoCommand(3);
        assertTrue(command.equals(command));
    }

    @Test
    public void equals_equalStepsToUndo_returnsTrue() {
        UndoCommand command1 = new UndoCommand(3);
        UndoCommand command2 = new UndoCommand(3);
        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_differentStepsToUndo_returnsFalse() {
        UndoCommand command1 = new UndoCommand(3);
        UndoCommand command2 = new UndoCommand(4);
        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_null_returnsFalse() {
        UndoCommand command = new UndoCommand(3);
        assertFalse(command.equals(null));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        UndoCommand command = new UndoCommand(3);
        assertFalse(command.equals("This is a string"));
    }
}
