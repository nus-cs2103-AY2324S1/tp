package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.department.Department;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Employee objects.
 */
public class DepartmentBuilder {

    public static final String DEFAULT_NAME = "Janitor";
    public static final Department DEPARTMENT_LOGISTICS = new DepartmentBuilder()
            .withName(VALID_DEPARTMENT_LOGISTIC).build();

    public static final Department DEPARTMENT_INVESTMENT = new DepartmentBuilder()
            .withName(VALID_DEPARTMENT_INVESTMENT).build();

    private DepartmentName name;
    private Set<EmployeeName> employees;
    /**
     * Creates a {@code DepartmentBuilder} with the default details.
     */
    public DepartmentBuilder() {
        name = new DepartmentName(DEFAULT_NAME);
        employees = new HashSet<>();
    }

    /**
     * Initializes the DepartmentBuilder with the data of {@code departmentToCopy}.
     */
    public DepartmentBuilder(Department departmentToCopy) {
        name = new DepartmentName(departmentToCopy.getName());
        employees = new HashSet<>(departmentToCopy.getEmployees());
    }

    /**
     * Sets the {@code Name} of the {@code Department} that we are building.
     */
    public DepartmentBuilder withName(String name) {
        this.name = new DepartmentName(name);
        return this;
    }

    /**
     * Parses the {@code employees} into a {@code Set<Name>} and set it to the
     * {@code Department} that we are building.
     */
    public DepartmentBuilder withEmployees(String ... employees) {
        this.employees = SampleDataUtil.getEmployeeNameSet(employees);
        return this;
    }

    public Department build() {
        return new Department(name, employees);
    }

    public static List<Department> getTypicalDepartments() {
        return new ArrayList<>(Arrays.asList(DEPARTMENT_INVESTMENT, DEPARTMENT_LOGISTICS));
    }
}
