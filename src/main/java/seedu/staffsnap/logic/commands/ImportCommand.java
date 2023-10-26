package seedu.staffsnap.logic.commands;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Applicant;


/**
 * Imports applicants from a CSV file and adds them to the applicant book.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports applicants from a CSV file.\n"
            + "Parameters: fileName\n"
            + "Example: " + COMMAND_WORD + " data.csv";

    public static final String MESSAGE_SUCCESS = "Imported %d applicants from %s";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found: %s";

    private final String fileName;

    public ImportCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Applicant> applicants;

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            applicants = new CsvToBeanBuilder<Applicant>(reader)
                    .withType(Applicant.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FILE_NOT_FOUND, fileName));
        }

        int count = applicants.size();

        applicants.forEach(model::addApplicant);

        return new CommandResult(String.format(MESSAGE_SUCCESS, count, fileName));
    }
}
