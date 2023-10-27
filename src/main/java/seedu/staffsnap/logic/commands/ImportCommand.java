package seedu.staffsnap.logic.commands;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.logic.parser.CsvApplicantParser;
import seedu.staffsnap.logic.parser.Parser;
import seedu.staffsnap.logic.parser.ParserUtil;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.CsvApplicant;


/**
 * Imports applicants from a CSV file and adds them to the applicant book.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports applicants from a CSV file.\n"
            + "Parameters: fileName\n"
            + "Example: " + COMMAND_WORD + " data.csv";

    public static final String MESSAGE_SUCCESS = "Imported %d applicants from %s";
    public static final String MESSAGE_INVALID_CSV_FORMAT = "The csv file has an invalid format!";


    private final String fileName;

    public ImportCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Applicant> applicantsToImport;
        try {
            applicantsToImport = CsvApplicantParser.parse(fileName);
        } catch (ParseException e) {
            throw new CommandException(MESSAGE_INVALID_CSV_FORMAT);
        }

        for (Applicant applicant : applicantsToImport) {
            model.addApplicant(applicant);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, applicantsToImport.size(), fileName));
    }
}
