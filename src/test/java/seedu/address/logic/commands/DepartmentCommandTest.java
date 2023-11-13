package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ManageHr;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyManageHr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;
import seedu.address.testutil.DepartmentBuilder;

public class DepartmentCommandTest {

    private static final DepartmentCommand.Type COMMAND_TYPE_ADD = DepartmentCommand.Type.ADD;
    @Test
    public void constructor_nullDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DepartmentCommand(null, null));
    }

    @Test
    public void execute_employeeAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingDepartmentsAdded modelStub = new ModelStubAcceptingDepartmentsAdded();
        Department validDepartment = new DepartmentBuilder().build();

        CommandResult commandResult = new DepartmentCommand(validDepartment, COMMAND_TYPE_ADD).execute(modelStub);

        assertEquals(String.format(DepartmentCommand.MESSAGE_ADDED_SUCCESS, validDepartment.getName()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDepartment), modelStub.departmentsAdded);
    }

    @Test
    public void execute_duplicateDepartment_throwsCommandException() {
        Department validDepartment = new DepartmentBuilder().build();
        DepartmentCommand departmentCommand = new DepartmentCommand(validDepartment, COMMAND_TYPE_ADD);
        ModelStub modelStub = new ModelStubWithDepartment(validDepartment);

        assertThrows(CommandException.class,
                DepartmentCommand.MESSAGE_DUPLICATE_DEPARTMENT, () -> departmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Department investment = new DepartmentBuilder().withName(VALID_DEPARTMENT_INVESTMENT).build();
        Department logistic = new DepartmentBuilder().withName(VALID_DEPARTMENT_LOGISTIC).build();
        DepartmentCommand addInvestmentCommand = new DepartmentCommand(investment, COMMAND_TYPE_ADD);
        DepartmentCommand addLogisticCommand = new DepartmentCommand(logistic, COMMAND_TYPE_ADD);

        // same object -> returns true
        assertTrue(addInvestmentCommand.equals(addInvestmentCommand));

        // same values -> returns true
        DepartmentCommand addInvestmentCommandCopy = new DepartmentCommand(investment, COMMAND_TYPE_ADD);
        assertTrue(addInvestmentCommand.equals(addInvestmentCommandCopy));

        // different types -> returns false
        assertFalse(addInvestmentCommand.equals(1));

        // null -> returns false
        assertFalse(addInvestmentCommand.equals(null));

        // different employee -> returns false
        assertFalse(addInvestmentCommand.equals(addLogisticCommand));
    }

    @Test
    public void toStringMethod() {
        DepartmentCommand addCommand = new DepartmentCommand(
                new DepartmentBuilder().withName(VALID_DEPARTMENT_INVESTMENT).build(), COMMAND_TYPE_ADD);
        String expected = DepartmentCommand.class.getCanonicalName()
                + "{department=[" + VALID_DEPARTMENT_INVESTMENT + "]}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getManageHrFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setManageHrFilePath(Path manageHrFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEmployee(Employee employee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDepartment(Department department) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setManageHr(ReadOnlyManageHr newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyManageHr getManageHr() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmployee(Employee employee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmployeeWithName(EmployeeName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEmployee(Employee target) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setEmployee(Employee target, Employee editedEmployee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Employee> getFilteredEmployeeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDepartment(Department department) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDepartmentWithName(DepartmentName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDepartment(Department target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single employee.
     */
    private class ModelStubWithDepartment extends ModelStub {
        private final Department department;

        ModelStubWithDepartment(Department department) {
            requireNonNull(department);
            this.department = department;
        }

        @Override
        public boolean hasDepartment(Department department) {
            requireNonNull(department);
            return this.department.isSameDepartment(department);
        }
    }

    /**
     * A Model stub that always accept the employee being added.
     */
    private class ModelStubAcceptingDepartmentsAdded extends ModelStub {
        final ArrayList<Department> departmentsAdded = new ArrayList<>();

        @Override
        public boolean hasDepartment(Department department) {
            requireNonNull(department);
            return departmentsAdded.stream().anyMatch(department::isSameDepartment);
        }

        @Override
        public void addDepartment(Department department) {
            requireNonNull(department);
            departmentsAdded.add(department);
        }

        @Override
        public ReadOnlyManageHr getManageHr() {
            return new ManageHr();
        }
    }
}
