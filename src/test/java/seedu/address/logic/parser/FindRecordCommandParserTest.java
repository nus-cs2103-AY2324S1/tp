package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.model.record.RecordContainsKeywordsPredicate;

public class FindRecordCommandParserTest {
    private FindRecordCommandParser parser = new FindRecordCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindRecordCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindRecordCommand() {
        // no leading and trailing whitespaces
        FindRecordCommand expectedFindRecordCommand =
                new FindRecordCommand(new RecordContainsKeywordsPredicate(Arrays.asList("Tylenol")));
        assertParseSuccess(parser, "Tylenol", expectedFindRecordCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Tylenol \n", expectedFindRecordCommand);
    }
}
