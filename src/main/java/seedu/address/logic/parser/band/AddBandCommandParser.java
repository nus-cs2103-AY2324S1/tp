package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.stream.Stream;

import seedu.address.logic.commands.band.AddBandCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.band.Band;
import seedu.address.model.band.BandName;


/**
 * Parses input arguments and creates a new AddBandCommand object
 */
public class AddBandCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AddBandCommand
     * and returns an AddBandCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBandCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBandCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);
        BandName name = ParserUtil.parseBandName(argMultimap.getValue(PREFIX_NAME).get());

        // tags and musicians to be implemented later on
        Band band = new Band(name, new HashSet<>(), new HashSet<>());

        return new AddBandCommand(band);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
