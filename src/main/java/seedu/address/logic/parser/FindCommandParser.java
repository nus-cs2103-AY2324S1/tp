package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.predicate.AddressContainsKeywordsPredicate;
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

        Predicate<Applicant> alwaysFalse = t -> false;
        Predicate<Applicant> namePredicate;
        Predicate<Applicant> phonePredicate;
        Predicate<Applicant> emailPredicate;
        Predicate<Applicant> addressPredicate;
        Predicate<Applicant> tagsPredicate;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.argumentHasAtLeastOnePrefix(
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            namePredicate = new NameContainsKeywordsPredicate(
                    parseFieldsForFind(argMultimap.getValue(PREFIX_NAME).get()));
        } else {
            namePredicate = alwaysFalse;
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phonePredicate = new PhoneContainsNumberPredicate(argMultimap.getValue(PREFIX_PHONE).get());
        } else {
            phonePredicate = alwaysFalse;
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            emailPredicate = new EmailContainsKeywordsPredicate(
                    parseFieldsForFind(argMultimap.getValue(PREFIX_EMAIL).get()));
        } else {
            emailPredicate = alwaysFalse;
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            addressPredicate = new AddressContainsKeywordsPredicate(
                    parseFieldsForFind(argMultimap.getValue(PREFIX_ADDRESS).get()));
        } else {
            addressPredicate = alwaysFalse;
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagsPredicate = new TagsContainKeywordsPredicate(
                    parseFieldsForFind(argMultimap.getValue(PREFIX_TAG).get()));
        } else {
            tagsPredicate = alwaysFalse;
        }

        Predicate<Applicant> applicantPredicate = applicant ->
                namePredicate.test(applicant)
                        || phonePredicate.test(applicant)
                        || emailPredicate.test(applicant)
                        || addressPredicate.test(applicant)
                        || tagsPredicate.test(applicant);

        return new FindCommand(applicantPredicate);
    }


    /**
     * Parses space separated arguments into a list of strings containing each argument
     * if the arguments are non-empty.
     */
    private List<String> parseFieldsForFind(String args) throws ParseException {
        assert args != null;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return Arrays.asList(trimmedArgs.split("\\s+"));
    }
}
