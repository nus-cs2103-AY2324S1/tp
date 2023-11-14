package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterGroupCommand;
import seedu.address.model.group.GroupBelongsTutorialPredicate;
import seedu.address.model.tutorial.Tutorial;

public class FilterGroupCommandParserTest {

    private FilterGroupCommandParser parser = new FilterGroupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "1", Tutorial.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArg_returnsFilterGroupCommand() {
        // no leading and trailing whitespaces
        FilterGroupCommand expectedFilterGroupCommand = new FilterGroupCommand(new GroupBelongsTutorialPredicate("01"));
        assertParseSuccess(parser, "01", expectedFilterGroupCommand);

        // multiple whitespaces before and after slot
        assertParseSuccess(parser, " \n 01 \n \t", expectedFilterGroupCommand);
    }
}
