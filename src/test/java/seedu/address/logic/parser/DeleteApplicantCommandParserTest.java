package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteApplicantCommand;


public class DeleteApplicantCommandParserTest {

    private DeleteApplicantCommandParser parser = new DeleteApplicantCommandParser();

    @Test
        public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteApplicantCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteApplicantCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNumber_throwsParseException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteApplicantCommand.MESSAGE_USAGE));
    }
}
