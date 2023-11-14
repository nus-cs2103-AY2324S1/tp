package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTutorialCommand;
import seedu.address.model.person.TutorialContainsKeywordsPredicate;

public class FindTutorialCommandParserTest {

    private FindTutorialCommandParser parser = new FindTutorialCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTutorialCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindTutorialCommand expectedFindTutorialCommand =
                new FindTutorialCommand(new TutorialContainsKeywordsPredicate(
                        Arrays.asList("CS2103T/F08", "CS2100/F04")));
        assertParseSuccess(parser, "CS2103T/F08 CS2100/F04", expectedFindTutorialCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103T/F08 \n \t CS2100/F04 \t", expectedFindTutorialCommand);
    }

}
