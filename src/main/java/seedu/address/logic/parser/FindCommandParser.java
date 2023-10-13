package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Predicate<Person> checkAllFields = checkAllFieldsPredicate(argMultimap);

        return new FindCommand(checkAllFields);
    }

    /**
     * Returns a Predicate that checks all the relevant fields of a {@code Person} object
     * against the keywords provided in the {@code ArgumentMultimap}.
     * <p>
     * The returned predicate will return {@code true} for a {@code Person} object if
     * all its fields match all the corresponding keywords specified in the {@code argMultimap}.
     * </p>
     *
     * @param argMultimap the parsed user input containing prefixes and their values.
     * @return a Predicate checking all the fields of a Person against the keywords.
     */
    private static Predicate<Person> checkAllFieldsPredicate(ArgumentMultimap argMultimap) {
        Predicate<Person> checkNames = person -> (argMultimap.getAllValues(PREFIX_NAME).stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)));
        Predicate<Person> checkPhones = person -> (argMultimap.getAllValues(PREFIX_PHONE).stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword)));
        Predicate<Person> checkEmails = person -> (argMultimap.getAllValues(PREFIX_EMAIL).stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword)));
        Predicate<Person> checkAddresses = person -> (argMultimap.getAllValues(PREFIX_ADDRESS).stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword)));

        // tags are slightly more complicated -- here a person passes the predicate if their tags
        // is a superset of the tags specified in the find command
        Predicate<Person> checkTags = person -> (argMultimap.getAllValues(PREFIX_TAG).stream()
                .allMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword))));

        return checkNames.and(checkPhones).and(checkEmails).and(checkAddresses).and(checkTags);
    }

}
