package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.clearcommands.ClearAllCommand;
import seedu.address.logic.commands.clearcommands.ClearCommand;
import seedu.address.logic.commands.clearcommands.ClearInternshipCommand;

public class ClearCommandParserTest {
    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notEnoughArgs_throwsParseException() {
        assertParseFailure(parser, "i",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearInternshipCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "i i",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearInternshipCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCompanyIndex_throwsParseException() {
        assertParseFailure(parser, "i a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearInternshipCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "i -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearInternshipCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsClearInternshipCommand() {
        assertParseSuccess(parser, "i 1", new ClearInternshipCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY));
        assertParseSuccess(parser, "i 2", new ClearInternshipCommand(INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY));
    }

    @Test
    public void parse_noneArgs_returnClearAllCommand() {
        assertParseSuccess(parser, "", new ClearAllCommand());
    }
}
