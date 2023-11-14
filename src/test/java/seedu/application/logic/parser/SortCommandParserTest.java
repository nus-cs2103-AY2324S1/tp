package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.SortCommand;
import seedu.application.model.job.FieldComparator;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_invalidArgs_throwParseException() {
        // extra characters after "sort" that are no specifiers
        assertParseFailure(parser, " by role",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // more than one specifier
        assertParseFailure(parser, " " + PREFIX_COMPANY + " " + PREFIX_STATUS,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // invalid specifier
        assertParseFailure(parser, " b/",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // no arguments
        assertParseFailure(parser, " ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        FieldComparator fieldComparator = new FieldComparator(PREFIX_COMPANY);
        SortCommand expectedCommand = new SortCommand(fieldComparator);
        assertParseSuccess(parser, " " + PREFIX_COMPANY, expectedCommand);
    }
}
