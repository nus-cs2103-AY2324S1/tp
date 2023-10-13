package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
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
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.SpecialtyContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements ParserComplex<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(PersonType personType, String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), personType);
    }

    public FindCommand parsePatient(String args) throws ParseException {
        //Placeholder for
        return new FindCommand(x -> true, PersonType.PATIENT);
    }

    public FindCommand parseSpecialist(String args) throws ParseException {

        final List<Predicate<Person>> predicateList = new ArrayList<>();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_SPECIALTY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_SPECIALTY);

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
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            List<String> addressKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_ADDRESS);
            predicateList.add(new AddressContainsKeywordsPredicate(addressKeywords));
        }
        if (argMultimap.getValue(PREFIX_SPECIALTY).isPresent()) {
            List<String> specialtyKeywords = splitKeywordsByWhitespace(argMultimap, PREFIX_SPECIALTY);
            predicateList.add(new SpecialtyContainsKeywordsPredicate(specialtyKeywords));
        }
        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            predicateList.add(new TagsContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_TAG)));
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
}
