package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.ListBandCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.band.Band;
import seedu.address.model.band.BandName;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListBandCommand object.
 */
public class ListBandCommandParser implements Parser<ListBandCommand> {

    @Override
    public ListBandCommand parse(String args) throws ParseException {
        try {
            BandName bandName = ParserUtil.parseBandName(args);
            Predicate<Band> bandNamePredicate = new BandNameContainsKeywordsPredicate(bandName.toString());
            return new ListBandCommand(bandNamePredicate);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListBandCommand.MESSAGE_USAGE), pe);
        }
    }
}
