package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.state.State;


public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_schedule() {
        assertParseSuccess(parser, "SCHEDULE", new ListCommand(State.SCHEDULE));
    }

    @Test
    public void parse_validArgs_noArgs() {
        assertParseSuccess(parser, "", new ListCommand(State.SCHEDULE));
    }

    @Test
    public void parse_validArgs_students() {
        assertParseSuccess(parser, "STUDENTS", new ListCommand(State.STUDENT));
    }

    @Test
    public void parse_validArgs_studentsPhone() {
        assertParseSuccess(parser, "STUDENTS phone", new ListCommand(State.STUDENT, new String[]{"phone"}));
    }

    @Test
    public void parse_validArgs_studentsPhoneEmail() {
        assertParseSuccess(parser, "STUDENTS phone email",
                new ListCommand(State.STUDENT, new String[]{"phone", "email"}));
    }

    @Test
    public void parse_validArgs_none() {
        assertParseSuccess(parser, "STUDENTS none", new ListCommand(State.STUDENT, new String[]{"none"}));
    }

    @Test
    public void parse_invalidArgsKeyword_studentsGender() {
        assertParseFailure(parser, "STUDENTS gender",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_smallCaseThrowsParseException() {
        assertParseFailure(parser, "student",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_wrongStateThrowsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
