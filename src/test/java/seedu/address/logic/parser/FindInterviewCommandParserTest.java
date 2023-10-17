package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.model.interview.JobContainsKeywordsPredicate;

public class FindInterviewCommandParserTest {

    private FindInterviewCommandParser parser = new FindInterviewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindInterviewCommand() {
        // no leading and trailing whitespaces
        FindInterviewCommand expectedFindCommand =
                new FindInterviewCommand(
                        new JobContainsKeywordsPredicate(Arrays.asList("Software-Engineer", "Data-Analyst")));
        assertParseSuccess(parser, "Software-Engineer Data-Analyst", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Software-Engineer \n \t Data-Analyst  \t", expectedFindCommand);
    }

}
