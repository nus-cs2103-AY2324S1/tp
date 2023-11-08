package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTeamCommand;
import seedu.address.model.team.TeamContainsKeywordsPredicate;

public class FindTeamCommandParserTest {

    private FindTeamCommandParser parser = new FindTeamCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTeamCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTeamCommand() {
        // no leading and trailing whitespaces
        FindTeamCommand expectedFindTeamCommand =
                new FindTeamCommand(new TeamContainsKeywordsPredicate(Arrays.asList("Team1", "Team2")));
        assertParseSuccess(parser, "Team1 Team2", expectedFindTeamCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Team1 \n \t Team2  \t", expectedFindTeamCommand);
    }
}
