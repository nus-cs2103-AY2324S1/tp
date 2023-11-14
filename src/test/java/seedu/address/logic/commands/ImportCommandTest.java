package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attachment.Attachment;

public class ImportCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportCommandTest");
    private static final Path VALID_CSV_FILE = TEST_DATA_FOLDER.resolve("valid.csv");
    private static final Path PARTIAL_VALID_CSV_FILE = TEST_DATA_FOLDER.resolve("partial_valid.csv");
    private static final Path INVALID_CSV_FILE = TEST_DATA_FOLDER.resolve("invalid.csv");
    private static final Path MISSING_FIELDS_CSV_FILE = TEST_DATA_FOLDER.resolve("missing_fields.csv");
    private static final Path INVALID_FORMAT_CSV_FILE = TEST_DATA_FOLDER.resolve("invalid_format.csv");
    private static final Path INVALID_PATH_FILE = Paths.get("invalid", "directory", "file.csv");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validCsv_importSuccessful() throws CommandException {
        Attachment validCsv = new Attachment(VALID_CSV_FILE.toString());
        CommandResult commandResult = new ImportCommand(validCsv).execute(model);

        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_SUCCESS, 4);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_partialValidCsv_importPartiallySuccessful() throws CommandException {
        Attachment partialValidCsv = new Attachment(PARTIAL_VALID_CSV_FILE.toString());
        CommandResult commandResult = new ImportCommand(partialValidCsv).execute(model);

        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_SUCCESS, 2) + " "
                + String.format(ImportCommand.MESSAGE_IMPORT_FAILURE, 2, "2, 3");

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidCsv_importFailure() throws CommandException {
        Attachment invalidCsv = new Attachment(INVALID_CSV_FILE.toString());
        CommandResult commandResult = new ImportCommand(invalidCsv).execute(model);

        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_FAILURE, 4, "2, 3, 4, 5");

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidFileFormatCsv_importFailure() throws CommandException {
        Attachment invalidFileFormatCsv = new Attachment(INVALID_FORMAT_CSV_FILE.toString());
        ImportCommand importCommand = new ImportCommand(invalidFileFormatCsv);

        String expectedMessage = ImportCommand.MESSAGE_INVALID_FILE_FORMAT;

        assertCommandFailure(importCommand, model, expectedMessage);
    }

    @Test
    public void execute_missingFieldsCsv_importFailure() throws CommandException {
        Attachment missingFieldsCsv = new Attachment(MISSING_FIELDS_CSV_FILE.toString());
        ImportCommand importCommand = new ImportCommand(missingFieldsCsv);

        String expectedMessage = String.format(ImportCommand.MESSAGE_MISSING_FIELDS, "gpa");

        assertCommandFailure(importCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidPath_importFailure() throws CommandException {
        Attachment invalidPathFile = new Attachment(INVALID_PATH_FILE.toString());
        ImportCommand importCommand = new ImportCommand(invalidPathFile);

        String expectedMessage = ImportCommand.MESSAGE_FAILED_TO_OPEN;

        assertCommandFailure(importCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Attachment firstAttachment = new Attachment(VALID_CSV_FILE.toString());
        Attachment secondAttachment = new Attachment(INVALID_CSV_FILE.toString());

        ImportCommand importFirstCommand = new ImportCommand(firstAttachment);
        ImportCommand importSecondCommand = new ImportCommand(secondAttachment);

        assertTrue(importFirstCommand.equals(importFirstCommand));

        ImportCommand importFirstCommandCopy = new ImportCommand(firstAttachment);
        assertTrue(importFirstCommand.equals(importFirstCommandCopy));

        assertFalse(importFirstCommand.equals(1));

        assertFalse(importFirstCommand.equals(null));

        assertFalse(importFirstCommand.equals(importSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Attachment attachment = new Attachment(VALID_CSV_FILE.toString());
        ImportCommand importCommand = new ImportCommand(attachment);
        String expected = ImportCommand.class.getCanonicalName() + "{attachment=" + attachment + "}";
        assertEquals(expected, importCommand.toString());
    }

}
