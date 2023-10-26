package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.model.job.Company.COMPANY_SPECIFIER;
import static seedu.application.model.job.Status.STATUS_SPECIFIER;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.SortCommand;
import seedu.application.model.job.FieldComparator;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_invalidArgs_throwParseException() {
        // extra characters after "sort" that are no specifiers
        assertParseFailure(parser, "by role",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // more than one specifier
        assertParseFailure(parser, COMPANY_SPECIFIER + " " + STATUS_SPECIFIER,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // invalid specifier
        assertParseFailure(parser, "-b",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_INVALID_SPECIFIER));

        // no arguments
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        FieldComparator fieldComparator = new FieldComparator(COMPANY_SPECIFIER);
        SortCommand expectedCommand = new SortCommand(fieldComparator);
        assertParseSuccess(parser, COMPANY_SPECIFIER, expectedCommand);
    }
}
