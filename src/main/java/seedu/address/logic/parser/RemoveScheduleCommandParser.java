package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTTYPE;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventCommand object
 */
public class RemoveScheduleCommandParser implements Parser<RemoveScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteEventCommand
     * and returns a DeleteEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENTNAME, PREFIX_EVENTTYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENTNAME, PREFIX_EVENTTYPE)) {
            List<Prefix> missingPrefix = getMissingPrefixes(argMultimap, PREFIX_EVENTNAME, PREFIX_EVENTTYPE);
            String missingPrefixString = "";
            for (Prefix prefix : missingPrefix) {
                missingPrefixString += prefix + " ";
            }
            throw new ParseException(String.format("Missing prefix(es) for %s!\n"
                + "Message Usage:\n" + RemoveScheduleCommand.MESSAGE_USAGE, missingPrefixString));
        }

        if (!arePrefixesUnique(argMultimap, PREFIX_EVENTNAME, PREFIX_EVENTTYPE)) {
            List<Prefix> duplicatePrefix = getDuplicatePrefixes(argMultimap, PREFIX_EVENTNAME, PREFIX_EVENTTYPE);
            String duplicatePrefixString = "";
            for (Prefix prefix : duplicatePrefix) {
                duplicatePrefixString += prefix + " ";
            }
            throw new ParseException(String.format("You can only have 1 of each prefix!\n"
                + "Duplicated prefixes are: " + duplicatePrefixString));
        }

        String eventName = argMultimap.getValue(PREFIX_EVENTNAME).get().toUpperCase();
        String eventType = argMultimap.getValue(PREFIX_EVENTTYPE).get().toLowerCase();
        String indexString = argMultimap.getPreamble().toLowerCase();
        if (indexString.equals("user")) {
            return new RemoveScheduleCommand(eventName, eventType, null);
        } else {
            try {
                Integer.parseInt(indexString);
                return new RemoveScheduleCommand(eventName, eventType,
                        ParserUtil.parseIndex(indexString));
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid index!" + "\n"
                        + "Index can only be 'user' or a positive integer! \n");
            }
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if there are duplicate prefixes
     * @param argumentMultimap The argument multimap to check for the presence of prefixes.
     * @param prefixes The prefixes to check for duplicates.
     */
    private static boolean arePrefixesUnique(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() == 1);
    }

    /**
     * Returns the prefixes that is not present in the given {@code ArgumentMultimap}.
     */
    private static List<Prefix> getMissingPrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
            .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Returns the prefixes that are not unique in the given {@code ArgumentMultimap}.
     */
    private static List<Prefix> getDuplicatePrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getAllValues(prefix).size() > 1)
            .collect(java.util.stream.Collectors.toList());
    }

}
