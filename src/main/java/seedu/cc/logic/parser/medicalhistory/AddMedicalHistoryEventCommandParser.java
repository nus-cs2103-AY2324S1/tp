package seedu.cc.logic.parser.medicalhistory;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_TREATMENT;

import java.util.stream.Stream;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.medhisteventcommands.AddMedicalHistoryEventCommand;
import seedu.cc.logic.parser.ArgumentMultimap;
import seedu.cc.logic.parser.ArgumentTokenizer;
import seedu.cc.logic.parser.Parser;
import seedu.cc.logic.parser.ParserUtil;
import seedu.cc.logic.parser.Prefix;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.model.medicalhistory.MedicalCondition;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.medicalhistory.Treatment;
import seedu.cc.model.util.Date;

/**
 * Parses input arguments and creates a new AddMedicalHistoryEventCommand object.
 */
public class AddMedicalHistoryEventCommandParser implements Parser<AddMedicalHistoryEventCommand> {

    /**
     * Returns true if all of the prefixes contain non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddMedicalHistoryEventCommand
     * and returns an AddMedicalHistoryEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMedicalHistoryEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEDICAL_CONDITION, PREFIX_TREATMENT, PREFIX_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMedicalHistoryEventCommand.MESSAGE_USAGE), pe);
        }


        if (!arePrefixesPresent(argMultimap, PREFIX_MEDICAL_CONDITION, PREFIX_TREATMENT, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMedicalHistoryEventCommand.MESSAGE_USAGE));
        }

        if (ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()).isFutureDate()) {
            throw new ParseException(String.format(AddMedicalHistoryEventCommand.MESSAGE_DATE_IN_FUTURE));
        }

        MedicalCondition medicalCondition = ParserUtil.parseMedicalCondition(argMultimap
                .getValue(PREFIX_MEDICAL_CONDITION).get());
        Treatment treatment = ParserUtil.parseTreatment(argMultimap.getValue(PREFIX_TREATMENT).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MEDICAL_CONDITION, PREFIX_TREATMENT, PREFIX_DATE);
        MedicalHistoryEvent event = new MedicalHistoryEvent(medicalCondition, treatment, date);

        return new AddMedicalHistoryEventCommand(event, index);
    }
}
