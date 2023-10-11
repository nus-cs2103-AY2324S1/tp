package seedu.staffsnap.testutil;

<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EmployeeUtil.java
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_TAG;
=======
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EmployeeUtil.java

import java.util.Set;

import seedu.staffsnap.logic.commands.AddCommand;
import seedu.staffsnap.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.staffsnap.model.employee.Employee;
import seedu.staffsnap.model.tag.Tag;

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
<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EmployeeUtil.java
        sb.append(PREFIX_EMAIL + employee.getEmail().value + " ");
        sb.append(PREFIX_JOB_TITLE + employee.getJobTitle().value + " ");
=======
        sb.append(PREFIX_DEPARTMENT + employee.getDepartment().value + " ");
        sb.append(PREFIX_ADDRESS + employee.getAddress().value + " ");
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EmployeeUtil.java
        employee.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
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
<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EmployeeUtil.java
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getJobTitle().ifPresent(jobTitle -> sb.append(PREFIX_JOB_TITLE).append(jobTitle.value).append(" "));
=======
        descriptor.getDepartment().ifPresent(department -> sb.append(PREFIX_DEPARTMENT).append(department.value)
                .append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EmployeeUtil.java
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
