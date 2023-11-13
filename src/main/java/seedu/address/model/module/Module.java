package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module in the address book.
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS =
            "Module codes should only contain alphanumeric characters and it should be at least 5 characters";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9]*";
    public static final int VALIDATION_LENGTH = 5;
    private final String moduleCode;

    /**
     * Constructs an {@code Module}.
     * @param moduleCode A valid module code.
     */
    public Module(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModule(moduleCode), MESSAGE_CONSTRAINTS);
        this.moduleCode = moduleCode;
    }

    /**
     * Returns if a given string is a valid module
     * @param test A module code to be tested.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() >= VALIDATION_LENGTH;
    }

    /**
     * Returns true if both modules have the same code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleCode().equals(getModuleCode());
    }

    public String getModuleCode() {
        return moduleCode;
    }

    @Override
    public String toString() {
        return getModuleCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return moduleCode.equals(otherModule.moduleCode);
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }
}
