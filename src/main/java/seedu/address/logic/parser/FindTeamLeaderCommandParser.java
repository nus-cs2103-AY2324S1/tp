package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindTeamLeaderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.TeamContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTeamLeaderCommand object
 */
public class FindTeamLeaderCommandParser implements Parser<FindTeamLeaderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTeamLeaderCommand
     * and returns a FindTeamLeaderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTeamLeaderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTeamLeaderCommand.MESSAGE_USAGE));
        }

        String[] teamKeywords = trimmedArgs.split("\\s+");

        return new FindTeamLeaderCommand(new TeamContainsKeywordsPredicate(Arrays.asList(teamKeywords)));
    }

}
