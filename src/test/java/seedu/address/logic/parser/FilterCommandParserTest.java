package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialContainsSlotsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "1", Tutorial.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "1 2", Tutorial.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validAndInvalidArgs_throwsParseException() {
        assertParseFailure(parser, "01 2", Tutorial.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArg_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new TutorialContainsSlotsPredicate(Arrays.asList("01")));
        assertParseSuccess(parser, "01", expectedFilterCommand);

        // multiple whitespaces before and after slot
        assertParseSuccess(parser, " \n 01 \n \t", expectedFilterCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new TutorialContainsSlotsPredicate(Arrays.asList("01", "02")));
        assertParseSuccess(parser, "01 02", expectedFilterCommand);

        // multiple whitespaces between slots
        assertParseSuccess(parser, " \n 01 \n \t 02  \t", expectedFilterCommand);
    }
}
