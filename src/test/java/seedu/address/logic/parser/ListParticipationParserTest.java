package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListParticipationParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListParticipationCommand.MESSAGE_USAGE);

    private ListParticipationParser parser = new ListParticipationParser();

    @Test
    public void parse_invalidIndex_failure() {
        // Invalid index (not a positive integer)
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input
        assertParseSuccess(parser, "1", new ListParticipationCommand(Index.fromOneBased(1)));

        // Valid input
        assertParseSuccess(parser, "2", new ListParticipationCommand(Index.fromOneBased(2)));
    }
}
