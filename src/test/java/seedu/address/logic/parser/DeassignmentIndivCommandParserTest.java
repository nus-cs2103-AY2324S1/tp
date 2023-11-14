package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeassignmentIndivCommand;
import seedu.address.model.person.Name;

public class DeassignmentIndivCommandParserTest {
    private DeassignmentIndivCommandParser parser = new DeassignmentIndivCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 n/Lab1", new DeassignmentIndivCommand(INDEX_FIRST_PERSON, new Name("Lab1")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeassignmentIndivCommand.MESSAGE_USAGE));
    }
}
