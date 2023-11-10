package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BINDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINDEX;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.band.AddMusicianToBandCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddMusiciantoBandCommand object
 */
public class AddMusicianToBandCommandParser implements Parser<AddMusicianToBandCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddMusiciantoBandCommand
     * and returns an AddMusiciantoBandCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMusicianToBandCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BINDEX, PREFIX_MINDEX);
        if (!arePrefixesPresent(argMultimap, PREFIX_BINDEX, PREFIX_MINDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMusicianToBandCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getAllValues(PREFIX_BINDEX).size() > 1) {
            throw new ParseException(AddMusicianToBandCommand.MESSAGE_MULTIPLE_BAND_INDICES);
        }
        Index bandIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_BINDEX).get());
        List<Index> musicianIndices = new ArrayList<>();
        for (String indexString : argMultimap.getAllValues(PREFIX_MINDEX)) {
            musicianIndices.add(ParserUtil.parseIndex(indexString));
        }
        if (musicianIndices.size() > new HashSet<>(musicianIndices).size()) {
            throw new ParseException(AddMusicianToBandCommand.MESSAGE_MUSICIAN_INDEX_REPEATED);
        }
        return new AddMusicianToBandCommand(bandIndex, musicianIndices);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

