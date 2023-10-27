package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.history.UserHistoryManager;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class UndoCommandTest {
    //Static commands used for testing

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PATIENT COMMANDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static final String SAMPLE_ADD_COMMAND_1 =
            "add name=John Doe gender=MALE birthdate=2000/10/20 phone=98765432 "
                    + "email=johnd@example.com address=311, Clementi Ave 2, #02-25 illnesses=flu";
    private static final String SAMPLE_ADD_COMMAND_2 =
            "add name=Jane Doe gender=FEMALE birthdate=2003/02/20 phone=98765432 "
                    + "email=janed@example.com address=311, Bedok Ave 3, #04-48 illnesses=fever";
    private static final String SAMPLE_DELETE_COMMAND = "delete 1";
    private static final String SAMPLE_EDIT_COMMAND = "edit 1 phone=91272464";
    private static final String SAMPLE_FIND_COMMAND = "find john";
    private static final String SAMPLE_LIST_COMMAND = "list";

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~APPOINTMENT COMMANDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static final String SAMPLE_APPOINTMENTS_COMMAND =
            "appointments";
    private static final String SAMPLE_CANCEL_COMMAND =
            "cancel 1";

    private static final String SAMPLE_RESCHEDULE_COMMAND =
            "reschedule 1 start=2023/05/02 09:00 end=2023/05/02 11:00 ";

    private static final String SAMPLE_SCHEDULE_COMMAND =
            "schedule patient=John Doe start=2023/10/20 12:00 end=2023/10/20 13:00 "
                    + "description=Follow up on Chest X-Ray ";

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MISC COMMANDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static final String SAMPLE_HELP_COMMAND = "help";
    private static final String SAMPLE_UNDO_COMMAND = "undo";
    private static final String SAMPLE_REDO_COMMAND = "redo";

    @TempDir
    public Path testFolder;
    private StorageManager storageManager;
    private Model model = new ModelManager();
    private Logic logic;
    private UserHistoryManager historyManager;


    /**
     * Initializes a dummy ModelManager instance before each test.
     */
    @BeforeEach
    public void setUpDummyModelManager() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storageManager);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~General Testing of UndoCommand~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Test basic undo functionality
     */
    @Test
    public void testUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        int sizeBefore = model.getAddressBook().getPersonList().size();
        model.undoHistory();
        int sizeAfter = model.getAddressBook().getPersonList().size();

        //asserts that the size of patient list before undo is not equal to the size after undo is executed.
        assertNotEquals(sizeBefore, sizeAfter);
    }

    /**
     * Test undo after running multiple commands
     */
    @Test
    public void testConsecutiveCommandFollowedByUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        CommandResult commandResult2 = logic.execute(SAMPLE_ADD_COMMAND_2);
        int sizeBefore = model.getAddressBook().getPersonList().size();
        CommandResult undoCommand1 = new UndoCommand().execute(model);
        int sizeAfter = model.getAddressBook().getPersonList().size();

        assertEquals(sizeBefore, sizeAfter + 1);
    }

    /**
     * Test multiple undo after running multiple commands
     */
    @Test
    public void testConsecutiveCommandFollowedByConsecutiveUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        CommandResult commandResult2 = logic.execute(SAMPLE_ADD_COMMAND_2);
        int sizeBefore = model.getAddressBook().getPersonList().size();
        CommandResult undoCommand1 = new UndoCommand().execute(model);
        CommandResult undoCommand2 = new UndoCommand().execute(model);
        int sizeAfter = model.getAddressBook().getPersonList().size();

        assertEquals(sizeBefore, sizeAfter + 2);
    }

    /**
     * Test multiple undo after running a single command
     */
    @Test
    public void testSingleCommandFollowedByConsecutiveUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        try {
            CommandResult undoCommand1 = new UndoCommand().execute(model);
            CommandResult undoCommand2 = new UndoCommand().execute(model);
        } catch (CommandException e) {
            assertEquals(model.getUserHistoryManager().getUndoHistorySize(), 1);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Integration Testing of UndoCommand with patient commands~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Test undo functionality with add command.
     * Add command is compatible with undo
     */
    @Test
    public void testAddCommandFollowedByUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        model.undoHistory();

        boolean containsSamplePatient = false;

        for (Person patient : model.getAddressBook().getPersonList()) {
            if (patient.getName().toString().equals("John Doe")) {
                containsSamplePatient = true;
            }
        }

        assertFalse(containsSamplePatient);
    }


    /**
     * Test undo functionality with delete command.
     * Delete command is compatible with undo
     */
    @Test
    public void testDeleteCommandFollowedByUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        CommandResult commandResult2 = logic.execute(SAMPLE_DELETE_COMMAND);
        model.undoHistory();

        boolean containsSamplePatient = false;

        for (Person patient : model.getAddressBook().getPersonList()) {
            if (patient.getName().toString().equals("John Doe")) {
                containsSamplePatient = true;
            }
        }

        assertTrue(containsSamplePatient);
    }

    /**
     * Test undo functionality with edit command.
     * Edit command is compatible with undo
     */
    @Test
    public void testEditCommandFollowedByUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        CommandResult commandResult2 = logic.execute(SAMPLE_EDIT_COMMAND);
        model.undoHistory();

        boolean isPhoneEdited = true;

        for (Person person : model.getAddressBook().getPersonList()) {
            if (person.getName().toString().equals("John Doe")
                    && person.getPhone().toString().equals("98765432")) {
                isPhoneEdited = false;
            }
        }

        assertFalse(isPhoneEdited);
    }

    /**
     * Test undo functionality with find command.
     * Find command is not compatible with undo
     */
    @Test
    public void testFindCommandFollowedByUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        CommandResult commandResult2 = logic.execute(SAMPLE_FIND_COMMAND);

        int sizeBeforeUndo = model.getFilteredPersonList().size();
        model.undoHistory();
        int sizeAfterUndo = model.getFilteredPersonList().size();

        //add command is undone instead of find command
        assertEquals(sizeBeforeUndo, sizeAfterUndo + 1);
    }

    /**
     * Test undo functionality with list command.
     * List command is not compatible with undo
     */
    @Test
    public void testListCommandFollowedByUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        CommandResult commandResult2 = logic.execute(SAMPLE_LIST_COMMAND);

        int sizeBeforeUndo = model.getFilteredPersonList().size();
        model.undoHistory();
        int sizeAfterUndo = model.getFilteredPersonList().size();

        //add command is undone instead of list command
        assertEquals(sizeBeforeUndo, sizeAfterUndo + 1);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Integration Testing of UndoCommand with appointment commands~~~~~~~~~~~~~~~~~~~~~
    /**
     * Test undo functionality with appointments command.
     * Appointments command is not compatible with undo
     */
    @Test
    public void testAppointmentsCommandFollowedByUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_APPOINTMENTS_COMMAND);

        try {
            CommandResult undoCommand = new UndoCommand().execute(model);
        } catch (CommandException e) {
            assertEquals(model.getUserHistoryManager().getUndoHistorySize(), 1);
        }
    }

    /**
     * Test undo functionality with cancel command.
     * Cancel command is compatible with undo
     */
    @Test
    public void testCancelCommandFollowedByUndo() throws CommandException, ParseException {
        CommandResult commandResult1 = logic.execute(SAMPLE_ADD_COMMAND_1);
        CommandResult commandResult2 = logic.execute(SAMPLE_SCHEDULE_COMMAND);
        CommandResult commandResult3 = logic.execute(SAMPLE_CANCEL_COMMAND);

        model.undoHistory();

        assertEquals(1, model.getAddressBook().getAppointmentList().size());
    }
}
