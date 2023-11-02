package seedu.classmanager.logic.parser;

import static seedu.classmanager.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_COUNT;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_TUTORIAL_COUNT;
import static seedu.classmanager.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classmanager.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.classmanager.logic.parser.ConfigCommandParser.MESSAGE_INVALID_COUNT_VALUE_TOO_SMALL;

import org.junit.jupiter.api.Test;

import seedu.classmanager.logic.commands.ConfigCommand;

public class ConfigCommandParserTest {

    private final ConfigCommandParser parser = new ConfigCommandParser();

    @Test
    public void parse_validArg_success() {
        int tutorialCount = 3;
        int assignmentCount = 2;
        ConfigCommand expectedCommand = new ConfigCommand(tutorialCount, assignmentCount);
        String argument = " " + PREFIX_TUTORIAL_COUNT + tutorialCount + " "
                + PREFIX_ASSIGNMENT_COUNT + assignmentCount;
        assertParseSuccess(parser, argument, expectedCommand);
    }

    @Test
    public void parse_validArg2_success() {
        int tutorialCount = 1;
        int assignmentCount = 0;
        ConfigCommand expectedCommand = new ConfigCommand(tutorialCount, assignmentCount);
        String argument = " " + PREFIX_ASSIGNMENT_COUNT + assignmentCount + " "
                + PREFIX_TUTORIAL_COUNT + tutorialCount;
        assertParseSuccess(parser, argument, expectedCommand);
    }

    @Test
    public void parse_nonIntArg_failure() {
        String argument = " " + PREFIX_ASSIGNMENT_COUNT + "a "
                + PREFIX_TUTORIAL_COUNT + "t";
        assertParseFailure(parser, argument, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ConfigCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeIntArg_failure() {
        String argument = " " + PREFIX_ASSIGNMENT_COUNT + "-1 "
                + PREFIX_TUTORIAL_COUNT + "2";
        assertParseFailure(parser, argument, String.format(MESSAGE_INVALID_COUNT_VALUE_TOO_SMALL, "assignments"));
    }

    @Test
    public void parse_emptyArg_failure() {
        String argument = " " + PREFIX_ASSIGNMENT_COUNT + " "
                + PREFIX_TUTORIAL_COUNT + "2";
        assertParseFailure(parser, argument, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ConfigCommand.MESSAGE_USAGE));
    }
}
