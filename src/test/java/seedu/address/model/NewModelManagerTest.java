package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalDoctor.ALICE;
import static seedu.address.testutil.TypicalPatient.CARL;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.exceptions.ItemNotFoundException;

public class NewModelManagerTest {
    private NewModelManager modelManager = new NewModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Database(), new Database(modelManager.getDatabase()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setDatabaseFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setDatabaseFilePath(path);
        assertEquals(path, modelManager.getDatabaseFilePath());
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAppointment(null));
    }

    @Test
    public void hasDoctor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDoctor(null));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPatient(null));
    }

    @Test
    public void hasAppointment_appointmentNotInDatabase_returnsFalse() {
        assertFalse(modelManager.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorNotInDatabase_returnsFalse() {
        assertFalse(modelManager.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientNotInDatabase_returnsFalse() {
        assertFalse(modelManager.hasPatient(CARL));
    }

    @Test
    public void hasAppointment_appointmentInDatabase_returnsTrue() {
        modelManager.addAppointment(APPOINTMENT_1);
        assertTrue(modelManager.hasAppointment(APPOINTMENT_1));
    }

    @Test
    public void hasDoctor_doctorInDatabase_returnsTrue() {
        modelManager.addDoctor(ALICE);
        assertTrue(modelManager.hasDoctor(ALICE));
    }

    @Test
    public void hasPatient_patientInDatabase_returnsTrue() {
        modelManager.addPatient(CARL);
        assertTrue(modelManager.hasPatient(CARL));
    }

    @Test
    public void deleteAppointment_emptyDatabase_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.deleteAppointment(APPOINTMENT_1));
    }

    @Test
    public void deleteDoctor_emptyDatabase_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.deleteDoctor(ALICE));
    }

    @Test
    public void deletePatient_emptyDatabase_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.deletePatient(CARL));
    }

    @Test
    public void getFilteredAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAppointmentList()
                .remove(0));
    }

    @Test
    public void getFilteredDoctorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredDoctorList()
                .remove(0));
    }

    @Test
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList()
                .remove(0));
    }

    @Test
    public void equals() {
        Database database = new Database();
        Database differentDatabase = new Database();
        differentDatabase.addDoctor(ALICE);
        differentDatabase.addPatient(CARL);
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new NewModelManager(database, userPrefs);
        NewModelManager modelManagerCopy = new NewModelManager(database, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new NewModelManager(differentDatabase, userPrefs)));
    }
}
