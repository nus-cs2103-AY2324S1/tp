package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.department.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.LeaveList;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.OvertimeHours;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Position;
import seedu.address.model.employee.Salary;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.RemarkList;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Employee objects.
 */
public class EmployeeBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_POSITION = "software engineer";
    public static final String DEFAULT_ID = "EID0102-0304";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_SALARY = "12000";
    public static final int DEFAULT_OVERTIME_HOURS = 0;

    private Name name;
    private Position position;
    private Id id;
    private Phone phone;
    private Email email;
    private Salary salary;
    private Set<Department> departments;
    private OvertimeHours overtimeHours;
    private LeaveList leaveList;
    private RemarkList remarkList;

    /**
     * Creates a {@code EmployeeBuilder} with the default details.
     */
    public EmployeeBuilder() {
        name = new Name(DEFAULT_NAME);
        position = new Position(DEFAULT_POSITION);
        id = new Id(DEFAULT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        salary = new Salary(DEFAULT_SALARY);
        departments = new HashSet<>();
        overtimeHours = new OvertimeHours(DEFAULT_OVERTIME_HOURS);
        leaveList = new LeaveList();
        remarkList = new RemarkList();
    }

    /**
     * Initializes the EmployeeBuilder with the data of {@code employeeToCopy}.
     */
    public EmployeeBuilder(Employee employeeToCopy) {
        name = employeeToCopy.getName();
        position = employeeToCopy.getPosition();
        id = employeeToCopy.getId();
        phone = employeeToCopy.getPhone();
        email = employeeToCopy.getEmail();
        salary = employeeToCopy.getSalary();
        departments = new HashSet<>(employeeToCopy.getDepartments());
        overtimeHours = employeeToCopy.getOvertimeHours();
        leaveList = employeeToCopy.getLeaveList();
        remarkList = employeeToCopy.getRemarkList();
    }

    /**
     * Sets the {@code Name} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code departments} into a {@code Set<Department>}
     * and set it to the {@code Employee} that we are building.
     */
    public EmployeeBuilder withDepartments(String ... departments) {
        this.departments = SampleDataUtil.getDepartmentSet(departments);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code employee} that we are building.
     */
    public EmployeeBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }


    /**
     * Sets the {@code OvertimeHours} of the {@code employee} that we are building.
     */
    public EmployeeBuilder withOvertimeHours(int hours) {
        this.overtimeHours = new OvertimeHours(hours);
        return this;
    }

    /**
     * Sets the {@code LeaveList} of the {@code employee} that we are building.
     */
    public EmployeeBuilder withLeaveList(ArrayList<Leave> leaves) {
        this.leaveList = new LeaveList(leaves);
        return this;
    }

    /**
     * Sets the {@code RemarkList} of the {@code employee} that we are building.
     */
    public EmployeeBuilder withRemarkList(ArrayList<Remark> remarks) {
        this.remarkList = new RemarkList(remarks);
        return this;
    }

    /**
     * Builds the {@code employee}.
     */
    public Employee build() {
        return new Employee(name, position, id, phone, email, salary, departments, overtimeHours,
                leaveList, remarkList);
    }
}
