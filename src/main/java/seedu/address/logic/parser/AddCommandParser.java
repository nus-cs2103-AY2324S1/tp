package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONFLICTING_DRUGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOTAL_STOCK;
import static seedu.address.model.prescription.Prescription.DATES_PREDICATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DOSAGE, PREFIX_FREQUENCY,
                    PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_EXPIRY_DATE, PREFIX_TOTAL_STOCK, PREFIX_NOTE,
                        PREFIX_CONFLICTING_DRUGS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DOSAGE, PREFIX_FREQUENCY,
                PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_EXPIRY_DATE, PREFIX_TOTAL_STOCK, PREFIX_NOTE,
                        PREFIX_CONFLICTING_DRUGS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        Dosage dosage = null;
        if (argMultimap.getValue(PREFIX_DOSAGE).isPresent()) {
            dosage = ParserUtil.parseDosage(argMultimap.getValue(PREFIX_DOSAGE).get());
        }

        Frequency frequency = null;
        if (argMultimap.getValue(PREFIX_FREQUENCY).isPresent()) {
            frequency = ParserUtil.parseFrequency(argMultimap.getValue(PREFIX_FREQUENCY).get());
        }

        Date startDate = new Date(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        }

        Date endDate = null;
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
        }

        Date expiryDate = null;
        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            expiryDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
        }

        Stock totalStock = null;
        if (argMultimap.getValue(PREFIX_TOTAL_STOCK).isPresent()) {
            totalStock = ParserUtil.parseTotalStock(argMultimap.getValue(PREFIX_TOTAL_STOCK).get());
        }

        Note note = null;
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
        }
        Set<Name> conflictingDrugs = ParserUtil.parseDrugs(argMultimap.getAllValues(PREFIX_CONFLICTING_DRUGS));

        Prescription prescription = new Prescription(name, dosage, frequency, startDate, endDate,
                expiryDate, totalStock, note, conflictingDrugs);

        if (!DATES_PREDICATE.test(prescription)) {
            throw new ParseException(AddCommand.MESSAGE_INVALID_DATES);
        }

        return new AddCommand(prescription);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
