package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.nio.file.Path;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

public class UndoCommandTest {
    //Static commands used for testing

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PATIENT COMMANDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static final String SAMPLE_ADD_COMMAND_1 =
            "add name=John Doe gender=MALE birthdate=2000/10/20 phone=98765432 "
                    + "email=johnd@example.com address=311, Clementi Ave 2, #02-25 illnesses=flu, fever";
    private static final String SAMPLE_ADD_COMMAND_2 =
            "add name=Jane Doe gender=FEMALE birthdate=2003/02/20 phone=98765432 "
                    + "email=janed@example.com address=311, Bedok Ave 3, #04-48 illnesses=fever";
    private static final String SAMPLE_DELETE_COMMAND = "delete 1";
    private static final String SAMPLE_EDIT_COMMAND = "edit 1 phone=91272464";
    private static final String SAMPLE_FIND_COMMAND = "find john";
    private static final String SAMPLE_LIST_COMMAND = "list";

    private static final String SAMPLE_DIAGNOSE_COMMAND = "diagnose 1 illnesses=Covid19, Covid20";

    private static final String SAMPLE_UNDIAGNOSE_COMMAND = "undiagnose 1 illnesses=flu, fever";


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~APPOINTMENT COMMANDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static final String SAMPLE_APPOINTMENTS_COMMAND = "appointments";
    private static final String SAMPLE_CANCEL_COMMAND = "cancel 1";

    private static final String SAMPLE_RESCHEDULE_COMMAND = "reschedule 1 start=2023/05/02 09:00 "
            + "end=2023/05/02 11:00 ";

    private static final String SAMPLE_SCHEDULE_COMMAND = "schedule patient=John Doe "
            + "start=2023/10/20 12:00 end=2023/10/20 13:00 "
            + "description=Follow up on Chest X-Ray "
            + "priority=high";

    private static final String SAMPLE_SCHEDULE_ANCIENT_APPOINTMENT_COMMAND = "schedule patient=John Doe "
            + "start=1800/10/20 12:00 end=1800/10/20 13:00 "
            + "description=Injuries from getting stoned "
            + "priority=medium";

    private static final String SAMPLE_TODAY_COMMAND = "today";

    private static final String SAMPLE_UPCOMING_COMMAND = "today";
    private static final String SAMPLE_FIND_PATIENT_APPOINTMENT_COMMAND = "find-appointment john";

    private static final String SAMPLE_SORT_COMMAND = "sort asc by=time";

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MISC COMMANDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static final String SAMPLE_UNDO_COMMAND = "undo";
    private static final String SAMPLE_REDO_COMMAND = "redo";

    @TempDir
    public Path temporaryFolder;
    private Storage storage;
    private Model model = new ModelManager();
    private Logic logic;
    private Command undoCommand = new UndoCommand();

    /**
     * Initializes a dummy ModelManager instance before each test.
     */
    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~General Testing of UndoCommand~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Test basic undo functionality
     */
    @Test
    public void execute_undoPatientCommand_success() throws CommandException, ParseException {
        String expectedMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;

        logic.execute(SAMPLE_ADD_COMMAND_1);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.undoHistory();

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoAppointmentCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);

        String expectedMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;

        logic.execute(SAMPLE_SCHEDULE_COMMAND);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.undoHistory();

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test undo after running multiple commands
     */
    @Test
    public void execute_undoAfterMultipleCommands_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_ADD_COMMAND_2);
        logic.execute(SAMPLE_SCHEDULE_COMMAND);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.undoHistory();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test multiple undo after running multiple commands
     */
    @Test
    public void execute_multipleUndosAfterMultipleCommands_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_ADD_COMMAND_2);
        logic.execute(SAMPLE_SCHEDULE_COMMAND);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.undoHistory();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);

        expectedModel.undoHistory();
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoAfterNoCommand_throwsCommandException() {
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO_ERROR);
    }

    /**
     * Test multiple undo after running a single command
     */
    @Test
    public void execute_multipleUndosAfterOneCommand_throwsCommandException() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_2);
        logic.execute(SAMPLE_UNDO_COMMAND);

        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO_ERROR);
    }

    @Test
    public void execute_undoAfterRedoCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_UNDO_COMMAND);
        logic.execute(SAMPLE_REDO_COMMAND);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.undoHistory();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Integration Testing of UndoCommand with patient commands~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Checks if a person is in the person list or not.
     *
     * @param model Model to be checked.
     * @param name Name to be checked.
     * @return true if the person list of model contains the name of the person we want to check, false otherwise.
     */
    public boolean containsPerson(Model model, String name) {
        boolean containsSamplePatient = false;

        for (Person patient : model.getAddressBook().getPersonList()) {
            if (patient.getName().toString().equals(name)) {
                containsSamplePatient = true;
            }
        }

        return containsSamplePatient;
    }

    /**
     * Checks if a person with a phone number is in the person list or not.
     *
     * @param model Model to be checked.
     * @param name Name to be checked.
     * @param phoneNumber Phone number belongs to that person.
     * @return true if the person list of model contains the name with correct phone number, false otherwise.
     */
    public boolean containsPersonWithPhoneNumber(Model model, String name, String phoneNumber) {
        for (Person person : model.getAddressBook().getPersonList()) {
            if (person.getName().toString().equals(name)
                    && person.getPhone().toString().equals(phoneNumber)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a person with specified illnesses is in the person list or not.
     *
     * @param model Model to be checked.
     * @param name Name to be checked.
     * @param illnesses Illnesses belong to that person.
     * @return true if the person list of model contains the name with correct illnesses, false otherwise.
     */
    public boolean containsPersonWithIllnesses(Model model, String name, Set<Tag> illnesses) {
        for (Person person : model.getAddressBook().getPersonList()) {
            if (person.getName().toString().equals(name)
                    && person.getTags().equals(illnesses)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Test undo functionality with add command.
     * Add command can be undone
     */
    @Test
    public void execute_undoAddCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        model.undoHistory();

        assertFalse(containsPerson(model, "John Doe"));
    }


    /**
     * Test undo functionality with delete command.
     * Delete command can be undone
     */
    @Test
    public void execute_undoDeleteCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_DELETE_COMMAND);
        model.undoHistory();

        assertTrue(containsPerson(model, "John Doe"));
    }

    /**
     * Test undo functionality with edit command.
     * Edit command can be undone
     */
    @Test
    public void execute_undoEditCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_EDIT_COMMAND);
        model.undoHistory();

        assertTrue(containsPersonWithPhoneNumber(model, "John Doe", "98765432"));
    }

    /**
     * Test undo functionality with find command.
     * Find command cannot be undone
     */
    @Test
    public void execute_undoFindCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.undoHistory();

        logic.execute(SAMPLE_FIND_COMMAND);

        String expectedMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test undo functionality with Patients command.
     * Patients command cannot be undone
     */
    @Test
    public void execute_undoListCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.undoHistory();

        logic.execute(SAMPLE_LIST_COMMAND);

        String expectedMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test undo functionality with Diagnose command.
     * Diagnose command can be undone
     */
    @Test
    public void execute_undoDiagnoseCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        Set<Tag> tags = model.getAddressBook().getPersonList().get(0).getTags();
        logic.execute(SAMPLE_DIAGNOSE_COMMAND);
        model.undoHistory();

        assertTrue(containsPersonWithIllnesses(model, "John Doe", tags));
    }

    /**
     * Test undo functionality with Undiagnose command.
     * Undiagnose command can be undone
     */
    @Test
    public void execute_undoUndiagnoseCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        Set<Tag> tags = model.getAddressBook().getPersonList().get(0).getTags();
        logic.execute(SAMPLE_UNDIAGNOSE_COMMAND);
        model.undoHistory();

        assertTrue(containsPersonWithIllnesses(model, "John Doe", tags));
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Integration Testing of UndoCommand with appointment commands~~~~~~~~~~~~~~~~~~~~~
    /**
     * Test undo functionality with appointments command.
     * Appointments command cannot be undone
     */
    @Test
    public void execute_undoAppointmentsCommand_throwsCommandException() throws CommandException, ParseException {
        logic.execute(SAMPLE_APPOINTMENTS_COMMAND);

        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO_ERROR);
    }

    /**
     * Test undo functionality with cancel command.
     * Cancel command can be undone
     */
    @Test
    public void execute_undoCancelCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_SCHEDULE_COMMAND);
        logic.execute(SAMPLE_CANCEL_COMMAND);

        assertTrue(model.getAddressBook().getAppointmentList().isEmpty());
    }

    /**
     * Test undo functionality with find patient appointment command.
     * Find appointment command cannot be undone
     */
    @Test
    public void execute_undoAppointmentFindCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_SCHEDULE_COMMAND);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.undoHistory();

        logic.execute(SAMPLE_FIND_PATIENT_APPOINTMENT_COMMAND);

        String expectedMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test undo functionality with reschedule command.
     * Reschedule command can be undone
     */
    @Test
    public void execute_undoRescheduleCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_SCHEDULE_COMMAND);
        Appointment appointmentBefore = model.getAddressBook().getAppointmentList().get(0);
        logic.execute(SAMPLE_RESCHEDULE_COMMAND);

        model.undoHistory();

        Appointment appointmentAfter = model.getAddressBook().getAppointmentList().get(0);

        assertEquals(appointmentBefore, appointmentAfter);
    }

    /**
     * Test undo functionality with schedule command.
     * Schedule command can be undone
     */
    @Test
    public void execute_undoScheduleCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_SCHEDULE_COMMAND);

        model.undoHistory();

        assertTrue(model.getAddressBook().getAppointmentList().isEmpty());
    }

    /**
     * Test undo functionality with today command.
     * Today command cannot be undone
     */
    @Test
    public void execute_undoTodayCommand_throwsCommandException() throws CommandException, ParseException {
        logic.execute(SAMPLE_TODAY_COMMAND);

        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO_ERROR);
    }

    /**
     * Test undo functionality with upcoming command.
     * Upcoming command cannot be undone
     */
    @Test
    public void execute_undoUpcomingCommand_throwsCommandException() throws CommandException, ParseException {
        logic.execute(SAMPLE_UPCOMING_COMMAND);

        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO_ERROR);
    }

    /**
     * Test undo functionality with Sort command.
     * Sort command can be undone
     */
    @Test
    public void execute_undoSortCommand_success() throws CommandException, ParseException {
        logic.execute(SAMPLE_ADD_COMMAND_1);
        logic.execute(SAMPLE_SCHEDULE_COMMAND);
        logic.execute(SAMPLE_SCHEDULE_ANCIENT_APPOINTMENT_COMMAND);
        Model expectedModel = new ModelManager(model.getAddressBook(), model.getUserPrefs());

        logic.execute(SAMPLE_SORT_COMMAND);
        model.undoHistory();

        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }
}
