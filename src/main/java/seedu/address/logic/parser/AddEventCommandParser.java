package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddEventCommand object.
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENTNAME,
                    PREFIX_SCHEDULE, PREFIX_REMINDER);

        checkPresentPrefixes(argMultimap);
        checkUniquePrefixes(argMultimap);

        String indexString;

        String eventName = argMultimap.getValue(PREFIX_EVENTNAME).get().toUpperCase();
        String schedule = argMultimap.getValue(PREFIX_SCHEDULE).get();
        String reminder = argMultimap.getValue(PREFIX_REMINDER).get();

        indexString = argMultimap.getPreamble().toLowerCase();
        if (indexString.equals("user")) {
            return new AddEventCommand(eventName, schedule, reminder);
        } else {
            try {
                Integer.parseInt(indexString);
                return new AddEventCommand(eventName, ParserUtil.parseIndex(indexString), schedule, reminder);
            } catch (NumberFormatException e) {
                throw new ParseException(String.format("Invalid index!" + "\n"
                    + "Index can only be 'user' or a positive integer! \n"));
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
     *
     * @param argumentMultimap The argument multimap to check for the presence of prefixes.
     * @throws ParseException If any of the required prefixes are missing.
     */
    private static void checkPresentPrefixes(ArgumentMultimap argumentMultimap) throws ParseException {
        if (!arePrefixesPresent(argumentMultimap, PREFIX_EVENTNAME,
            PREFIX_SCHEDULE, PREFIX_REMINDER)) {
            List<Prefix> missingPrefix = getMissingPrefixes(argumentMultimap, PREFIX_EVENTNAME,
                PREFIX_SCHEDULE, PREFIX_REMINDER);
            String missingPrefixString = "";
            for (Prefix prefix : missingPrefix) {
                missingPrefixString += prefix + " ";
            }
            throw new ParseException(String.format("Missing prefix(es) for %s!\n"
                + "Message Usage:\n" + AddEventCommand.MESSAGE_USAGE, missingPrefixString));
        }
    }

    /**
     * Returns the prefixes that is not present in the given {@code ArgumentMultimap}.
     * @throws ParseException if the user input does not conform the expected format
     */
    private static List<Prefix> getMissingPrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
            .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Returns true if there are duplicate prefixes
     * @param argumentMultimap
     * @param prefixes
     */
    private static boolean arePrefixesUnique(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() == 1);
    }

    /**
     * Checks if the prefixes for event name, schedule and reminder are unique in the given ArgumentMultimap.
     * Throws a ParseException if there are duplicate prefixes.
     *
     * @param argumentMultimap the ArgumentMultimap to check for duplicate prefixes
     * @throws ParseException if there are duplicate prefixes
     */
    private static void checkUniquePrefixes(ArgumentMultimap argumentMultimap) throws ParseException {
        if (!arePrefixesUnique(argumentMultimap, PREFIX_EVENTNAME,
            PREFIX_SCHEDULE, PREFIX_REMINDER)) {
            List<Prefix> duplicatePrefix = getDuplicatePrefixes(argumentMultimap, PREFIX_EVENTNAME,
                PREFIX_SCHEDULE, PREFIX_REMINDER);
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
