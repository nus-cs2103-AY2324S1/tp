package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveFromTutorialCommand;
import seedu.address.model.module.Module;
import seedu.address.model.tutorial.Tutorial;

class RemoveFromTutorialCommandParserTest {
    private final RemoveFromTutorialCommandParser parser = new RemoveFromTutorialCommandParser();

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "m/CS1000 tn/T11",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromTutorialCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noModule_throwsParseException() {
        assertParseFailure(parser, "1 tn/T11",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromTutorialCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noTutorial_throwsParseException() {
        assertParseFailure(parser, "1 m/CS1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromTutorialCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a m/CS1000 tn/ T11",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromTutorialCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAddToModuleCommand() {
        // data
        Module testMod = new Module("CS1000");
        Tutorial testTut = new Tutorial(testMod, "T11");

        // result
        assertParseSuccess(parser, "1 m/CS1000 tn/T11",
                new RemoveFromTutorialCommand(INDEX_FIRST_PERSON, testTut));
    }
}
