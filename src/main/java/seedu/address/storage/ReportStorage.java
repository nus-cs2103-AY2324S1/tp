package seedu.address.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.employee.Report;

/**
 * Class with methods to handle report storage
 */
public class ReportStorage {
    public static final String REPORT_FOLDER = "reports";

    private ReportStorage() {}

    /**
     * Saves a report as a txt file in the reports folder
     * @param report Report to be saved
     * @throws CommandException If report is null or if there is an error writing to the file
     */
    public static void saveReport(Report report) throws CommandException {
        //Check if report is valid
        if (report == null) {
            throw new CommandException(Messages.MESSAGE_REPORT_SAVE_ERROR);
        }

        // Generate report as txt file using file writer
        String reportString = report.toString();
        String fileName = LocalDate.now() + "_" + report.employee.getName().fullName + ".txt";

        // Create reports folder if it does not exist
        File reportFolder = new File(REPORT_FOLDER);
        if (!reportFolder.exists()) {
            reportFolder.mkdir();
        }

        // Create and write to report file
        File reportFile = new File(REPORT_FOLDER + "/" + fileName);
        try {
            reportFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile));
            writer.write(reportString);
            writer.close();
        } catch (IOException e) {
            throw new CommandException(Messages.MESSAGE_REPORT_SAVE_ERROR);
        }
    }
}
