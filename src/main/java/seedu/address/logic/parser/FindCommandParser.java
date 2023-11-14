package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_ID;
import static seedu.address.logic.Messages.MESSAGE_INVALID_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.List;

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
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected
     *                        format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_APPOINTMENT, PREFIX_MEDICAL);

        if (ArgumentMultimap.isAnyPrefixPresent(argMultimap, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_MEDICAL)
                || !ArgumentMultimap.isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_APPOINTMENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID, PREFIX_APPOINTMENT);

        CompositePredicate findCommandPredicate = new CompositePredicate();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
            List<String> nameKeywordsList = Arrays.asList(nameKeywords);
            if (nameKeywordsList.get(0).equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_NAME, FindCommand.MESSAGE_USAGE));
            }
            findCommandPredicate.add(new NameContainsKeywordsPredicate(nameKeywordsList));
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String[] idKeywords = argMultimap.getValue(PREFIX_ID).get().trim().split("\\s+");
            List<String> idKeywordsList = Arrays.asList(idKeywords);
            if (idKeywordsList.get(0).equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_ID, FindCommand.MESSAGE_USAGE));
            }
            findCommandPredicate.add(new IdContainsKeywordsPredicate(idKeywordsList));
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT).isPresent()) {
            Appointment query = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_APPOINTMENT).get());
            findCommandPredicate.add(new AppointmentOverlapsPredicate(query));
        }

        return new FindCommand(findCommandPredicate);
    }
}
