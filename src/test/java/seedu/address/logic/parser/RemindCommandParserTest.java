package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.model.person.predicates.RemindPredicate;

public class RemindCommandParserTest {

    private RemindCommandParser parser = new RemindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_argsNotNumbers_throwsParseException() {
        assertParseFailure(parser, " abcd", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_NOT_NUMBERS));
    }

    @Test
    public void parse_argsNotInRange_throwsParseException() {
        assertParseFailure(parser, " -1", String.format(Messages.MESSAGE_NOT_IN_RANGE, 0, 7305));
        assertParseFailure(parser, " 8000", String.format(Messages.MESSAGE_NOT_IN_RANGE, 0, 7305));
    }

    @Test
    public void parse_validArgs_returnsRemindCommand() {
        // no leading and trailing whitespaces
        RemindCommand expectedRemindCommand = new RemindCommand(new RemindPredicate(30));
        assertParseSuccess(parser, " 30", expectedRemindCommand);
    }

}
