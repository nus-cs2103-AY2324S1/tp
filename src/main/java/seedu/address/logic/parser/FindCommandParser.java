package seedu.address.logic.parser;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
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
        if (args.isBlank()) {
            if (personType.equals(PersonType.PATIENT)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommand.MESSAGE_USAGE_PATIENT));
            }
            if (personType.equals(PersonType.SPECIALIST)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommand.MESSAGE_USAGE_SPECIALIST));
            }
        }
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

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_AGE, PREFIX_MEDICALHISTORY);

        List<Predicate<Person>> predicateList = setupPersonPredicates(argMultimap);
        predicateList.add(PersonType.PATIENT.getSearchPredicate());

        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            List<String> ageKeyWords = splitKeywordsByWhitespace(argMultimap, PREFIX_AGE);
            predicateList.add(new AgeContainsKeywordsPredicate(ageKeyWords));
        }

        if (argMultimap.getValue(PREFIX_MEDICALHISTORY).isPresent()) {
            List<String> medHistKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_MEDICALHISTORY);
            predicateList.add(new MedHistoryContainsKeywordsPredicate(medHistKeywords));
        }

        Predicate<Person> combinedPredicate = person -> predicateList.stream().map(p -> p.test(person))
                .reduce(true, (x, y) -> x && y);

        return new FindCommand(combinedPredicate, PersonType.PATIENT);
    }

    private FindCommand parseSpecialist(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION,
                        PREFIX_TAG, PREFIX_SPECIALTY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_LOCATION, PREFIX_SPECIALTY);
        List<Predicate<Person>> predicateList = setupPersonPredicates(argMultimap);
        predicateList.add(PersonType.SPECIALIST.getSearchPredicate());

        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            List<String> addressKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_LOCATION);
            predicateList.add(new LocationContainsKeywordsPredicate(addressKeywords));
        }

        if (argMultimap.getValue(PREFIX_SPECIALTY).isPresent()) {
            List<String> specialtyKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_SPECIALTY);
            predicateList.add(new SpecialtyContainsKeywordsPredicate(specialtyKeywords));
        }

        Predicate<Person> combinedPredicate = person -> predicateList.stream().map(p -> p.test(person))
                .reduce(true, (x, y) -> x && y);

        return new FindCommand(combinedPredicate, PersonType.SPECIALIST);
    }

    private List<String> splitKeywordsByWhitespace(ArgumentMultimap argMultimap, Prefix prefix) {
        if (argMultimap.getValue(prefix).isPresent()) {
            String trimmedArgs = argMultimap.getValue(prefix).get().trim();
            String[] keywords = trimmedArgs.split("\\s+");
            return Arrays.asList(keywords);
        }
        return new ArrayList<>();
    }

    private List<Predicate<Person>> setupPersonPredicates(ArgumentMultimap argMultimap) {
        List<Predicate<Person>> predicateList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_NAME);
            predicateList.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            List<String> phoneKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_PHONE);
            predicateList.add(new PhoneContainsKeywordsPredicate(phoneKeywords));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            List<String> emailKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_EMAIL);
            predicateList.add(new EmailContainsKeywordsPredicate(emailKeywords));
        }

        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            predicateList.add(new TagsContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_TAG)));
        }
        return predicateList;
    }
}
