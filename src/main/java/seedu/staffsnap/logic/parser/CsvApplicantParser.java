package seedu.staffsnap.logic.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;

import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.CsvApplicant;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class CsvApplicantParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     * @throws IOException if the file is not found
     */
    public static List<Applicant> parse(String fileName) throws ParseException, IOException {
        List<CsvApplicant> csvApplicants;
        List<Applicant> applicantsToImport = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).withSkipLines(1).build()) {
            csvApplicants = new CsvToBeanBuilder<CsvApplicant>(reader)
                    .withType(CsvApplicant.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new IOException();
        }

        for (CsvApplicant csvApplicant : csvApplicants) {
            applicantsToImport.add(ParserUtil.parseApplicantFromCsv(csvApplicant));
        }

        return applicantsToImport;
    }
}
