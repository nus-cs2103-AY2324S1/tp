package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.staffsnap.logic.parser.ParserUtil.arePrefixesPresent;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import seedu.staffsnap.logic.commands.ImportCommand;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.CsvApplicant;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class CsvApplicantParser {

    public static final String MESSAGE_FILE_NOT_FOUND = "File not found: %s";

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public static List<Applicant> parse(String fileName) throws ParseException {
        List<CsvApplicant> csvApplicants;
        List<Applicant> applicantsToImport = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            csvApplicants = new CsvToBeanBuilder<CsvApplicant>(reader)
                    .withSkipLines(1)
                    .withType(CsvApplicant.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new ParseException(String.format(MESSAGE_FILE_NOT_FOUND, fileName));
        }

        for (CsvApplicant csvApplicant : csvApplicants) {
            applicantsToImport.add(ParserUtil.parseApplicantFromCsv(csvApplicant));
        }

        return applicantsToImport;
    }
}
