package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.department.Department;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.RemarkList;

/**
 * Represents an Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Global employee details
    public static final int MAX_OVERTIME_HOURS = 72;
    public static final int DEFAULT_OVERTIME_HOURS = 0;
    public static final ArrayList<Leave> DEFAULT_LEAVELIST = new ArrayList<>();
    public static final int MAX_NUM_OF_LEAVES = 14;
    public static final ArrayList<Remark> DEFAULT_REMARKLIST = new ArrayList<>();

    // Identity fields
    private final Name name;
    private final Position position;
    private final Id id;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Salary salary;
    private final Set<Department> departments = new HashSet<>();
    private final OvertimeHours overtimeHours;
    private final LeaveList leaveList;
    private final RemarkList remarkList;

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Position position, Id id, Phone phone, Email email, Salary salary,
                    Set<Department> departments) {
        requireAllNonNull(name, position, id, phone, email, salary, departments);
        this.name = name;
        this.position = position;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.departments.addAll(departments);
        this.overtimeHours = new OvertimeHours(DEFAULT_OVERTIME_HOURS);
        this.leaveList = new LeaveList(DEFAULT_LEAVELIST);
        this.remarkList = new RemarkList(DEFAULT_REMARKLIST);
    }

    /**
     * Overloaded constructor for Employee class to set fields with default values.
     */
    public Employee(Name name, Position position, Id id, Phone phone, Email email, Salary salary,
                    Set<Department> departments, OvertimeHours overtimeHours,
                    LeaveList leaveList, RemarkList remarkList) {
        requireAllNonNull(name, position, id, phone, email, salary, departments, overtimeHours, leaveList, remarkList);
        this.name = name;
        this.position = position;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.departments.addAll(departments);
        this.overtimeHours = overtimeHours;
        this.leaveList = leaveList;
        this.remarkList = remarkList;
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Id getId() {
        return id;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Salary getSalary() {
        return salary;
    }

    public OvertimeHours getOvertimeHours() {
        return overtimeHours;
    }

    public RemarkList getRemarkList() {
        return remarkList.getCopiedRemarkList();
    }

    public LeaveList getLeaveList() {
        return leaveList.getCopiedLeaveList();
    }

    /**
     * Returns the current leave status of the employee.
     */
    public boolean isOnLeaveToday() {
        return leaveList.getCurrentLeaveStatus();
    }

    /**
     * Returns the leave status of the employee on the specified date.
     */
    public boolean isOnLeave(LocalDate date) {
        return leaveList.getLeaveStatus(date);
    }

    /**
     * Returns the number of leaves taken by the employee.
     */
    public int getNumOfLeaves() {
        return leaveList.getSize();
    }

    /**
     * Calculates the overtime pay of the employee.
     *
     * @return the overtime pay of the employee rounded to two decimal places.
     */
    public double getOvertimePay() {
        return Math.round(overtimeHours.value * salary.getOvertimeHourlyRate() * 100) / 100d;
    }

    /**
     * Returns an immutable department set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Department> getDepartments() {
        return Collections.unmodifiableSet(departments);
    }

    /**
     * Returns true if both employees have the same employee id.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getId().equals(getId());
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return name.equals(otherEmployee.name)
                && position.equals(otherEmployee.position)
                && id.equals(otherEmployee.id)
                && phone.equals(otherEmployee.phone)
                && email.equals(otherEmployee.email)
                && salary.equals(otherEmployee.salary)
                && departments.equals(otherEmployee.departments)
                && overtimeHours.equals(otherEmployee.overtimeHours)
                && leaveList.equals(otherEmployee.leaveList)
                && remarkList.equals(otherEmployee.remarkList);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, position, id, phone, email, salary, departments, overtimeHours,
                leaveList, remarkList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("position", position)
                .add("id", id)
                .add("phone", phone)
                .add("email", email)
                .add("salary", salary)
                .add("departments", departments)
                .add("overtimeHours", overtimeHours)
                .add("leaves", leaveList)
                .add("remarks", remarkList)
                .toString();
    }
}
