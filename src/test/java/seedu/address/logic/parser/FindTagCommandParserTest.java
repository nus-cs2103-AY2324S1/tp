package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTagCommand;
import seedu.address.model.person.PersonContainsTagsPredicate;
import seedu.address.model.task.TaskContainsTagsPredicate;


class FindTagCommandParserTest {
    private final FindTagCommandParser parser = new FindTagCommandParser();

    @Test
    public void parse_validArgs_returnsFindTagCommand() {
        // Only one tag keyword
        FindTagCommand expectedFindTagCommand =
                new FindTagCommand(new PersonContainsTagsPredicate(Arrays.asList("friends")),
                        new TaskContainsTagsPredicate(Arrays.asList("friends")));
        assertParseSuccess(parser, "friends", expectedFindTagCommand);

        // Multiple tag keywords separated by whitespace
        expectedFindTagCommand =
                new FindTagCommand(new PersonContainsTagsPredicate(Arrays.asList("friends", "owesMoney")),
                        new TaskContainsTagsPredicate(Arrays.asList("friends", "owesMoney")));
        assertParseSuccess(parser, "friends owesMoney", expectedFindTagCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
    }
}
