package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAllTagCommand;
import seedu.address.model.person.PersonContainsAllTagsPredicate;
import seedu.address.model.task.TaskContainsAllTagsPredicate;


class FindAllTagCommandParserTest {
    private final FindAllTagCommandParser parser = new FindAllTagCommandParser();

    @Test
    public void parse_validArgs_returnsFindAllTagCommand() {
        // Only one tag keyword
        FindAllTagCommand expectedFindAllTagCommand =
                new FindAllTagCommand(new PersonContainsAllTagsPredicate(Arrays.asList("friends")),
                        new TaskContainsAllTagsPredicate(Arrays.asList("friends")));
        assertParseSuccess(parser, "friends", expectedFindAllTagCommand);

        // Multiple tag keywords separated by whitespace
        expectedFindAllTagCommand =
                new FindAllTagCommand(new PersonContainsAllTagsPredicate(Arrays.asList("friends", "owesMoney")),
                        new TaskContainsAllTagsPredicate(Arrays.asList("friends", "owesMoney")));
        assertParseSuccess(parser, "friends owesMoney", expectedFindAllTagCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllTagCommand.MESSAGE_USAGE));
    }
}
