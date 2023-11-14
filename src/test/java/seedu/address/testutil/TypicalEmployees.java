package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANAGER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ManageHr;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Employee;

/**
 * A utility class containing a list of {@code Employee} objects to be used in tests.
 */
public class TypicalEmployees {

    public static final Employee ALICE = new EmployeeBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withSalary("4444")
            .withLeave("14")
            .withRole("manager")
            .withSupervisors()
            .withDepartments("investment").build();
    public static final Employee BENSON = new EmployeeBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withSalary("2334")
            .withLeave("21")
            .withRole("manager")
            .withSupervisors()
            .withDepartments("logistics", "investment").build();
    public static final Employee CARL = new EmployeeBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withSalary("4212").withLeave("14").withRole("subordinate")
            .withSupervisors("Benson Meier").withAddress("wall street").build();
    public static final Employee DANIEL = new EmployeeBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withSalary("10000").withLeave("35").withAddress("10th street")
            .withRole("manager").withSupervisors("Benson Meier")
            .withDepartments("investment").build();
    public static final Employee ELLE = new EmployeeBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withSalary("0").withLeave("0")
            .withRole("manager").withSupervisors().withAddress("michegan ave").build();
    public static final Employee FIONA = new EmployeeBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withSalary("0").withLeave("0")
            .withRole("subordinate").withSupervisors().withAddress("little tokyo").build();
    public static final Employee GEORGE = new EmployeeBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withSalary("0").withLeave("0")
            .withRole("subordinate").withSupervisors().withAddress("4th street").build();

    // Manually added
    public static final Employee HOON = new EmployeeBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withSalary("0").withLeave("0")
            .withRole("subordinate").withSupervisors().withAddress("little india").build();
    public static final Employee IDA = new EmployeeBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withSalary("0").withLeave("0")
            .withRole("subordinate").withSupervisors().withAddress("chicago ave").build();

    // Manually added - Employee's details found in {@code CommandTestUtil}
    public static final Employee AMY = new EmployeeBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withSalary(VALID_SALARY_AMY)
            .withLeave(VALID_LEAVE_AMY).withRole(VALID_ROLE_AMY).withSupervisors()
            .withDepartments(VALID_DEPARTMENT_INVESTMENT).build();
    public static final Employee BOB = new EmployeeBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withSalary(VALID_SALARY_BOB)
            .withLeave(VALID_LEAVE_BOB).withRole(VALID_ROLE_BOB).withSupervisors(VALID_MANAGER_AMY)
            .withDepartments(VALID_DEPARTMENT_LOGISTIC, VALID_DEPARTMENT_INVESTMENT).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEmployees() {} // prevents instantiation

    /**
     * Returns an {@code ManageHr} with all the typical employees.
     */
    public static ManageHr getTypicalManageHr() {
        ManageHr hr = new ManageHr();
        for (Department department: DepartmentBuilder.getTypicalDepartments()) {
            hr.addDepartment(department);
        }
        for (Employee employee : getTypicalEmployees()) {
            hr.addEmployee(employee);
        }
        return hr;
    }

    public static List<Employee> getTypicalEmployees() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
