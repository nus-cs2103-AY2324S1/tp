package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.record.Condition;
import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;
import seedu.address.model.shared.DateTime;

/**
 * Parses a user input and creates a AddRecordCommand object
 */
public class AddRecordCommandParser implements Parser<AddRecordCommand> {
    @Override
    public AddRecordCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_DATE, PREFIX_CONDITION, PREFIX_MEDICATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_CONDITION, PREFIX_MEDICATION)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecordCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE);

        DateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE).get());
        List<Condition> conditions = ParserUtil.parseConditions(argMultimap.getAllValues(PREFIX_CONDITION));
        List<Medication> medications = ParserUtil.parseMedications(argMultimap.getAllValues(PREFIX_MEDICATION));

        Record record = new Record(dateTime, conditions, medications, null, index.getZeroBased());
        return new AddRecordCommand(index, record);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
