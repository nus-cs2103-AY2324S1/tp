package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                    args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_BIRTHDAY, PREFIX_TAG);

        checkPresentPrefixes(argMultimap);
        checkUniquePrefixes(argMultimap);

        // Check if all prefixes are unique
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_BIRTHDAY);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Birthday birthday = ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Schedule schedule = new Schedule();
        Person person = new Person(name, phone, email, address, birthday, schedule, tagList);

        return new AddCommand(person);
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
        if (!arePrefixesPresent(argumentMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_BIRTHDAY)) {
            List<Prefix> missingPrefix = getMissingPrefixes(argumentMultimap, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_BIRTHDAY);
            String missingPrefixString = "";
            for (Prefix prefix : missingPrefix) {
                missingPrefixString += prefix + " ";
            }
            throw new ParseException(String.format("Missing prefix(es) for %s!\n"
                + "Message Usage:\n" + AddCommand.MESSAGE_USAGE, missingPrefixString));
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
        if (!arePrefixesUnique(argumentMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_BIRTHDAY)) {
            List<Prefix> duplicatePrefix = getDuplicatePrefixes(argumentMultimap, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_BIRTHDAY);
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
