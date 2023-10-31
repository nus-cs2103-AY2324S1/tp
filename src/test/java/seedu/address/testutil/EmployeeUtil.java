package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Name;

/**
 * A utility class for Employee.
 */
public class EmployeeUtil {

    /**
     * Returns an add command string for adding the {@code employee}.
     */
    public static String getAddCommand(Employee employee) {
        return AddCommand.COMMAND_WORD + " " + getEmployeeDetails(employee);
    }

    /**
     * Returns the part of command string for the given {@code employee}'s details.
     */
    public static String getEmployeeDetails(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + employee.getName().fullName + " ");
        sb.append(PREFIX_PHONE + employee.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + employee.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + employee.getAddress().value + " ");
        sb.append(PREFIX_SALARY + employee.getSalary().value + " ");
        sb.append(PREFIX_LEAVE + employee.getLeave().value + " ");
        sb.append(PREFIX_ROLE + employee.getRole().toString() + " ");
        employee.getSupervisors().stream().forEach(
                x -> sb.append(PREFIX_MANAGER + x.toString() + " ")
        );
        employee.getDepartments().stream().forEach(
            s -> sb.append(PREFIX_DEPARTMENT + s.departmentName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEmployeeDescriptor}'s details.
     */
    public static String getEditEmployeeDescriptorDetails(EditEmployeeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.value).append(" "));
        descriptor.getLeave().ifPresent(leave -> sb.append(PREFIX_LEAVE).append(leave.value).append(" "));
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role).append(" "));
        if (descriptor.getSupervisors().isPresent()) {
            Set<Name> supervisors = descriptor.getSupervisors().get();
            if (supervisors.isEmpty()) {
                sb.append(PREFIX_MANAGER);
            } else {
                supervisors.forEach(s -> sb.append(PREFIX_MANAGER).append(s).append(" "));
            }
        }
        if (descriptor.getDepartments().isPresent()) {
            Set<Department> departments = descriptor.getDepartments().get();
            if (departments.isEmpty()) {
                sb.append(PREFIX_DEPARTMENT);
            } else {
                departments.forEach(s -> sb.append(PREFIX_DEPARTMENT).append(s.departmentName).append(" "));
            }
        }
        return sb.toString();
    }
}
