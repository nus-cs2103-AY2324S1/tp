package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.isAnyPrefixPresent;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.predicates.AppointmentOverlapsPredicate;
import seedu.address.model.person.predicates.CompositePredicate;
import seedu.address.model.person.predicates.IdContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_APPOINTMENT, PREFIX_MEDICAL, PREFIX_TAG);

        if (isAnyPrefixPresent(argMultimap, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_MEDICAL, PREFIX_TAG)
                || !isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC, PREFIX_APPOINTMENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_NRIC, PREFIX_APPOINTMENT);

        CompositePredicate findCommandPredicate = new CompositePredicate();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
            findCommandPredicate.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            String[] nricKeywords = argMultimap.getValue(PREFIX_NRIC).get().trim().split("\\s+");
            findCommandPredicate.add(new IdContainsKeywordsPredicate(Arrays.asList(nricKeywords)));
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT).isPresent()) {
            Appointment query = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_APPOINTMENT).get());
            findCommandPredicate.add(new AppointmentOverlapsPredicate(query));
        }

        return new FindCommand(findCommandPredicate);
    }
}
