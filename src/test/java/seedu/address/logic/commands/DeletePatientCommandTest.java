package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patients.Patient;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePatientCommand}.
 */
public class DeletePatientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_nullModel_throwsNullPointerException() {
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(1L);
        assertThrows(NullPointerException.class, () -> deletePatientCommand.execute(null));
    }

    @Test
    void execute_validId_success() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(patient.getId());

        assertDoesNotThrow(() -> deletePatientCommand.execute(model));
    }

    @Test
    void execute_invalidId_throwsCommandException() {
        Model model = new ModelManager();
        long invalidId = 99999L;
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(invalidId);
        assertThrows(CommandException.class, () -> deletePatientCommand.execute(model));
    }

    @Test
    void execute_negativeId_throwsCommandException() {
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(-1L);
        assertThrows(CommandException.class, () -> deletePatientCommand.execute(new ModelManager()));
    }

    @Test
    void execute_zeroId_throwsCommandException() {
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(0L);
        assertThrows(CommandException.class, () -> deletePatientCommand.execute(new ModelManager()));
    }

    @Test
    void execute_multipleDeletions_success() throws CommandException {
        Patient patientOne = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        Patient patientTwo = new PatientBuilder()
            .withName("Mike Lim")
            .withPhone("90998766")
            .withBirthdate("10-10-2000")
            .withGender("M")
            .withRemark("No Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("2 Harvey Drive")
            .withEmail("mike@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patientOne);
        model.addPatient(patientTwo);

        DeletePatientCommand deleteFirstPatient = new DeletePatientCommand(patientOne.getId());
        deleteFirstPatient.execute(model);

        assertNull(model.getPatientById(patientOne.getId()));
        assertNotNull(model.getPatientById(patientTwo.getId()));
    }

    @Test
    void equals() {
        long firstId = 1L;
        long secondId = 2L;
        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand(firstId);
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand(secondId);

        assertEquals(deleteFirstCommand, deleteFirstCommand);

        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand(firstId);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        assertNotEquals(deleteFirstCommand, 1);

        assertNotEquals(deleteFirstCommand, null);

        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

}
