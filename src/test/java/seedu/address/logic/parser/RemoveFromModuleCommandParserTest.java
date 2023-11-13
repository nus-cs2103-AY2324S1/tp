package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveFromModuleCommand;
import seedu.address.model.module.Module;

public class RemoveFromModuleCommandParserTest {
    private final RemoveFromModuleCommandParser parser = new RemoveFromModuleCommandParser();
    @Test
    public void parse_validArgs_returnsAddToModuleCommand() {
        Module cs1000 = new Module("CS1000");

        assertParseSuccess(parser, "1 m/CS1000",
                new RemoveFromModuleCommand(INDEX_FIRST_PERSON, cs1000));
    }
    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a m/CS1000",
                String.format(ParserUtil.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "m/CS1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noModule_throwsParseException() {
        assertParseFailure(parser, "f/CS1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromModuleCommand.MESSAGE_USAGE));
    }
}
