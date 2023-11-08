package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.state.State;


public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    /* Tests for LIST parameter */

    // Test for empty LIST parameter: defaults to list schedule
    @Test
    public void parse_validArgs_noArgs() {
        assertParseSuccess(parser, "", new ListCommand(State.SCHEDULE));
    }

    // Tests for valid LIST parameters
    @Test
    public void parse_validArgs_schedule() {
        assertParseSuccess(parser, "schedule", new ListCommand(State.SCHEDULE));
    }
    @Test
    public void parse_validArgs_fullTaskList() {
        assertParseSuccess(parser, "tasks", new ListCommand(State.TASK));
    }
    @Test
    public void parse_validArgs_students() {
        assertParseSuccess(parser, "students", new ListCommand(State.STUDENT));
    }

    /* Tests for invalid LIST parameter */

    // Invalid letter parameter
    @Test
    public void parse_invalidArgs_letterThrowsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
    // Invalid number parameter
    @Test
    public void parse_invalidArgs_numberThrowsParseException() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
    // Invalid flag parameter, should show list error, not error regarding flag
    @Test
    public void parse_invalidArgs_wrongLetterThrowsParseException() {
        assertParseFailure(parser, "-name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    /* Tests for valid keywords parameter for student list */

    // Single keyword
    @Test
    public void parse_validArgs_studentsPhone() {
        assertParseSuccess(parser, "students phone", new ListCommand(State.STUDENT, new String[]{"phone"}));
    }
    // Multiple keywords
    @Test
    public void parse_validArgs_studentsPhoneEmail() {
        assertParseSuccess(parser, "students phone email",
                new ListCommand(State.STUDENT, new String[]{"phone", "email"}));
    }
    // 'None' keyword
    @Test
    public void parse_validArgs_none() {
        assertParseSuccess(parser, "students none", new ListCommand(State.STUDENT, new String[]{"none"}));
    }
    // 'None' keyword with other keywords: should disregard other keywords
    @Test
    public void parse_validArgs_noneWithOthers() {
        assertParseSuccess(parser, "students phone none email", new ListCommand(State.STUDENT, new String[]{"none"}));
    }
    // 'All' keyword
    @Test
    public void parse_validArgs_all() {
        assertParseSuccess(parser, "students all", new ListCommand(State.STUDENT,
                parser.DISPLAYABLE_FIELDS.toArray(new String[0])));
    }
    // 'All' keyword with other keywords: should disregard other keywords
    @Test
    public void parse_validArgs_allWithOthers() {
        assertParseSuccess(parser, "students email all subjects",
                new ListCommand(State.STUDENT, parser.DISPLAYABLE_FIELDS.toArray(new String[0])));
    }

    /* Tests for invalid keywords parameter for student list */

    // Incorrect flag: flag that does not exist
    @Test
    public void parse_invalidArgsKeyword_studentsGender() {
        assertParseFailure(parser, "students gender",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }


}
