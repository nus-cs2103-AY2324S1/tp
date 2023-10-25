package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.applicant.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.applicant.predicate.PhoneContainsNumberPredicate;
import seedu.address.model.applicant.predicate.TagsContainKeywordsPredicate;

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
        requireNonNull(args);

        Predicate<Applicant> ALWAYS_TRUE = t -> true;
        Predicate<Applicant> namePredicate, phonePredicate,
                emailPredicate, addressPredicate,
                tagsPredicate;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);


        // TODO: make predicate for each value
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            namePredicate = new NameContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_NAME));
        } else {
            namePredicate = ALWAYS_TRUE;
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phonePredicate = new PhoneContainsNumberPredicate(argMultimap.getValue(PREFIX_PHONE).get());
        } else {
            phonePredicate = ALWAYS_TRUE;
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            emailPredicate = new EmailContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_EMAIL));
        } else {
            emailPredicate = ALWAYS_TRUE;
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            addressPredicate = new AddressContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_ADDRESS));
        } else {
            addressPredicate = ALWAYS_TRUE;
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagsPredicate = new TagsContainKeywordsPredicate(argMultimap.getAllValues(PREFIX_TAG));
        } else {
            tagsPredicate = ALWAYS_TRUE;
        }

        Predicate<Applicant> applicantPredicate = applicant ->
                namePredicate.test(applicant) && phonePredicate.test(applicant)
                        && emailPredicate.test(applicant) && addressPredicate.test(applicant)
                        && tagsPredicate.test(applicant);

        return new FindCommand(applicantPredicate);
    }
}
