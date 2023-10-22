package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.model.job.Company.COMPANY_SPECIFIER;
import static seedu.application.model.job.Status.STATUS_SPECIFIER;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.ListCommand;
import seedu.application.model.job.FieldComparator;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_invalidArgs_throwParseException() {
        // extra characters after "list" that are no specifiers
        assertParseFailure(parser, "by role",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        // more than one specifier
        assertParseFailure(parser, COMPANY_SPECIFIER + " " + STATUS_SPECIFIER,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        // invalid specifier
        assertParseFailure(parser, "-b",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_INVALID_SPECIFIER));
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        FieldComparator fieldComparator = new FieldComparator(COMPANY_SPECIFIER);
        ListCommand expectedCommand = new ListCommand(fieldComparator);
        assertParseSuccess(parser, COMPANY_SPECIFIER, expectedCommand);
    }
}
