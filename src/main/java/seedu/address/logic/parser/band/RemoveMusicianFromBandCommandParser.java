package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BINDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.band.RemoveMusicianFromBandCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveMusicianFromBandCommand object.
 */
public class RemoveMusicianFromBandCommandParser implements Parser<RemoveMusicianFromBandCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveMusicianFromBandCommand
     * and returns a RemoveMusicianFromBandCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public RemoveMusicianFromBandCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_BINDEX, PREFIX_MINDEX);
        if (!arePrefixesPresent(argMultimap, PREFIX_BINDEX, PREFIX_MINDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveMusicianFromBandCommand.MESSAGE_USAGE));
        }
        try {
            Index bandIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_BINDEX).get());
            Index musicianIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MINDEX).get());
            return new RemoveMusicianFromBandCommand(bandIndex, musicianIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMusicianFromBandCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

