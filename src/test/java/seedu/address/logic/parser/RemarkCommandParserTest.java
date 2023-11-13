package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

public class RemarkCommandParserTest {

    private RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_argsPreambleIllegalValue_throwsParseException() {
        assertParseFailure(parser, " abc r/Likes to Swim", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " -1 r/Likes to Swim", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_argsDuplicatePrefix_throwsParseException() {
        assertParseFailure(parser, " 1 r/Likes to swim r/Likes cooking",
                Messages.MESSAGE_DUPLICATE_FIELDS + "r/");
    }

    @Test
    public void parse_validArgs_returnsRemarkCommand() {
        // no leading and trailing whitespaces
        RemarkCommand expectedRemarkCommand = new RemarkCommand(Index.fromOneBased(1), new Remark("Likes to swim"));
        assertParseSuccess(parser, " 1 r/Likes to swim", expectedRemarkCommand);

        // empty remark
        expectedRemarkCommand = new RemarkCommand(Index.fromOneBased(1), new Remark(""));
        assertParseSuccess(parser, " 1 r/", expectedRemarkCommand);
    }

}
