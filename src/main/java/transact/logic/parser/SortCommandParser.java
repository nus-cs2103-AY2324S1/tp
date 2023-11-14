package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static transact.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static transact.logic.parser.CliSyntax.PREFIX_DATE_SORTING;

import java.util.Comparator;
import java.util.List;

import transact.logic.commands.SortCommand;
import transact.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    @Override
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE_SORTING,
                PREFIX_AMOUNT);
        argMultimap.verifyNotEmpty(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE_SORTING, PREFIX_AMOUNT);

        List<ArgumentTokenizer.PrefixPosition> prefixPositions = ArgumentTokenizer.findAllPrefixPositions(args,
                PREFIX_DATE_SORTING, PREFIX_AMOUNT);
        prefixPositions.sort(Comparator.comparingInt(ArgumentTokenizer.PrefixPosition::getStartPosition));

        SortCommand.SortRules sortRules = new SortCommand.SortRules();
        for (ArgumentTokenizer.PrefixPosition prefixPosition : prefixPositions) {
            String ascDesc = argMultimap.getValue(prefixPosition.getPrefix()).get();
            boolean isAscending = true;
            switch (ascDesc.toLowerCase()) {
            case "asc":
                break;
            case "desc":
                isAscending = false;
                break;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SortCommand.MESSAGE_USAGE));
            }
            switch (prefixPosition.getPrefix().getPrefix()) {
            case "date/":
                sortRules.addSortRule(new SortCommand.SortInfo(SortCommand.SortType.DATE, isAscending));
                break;
            case "amt/":
                sortRules.addSortRule(new SortCommand.SortInfo(SortCommand.SortType.AMOUNT, isAscending));
                break;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SortCommand.MESSAGE_USAGE));
            }
        }

        return new SortCommand(sortRules);
    }
}
