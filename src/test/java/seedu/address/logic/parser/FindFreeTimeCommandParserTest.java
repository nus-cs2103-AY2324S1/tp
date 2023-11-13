package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_MAX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_NEGATIVE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_ZERO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_THIRTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FindFreeTimeCommand;
import seedu.address.model.Duration;

public class FindFreeTimeCommandParserTest {
    private final FindFreeTimeCommandParser parser = new FindFreeTimeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Command command = new FindFreeTimeCommand(VALID_GROUP_BOB, new Duration(30));
        String userInput = GROUP_DESC_BOB + VALID_DURATION_THIRTY;
        assertParseSuccess(parser, userInput, command);
    }

    // group missing
    @Test
    public void parse_groupFieldMissing_unSuccessful() {
        String userInput = GROUP_DESC_BOB;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFreeTimeCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    // duration missing
    @Test
    public void parse_durationFieldMissing_unSuccessful() {
        String userInput = VALID_DURATION_THIRTY;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFreeTimeCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    // duration and group empty with prefix
    @Test
    public void parse_emptyPrefix_unSuccessful() {
        String userInput = PREFIX_GROUPTAG.getPrefix() + PREFIX_DURATION.getPrefix();
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFreeTimeCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    // duration is 0
    @Test
    public void parse_zeroDuration_unSuccessful() {
        String userInput = GROUP_DESC_BOB + INVALID_DURATION_ZERO_DESC;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            "You can't have a meeting without specifying a duration");
        assertParseFailure(parser, userInput, expectedMessage);
    }

    // duration < 0
    @Test
    public void parse_negativeDuration_unSuccessful() {
        String userInput = GROUP_DESC_BOB + INVALID_DURATION_NEGATIVE_DESC;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            "Duration specified is less than 0");
        assertParseFailure(parser, userInput, expectedMessage);
    }

    // duration exceeds number of minutes in a week
    @Test
    public void parse_exceedMaxDuration_unSuccessful() {
        String userInput = GROUP_DESC_BOB + INVALID_DURATION_MAX_DESC;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            String.format("The value you entered, %d is beyond the time you have in a week", 10080));
        assertParseFailure(parser, userInput, expectedMessage);
    }

}
