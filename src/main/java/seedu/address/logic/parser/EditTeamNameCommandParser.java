package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.List;
import java.util.stream.Stream;


import seedu.address.logic.commands.EditTeamNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new EditTeamNameCommand object
 */
public class EditTeamNameCommandParser implements Parser<EditTeamNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTeamNameCommand
     * and returns an EditTeamNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTeamNameCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TEAMNAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_TEAMNAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamNameCommand.MESSAGE_USAGE));
        }

        List<String> teamNames = argMultimap.getAllValues(PREFIX_TEAMNAME);
        if (teamNames.size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamNameCommand.MESSAGE_USAGE));
        }

        String originalTeamName = ParserUtil.parseTeamName(teamNames.get(0));
        String newTeamName = ParserUtil.parseTeamName(teamNames.get(1));

        return new EditTeamNameCommand(originalTeamName, newTeamName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

