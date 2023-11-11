package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.DepartmentBuilder.DEPARTMENT_INVESTMENT;
import static seedu.address.testutil.DepartmentBuilder.DEPARTMENT_LOGISTICS;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalEmployees.ELLE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.name.NameContainsKeywordsPredicate;
import seedu.address.testutil.DepartmentBuilder;
import seedu.address.testutil.ManageHrBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ManageHr(), new ManageHr(modelManager.getManageHr()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setManageHrFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setManageHrFilePath(Paths.get("new/address/book/file/path"));
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
    public void setManageHrFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setManageHrFilePath(null));
    }

    @Test
    public void setManageHrFilePath_validPath_setsManageHrFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setManageHrFilePath(path);
        assertEquals(path, modelManager.getManageHrFilePath());
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEmployee(null));
    }

    @Test
    public void hasEmployee_employeeNotInManageHr_returnsFalse() {
        assertFalse(modelManager.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeInManageHr_returnsTrue() {
        modelManager.addEmployee(ELLE);
        assertTrue(modelManager.hasEmployee(ELLE));
    }

    @Test
    public void getFilteredEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEmployeeList().remove(0));
    }

    @Test
    public void hasDepartment_departmentExists_success() {
        modelManager.addDepartment(DEPARTMENT_LOGISTICS);
        assertTrue(modelManager.hasDepartment(DEPARTMENT_LOGISTICS));
    }

    @Test
    public void hasDepartment_noDepartment_failure() {
        modelManager.addDepartment(DEPARTMENT_LOGISTICS);
        assertFalse(modelManager.hasDepartment(DEPARTMENT_INVESTMENT));
    }

    @Test
    public void hasEmployeeWithName_employeeExists_success() {
        modelManager.addEmployee(ELLE);
        assertTrue(modelManager.hasEmployeeWithName(ELLE.getName()));
    }

    @Test
    public void hasEmployeeWithName_noEmployee_failure() {
        modelManager.addEmployee(ELLE);
        assertFalse(modelManager.hasEmployeeWithName(ALICE.getName()));
    }

    @Test
    public void addDeleteDepartment_hasDept_success() {
        modelManager.addDepartment(DEPARTMENT_LOGISTICS);
        modelManager.deleteDepartment(DEPARTMENT_LOGISTICS);
        assertFalse(modelManager.hasDepartment(DEPARTMENT_LOGISTICS));
    }

    @Test
    public void equals() {
        ManageHr manageHR = new ManageHrBuilder().withDepartment(DEPARTMENT_INVESTMENT)
                .withDepartment(DEPARTMENT_LOGISTICS).withEmployee(ALICE).withEmployee(BENSON).build();
        ManageHr differentManageHr = new ManageHr();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(manageHR, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(manageHR, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentManageHr, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEmployeeList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(manageHR, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setManageHrFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(manageHR, differentUserPrefs)));
    }
}
