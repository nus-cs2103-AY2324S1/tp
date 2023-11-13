package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.band.FindBandCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.band.Band;
import seedu.address.model.band.BandName;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindBandCommand object.
 */
public class FindBandCommandParser implements Parser<FindBandCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBandCommand
     * and returns a FindBandCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindBandCommand parse(String args) throws ParseException {
        try {
            BandName bandName = ParserUtil.parseBandName(args);
            Predicate<Band> bandNamePredicate = new BandNameContainsKeywordsPredicate(bandName.toString());
            return new FindBandCommand(bandNamePredicate);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBandCommand.MESSAGE_USAGE), pe);
        }
    }
}
