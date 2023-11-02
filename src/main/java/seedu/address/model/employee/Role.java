package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.employee.exceptions.InvalidRoleException;

/**
 * Represents the role of an employee, which can be either a manager or a subordinate.
 * The role is stored as an enum and validated using regular expressions.
 */
public class Role {

    enum EmployeeRole {
        MANAGER("manager"),
        SUBORDINATE("subordinate");

        private final String displayName;

        EmployeeRole(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "Employee's role should either be 'manager' for Manager or"
            + " 'subordinate' for Subordinate, and it should not be blank";

    /*
     * Employee's role should be 'manager' for manager role and 'subordinate' for subordinate role
     */
    public static final String VALIDATION_REGEX = "manager|subordinate";

    public final EmployeeRole role;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        switch (role) {
        case "manager":
            this.role = EmployeeRole.MANAGER;
            break;
        case "subordinate":
            this.role = EmployeeRole.SUBORDINATE;
            break;
        default:
            throw new InvalidRoleException();
        }
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if this employee holds the role of a manager.
     *
     * @return {@code true} if this employee is a manager, {@code false} otherwise.
     */
    public boolean isManager() {
        return role == EmployeeRole.MANAGER;
    }

    @Override
    public String toString() {
        return role.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return role.equals(otherRole.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

}
