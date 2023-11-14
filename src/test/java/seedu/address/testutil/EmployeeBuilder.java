package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Employee objects.
 */
public class EmployeeBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_SALARY = "1234";
    public static final String DEFAULT_LEAVE = "14";
    private static final String DEFAULT_ROLE = "manager";
    private EmployeeName name;
    private Phone phone;
    private Email email;
    private Address address;
    private Salary salary;
    private Leave leave;
    private Role role;
    private Set<EmployeeName> supervisors;
    private Set<DepartmentName> departments;

    /**
     * Creates a {@code EmployeeBuilder} with the default details.
     */
    public EmployeeBuilder() {
        name = new EmployeeName(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        salary = new Salary(DEFAULT_SALARY);
        leave = new Leave(DEFAULT_LEAVE);
        role = new Role(DEFAULT_ROLE);
        supervisors = new HashSet<>();
        departments = new HashSet<>();
    }

    /**
     * Initializes the EmployeeBuilder with the data of {@code employeeToCopy}.
     */
    public EmployeeBuilder(Employee employeeToCopy) {
        name = employeeToCopy.getName();
        phone = employeeToCopy.getPhone();
        email = employeeToCopy.getEmail();
        address = employeeToCopy.getAddress();
        salary = employeeToCopy.getSalary();
        leave = employeeToCopy.getLeave();
        role = employeeToCopy.getRole();
        supervisors = new HashSet<>(employeeToCopy.getSupervisors());
        departments = new HashSet<>(employeeToCopy.getDepartments());
    }

    /**
     * Sets the {@code Name} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withName(String name) {
        this.name = new EmployeeName(name);
        return this;
    }

    /**
     * Parses the {@code departments} into a {@code Set<Department>} and set it to the
     * {@code Employee} that we are building.
     */
    public EmployeeBuilder withDepartments(String ... departments) {
        this.departments = SampleDataUtil.getDepartmentNameSet(departments);
        return this;
    }

    /**
     * Parses the {@code supervisorName} into a {@code Set<Name>} and set it to the
     * {@code Employee} that we are building.
     */
    public EmployeeBuilder withSupervisors(String ... supervisorName) {
        this.supervisors = SampleDataUtil.getEmployeeNameSet(supervisorName);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Employee} that we are building.
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
     * Sets the {@code Salary} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /**
     * Sets the {@code leave} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withLeave(String leave) {
        this.leave = new Leave(leave);
        return this;
    }

    /**
     * Sets the {@code role} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    public Employee build() {
        return new Employee(name, phone, email, address, salary, leave, role, supervisors, departments);
    }

}
