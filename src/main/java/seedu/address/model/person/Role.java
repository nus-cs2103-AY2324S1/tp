package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoleType(String)}
 */
public class Role {

    /**
     * Describes the type of the Role. The user input / output must match these values
     * in the code exactly.
     */
    public enum RoleType {
        Student,
        TA,
        Professor
    }

    public static final String MESSAGE_CONSTRAINTS =
        "A role must take one of the roleTypes: Student, TA, or Professor.";

    /*
     * The Role must be either one of Student, TA or Professor.
     */
    public static final String VALIDATION_REGEX = "^(Student|TA|Professor)$";

    /**
     * Delimiter used when multiple roles are specified in the same command.
     * May be used as a shorthand for PREFIX_ROLE A PREFIX_ROLE B by doing PREFIX_ROLE A PARSE_ROLE_DELIMTIER B.
     */
    public static final String PARSE_ROLE_DELIMITER = ", ";
    public final RoleType roleType;


    /**
     * Constructs a {@code Role}.
     *
     * @param roleString A valid role string.
     */
    public Role(String roleString) {
        requireNonNull(roleString);
        checkArgument(isValidRoleType(roleString), MESSAGE_CONSTRAINTS);

        roleType = RoleType.valueOf(roleString);
    }

    public RoleType getRoleType() {
        return roleType;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRoleType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return roleType.toString();
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
        return roleType.equals(otherRole.roleType);
    }

    @Override
    public int hashCode() {
        return roleType.hashCode();
    }
}
