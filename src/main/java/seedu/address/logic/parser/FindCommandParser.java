package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_BLANK_ARGUMENTS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindPredicateMap;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.predicates.AgeContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
import seedu.address.model.person.predicates.MedHistoryContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.SpecialtyContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements ParserComplex<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(PersonType personType, String args) throws ParseException {
        if (personType.equals(PersonType.PATIENT)) {
            return parsePatient(args);
        } else if (personType.equals(PersonType.SPECIALIST)) {
            return parseSpecialist(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_PERSON_TYPE));
        }
    }

    private FindCommand parsePatient(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_TAG, PREFIX_AGE, PREFIX_MEDICALHISTORY);

        if (!argMultimap.getPreamble().isBlank() && !args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE_PATIENT));
        }

        if (argMultimap.anyValuesBlank(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_TAG, PREFIX_AGE, PREFIX_MEDICALHISTORY)) {
            throw new ParseException(String.format(MESSAGE_BLANK_ARGUMENTS,
                    FindCommand.MESSAGE_USAGE_PATIENT));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_AGE, PREFIX_MEDICALHISTORY);

        FindPredicateMap findPredicateMap = setupPersonPredicates(argMultimap);

        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            List<String> ageKeyWords = splitKeywordsByWhitespace(argMultimap, PREFIX_AGE);
            findPredicateMap.put(PREFIX_AGE, new AgeContainsKeywordsPredicate(ageKeyWords));
        }
        if (argMultimap.getValue(PREFIX_MEDICALHISTORY).isPresent()) {
            List<String> medHistKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_MEDICALHISTORY);
            findPredicateMap.put(PREFIX_MEDICALHISTORY,
                    new MedHistoryContainsKeywordsPredicate(medHistKeywords));
        }
        return new FindCommand(findPredicateMap, PersonType.PATIENT);
    }

    private FindCommand parseSpecialist(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION,
                        PREFIX_TAG, PREFIX_SPECIALTY);

        if (!argMultimap.getPreamble().isBlank() && !args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE_SPECIALIST));
        }

        if (argMultimap.anyValuesBlank(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION,
                PREFIX_TAG, PREFIX_SPECIALTY)) {
            throw new ParseException(String.format(MESSAGE_BLANK_ARGUMENTS,
                    FindCommand.MESSAGE_USAGE_SPECIALIST));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_LOCATION, PREFIX_SPECIALTY);

        FindPredicateMap findPredicateMap = setupPersonPredicates(argMultimap);

        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            List<String> addressKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_LOCATION);
            findPredicateMap.put(PREFIX_LOCATION, new LocationContainsKeywordsPredicate(addressKeywords));
        }
        if (argMultimap.getValue(PREFIX_SPECIALTY).isPresent()) {
            List<String> specialtyKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_SPECIALTY);
            findPredicateMap.put(PREFIX_SPECIALTY, new SpecialtyContainsKeywordsPredicate(specialtyKeywords));
        }

        return new FindCommand(findPredicateMap, PersonType.SPECIALIST);
    }

    private List<String> splitKeywordsByWhitespace(ArgumentMultimap argMultimap, Prefix prefix) {
        assert argMultimap.getValue(prefix).isPresent();
        String trimmedArgs = argMultimap.getValue(prefix).get().trim();
        String[] keywords = trimmedArgs.split("\\s+");
        return Arrays.asList(keywords);
    }

    private FindPredicateMap setupPersonPredicates(ArgumentMultimap argMultimap) {
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_NAME);
            findPredicateMap.put(PREFIX_NAME, new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            List<String> phoneKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_PHONE);
            findPredicateMap.put(PREFIX_PHONE, new PhoneContainsKeywordsPredicate(phoneKeywords));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            List<String> emailKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_EMAIL);
            findPredicateMap.put(PREFIX_EMAIL, new EmailContainsKeywordsPredicate(emailKeywords));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tagKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_TAG);
            findPredicateMap.put(PREFIX_TAG, new TagsContainsKeywordsPredicate(tagKeywords));
        }
        return findPredicateMap;
    }
}
