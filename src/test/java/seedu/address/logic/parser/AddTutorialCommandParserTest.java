package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TUTORIAL_TIME_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.model.module.Module;
import seedu.address.model.tutorial.Tutorial;

public class AddTutorialCommandParserTest {
    private AddTutorialCommandParser parser = new AddTutorialCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddTutorialCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_validTutorialTimeFormat_returnsAddTutorialCommand() {
        Module testModule = new Module("CS1000");
        AddTutorialCommand expectedTutorialCommand =
                new AddTutorialCommand(new Tutorial(testModule, "test name", "Mon 2PM"));
        assertParseSuccess(parser, " m/CS1000 tn/test name tt/Mon 2PM", expectedTutorialCommand);
    }

    @Test
    public void parse_invalidTutorialTimeFormat_throwsParseException() {
        Module testModule = new Module("CS1000");
        AddTutorialCommand expectedTutorialCommand =
                new AddTutorialCommand(new Tutorial(testModule, "test name", "test time"));
        assertParseFailure(parser, " m/CS1000 tn/test name tt/test time",
                String.format(MESSAGE_INVALID_TUTORIAL_TIME_FORMAT, AddTutorialCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " m/CS1000 tn/test name tt/Mon 0PM",
                String.format(MESSAGE_INVALID_TUTORIAL_TIME_FORMAT, AddTutorialCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " m/CS1000 tn/test name tt/Mon 13PM",
                String.format(MESSAGE_INVALID_TUTORIAL_TIME_FORMAT, AddTutorialCommand.MESSAGE_USAGE));
    }
}
