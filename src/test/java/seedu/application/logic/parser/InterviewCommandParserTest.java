package seedu.application.logic.parser;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.HelpCommand;
import seedu.application.logic.commands.InterviewAddCommand;
import seedu.application.logic.commands.InterviewDeleteCommand;
import seedu.application.logic.commands.InterviewEditCommand;
import seedu.application.logic.parser.exceptions.ParseException;

class InterviewCommandParserTest {
    private final InterviewCommandParser parser = new InterviewCommandParser();

    @Test
    public void parseCommand_add() throws Exception {
        assertTrue(parser.parse("add 1 " + PREFIX_INTERVIEW_TYPE + "Technical "
                + PREFIX_INTERVIEW_DATETIME + "Dec 31 2030 1200 " + PREFIX_INTERVIEW_ADDRESS
                + "Home") instanceof InterviewAddCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        assertTrue(parser.parse("delete 1 " + PREFIX_JOB_SOURCE + "1 ") instanceof InterviewDeleteCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        assertTrue(parser.parse("edit 1 " + PREFIX_JOB_SOURCE + "2 " + PREFIX_INTERVIEW_TYPE + "Online ")
                instanceof InterviewEditCommand);
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parse("unknownCommand"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parse(""));
    }
}
