package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddScheduleCommand object.
 */
public class AddScheduleCommandParser implements Parser<AddScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddScheduleCommand
     * and returns an AddScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENTNAME, PREFIX_EVENTTYPE, PREFIX_SCHEDULE);

        checkPresentPrefixes(argMultimap);
        checkUniquePrefixes(argMultimap);

        String indexString;

        String eventName = argMultimap.getValue(PREFIX_EVENTNAME).get().toUpperCase();
        String eventType = argMultimap.getValue(PREFIX_EVENTTYPE).get().toLowerCase();
        String schedule = argMultimap.getValue(PREFIX_SCHEDULE).get();

        indexString = argMultimap.getPreamble().toLowerCase();
        if (indexString.equals("user")) {
            return new AddScheduleCommand(eventName, eventType, schedule);
        } else {
            try {
                Integer.parseInt(indexString);
                return new AddScheduleCommand(eventName, eventType,
                        ParserUtil.parseIndex(indexString), schedule);
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
     * Checks if the required prefixes for adding an event are present in the given argument multimap.
     * Throws a ParseException if any of the required prefixes are missing.
     * @param argMultimap The argument multimap to check for the presence of prefixes.
     * @throws ParseException If any of the required prefixes are missing.
     */
    private static void checkPresentPrefixes(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_EVENTNAME, PREFIX_EVENTTYPE, PREFIX_SCHEDULE)) {
            List<Prefix> missingPrefix = getMissingPrefixes(argMultimap, PREFIX_EVENTNAME,
                PREFIX_EVENTTYPE, PREFIX_SCHEDULE);
            String missingPrefixString = "";
            for (Prefix prefix : missingPrefix) {
                missingPrefixString += prefix + " ";
            }
            throw new ParseException(String.format("Missing prefix(es) for %s!\n"
                + "Message Usage:\n" + AddScheduleCommand.MESSAGE_USAGE, missingPrefixString));
        }
    }

    /**
     * Returns the prefixes that is not present in the given {@code ArgumentMultimap}.
     */
    private static List<Prefix> getMissingPrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
            .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Returns true if there are duplicate prefixes
     * @param argumentMultimap The argument multimap to check for duplicate prefixes.
     * @param prefixes The prefixes to check for duplicates.
     */
    private static boolean arePrefixesUnique(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() == 1);
    }

    /**
     * Checks if there are duplicate prefixes in the given argument multimap.
     * Throws a ParseException if there are duplicate prefixes.
     * @param argMultimap The argument multimap to check for duplicate prefixes.
     * @throws ParseException If there are duplicate prefixes.
     */
    private static void checkUniquePrefixes(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesUnique(argMultimap, PREFIX_EVENTNAME,
            PREFIX_EVENTTYPE, PREFIX_SCHEDULE)) {
            List<Prefix> duplicatePrefix = getDuplicatePrefixes(argMultimap, PREFIX_EVENTNAME,
                PREFIX_EVENTTYPE, PREFIX_SCHEDULE);
            String duplicatePrefixString = "";
            for (Prefix prefix : duplicatePrefix) {
                duplicatePrefixString += prefix + " ";
            }
            throw new ParseException(String.format("You can only have 1 of each prefix!\n"
                + "Duplicated prefixes are: " + duplicatePrefixString));
        }
    }

    /**
     * Returns the prefixes that are not unique in the given {@code ArgumentMultimap}.
     */
    private static List<Prefix> getDuplicatePrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getAllValues(prefix).size() > 1)
            .collect(java.util.stream.Collectors.toList());
    }
}
