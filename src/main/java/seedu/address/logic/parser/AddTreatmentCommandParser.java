package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTreatmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.treatment.Treatment;
import seedu.address.model.treatment.TreatmentCost;
import seedu.address.model.treatment.TreatmentName;
import seedu.address.model.treatment.TreatmentTime;

/**
 * Parses input arguments and creates a new AddTreatment object.
 */
public class AddTreatmentCommandParser implements Parser<AddTreatmentCommand> {

    /**
     * Parses the given {@code args} of arguments into an AddTreatmentCommand.
     *
     * @param args User input string.
     * @return The parsed AddTreatment command.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddTreatmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TREATMENT, PREFIX_COST, PREFIX_TIME
            );

        if (!arePrefixesPresent(argMultimap, PREFIX_TREATMENT, PREFIX_COST, PREFIX_TIME
        ) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddTreatmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COST, PREFIX_TREATMENT, PREFIX_TIME);

        TreatmentCost cost = ParserUtil.parseTreatmentCost(argMultimap.getValue(PREFIX_COST).get());
        TreatmentName name = ParserUtil.parseTreatmentName(
            argMultimap.getValue(PREFIX_TREATMENT).get());
        TreatmentTime time = ParserUtil.parseTreatmentTime(argMultimap.getValue(PREFIX_TIME).get());

        Treatment treatment = new Treatment(name, cost, time);
        return new AddTreatmentCommand(treatment);

    }

    /**
     * Checks if all the specified prefixes are present in the given {@code argumentMultimap}.
     *
     * @param argumentMultimap The argumentMultimap to check.
     * @param prefixes         The prefixes to check for presence.
     * @return True if all specified prefixes are present, false otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
        Prefix... prefixes) {
        return Stream.of(prefixes)
            .allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

