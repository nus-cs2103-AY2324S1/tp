package seedu.address.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.employee.Report;
import seedu.address.logic.Messages;

import java.time.LocalDate;

import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.storage.ReportStorage.REPORT_FOLDER;



public class ReportStorageTest {

    @Test
    public void saveReport_ValidReport_ReportFileCreated() {
        Report report = new Report(
                ALICE,
                ALICE.getOvertimeHours().value,
                ALICE.getOvertimePay(),
                ALICE.getNumOfLeaves(),
                ALICE.getRemarkList()
        );

        try {
            ReportStorage.saveReport(report);

            // Check if report file exists
            File reportFile = new File(REPORT_FOLDER + "/" + LocalDate.now() + "_" + ALICE.getName().fullName + ".txt");
            assertTrue(reportFile.exists());
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void saveReport_nullReport_throwsCommandException() {
        assertThrows(CommandException.class, () -> ReportStorage.saveReport(null));
    }

    @AfterEach
    public void deleteReportFile() {
        File reportFolder = new File(REPORT_FOLDER);
        File[] reportFiles = reportFolder.listFiles();
        for (File reportFile : reportFiles) {
            reportFile.delete();
        }
        reportFolder.delete();
    }
}
