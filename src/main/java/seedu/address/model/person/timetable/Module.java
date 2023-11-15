package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Module in the application.
 * Contains information about the module code and its timings.
 */
public class Module extends TimeBlock {
    public static final String MESSAGE_CONSTRAINTS = "Module codes should start with 2-3 alphabetic characters, \n"
            + "followed by 3-4 digits, and can optionally end with an extra alphabetic character. \n"
            + "The alphabetic characters are case-insensitive.";

    private static final String VALIDATION_REGEX = "^[a-zA-Z]{2,3}\\d{3,4}[a-zA-Z]?$";
    private final String moduleName;

    /**
     * Constructs a {@code Module} with the specified code and time block.
     *
     * @param name The module code. Should follow the format described in MESSAGE_CONSTRAINTS.
     * @param timeBlockString The time block for the module. Expected format: DAY HHMM HHMM.
     */
    public Module(String name, String timeBlockString) { //name = name, timeBlockString = DAY HHMM HHMM
        super(timeBlockString);
        requireNonNull(name);
        checkArgument(isValidModuleName(name), MESSAGE_CONSTRAINTS);

        this.moduleName = name;
    }

    /**
     * Factory method to create a new Module object from a given unparsed input string.
     * Expected format for the input string: NAME DAY HHMM HHMM.
     *
     * @param unparsedInput The input string containing module details.
     * @return A new Module object.
     * @throws IllegalArgumentException If the given input does not adhere to the expected format.
     */
    public static Module newModule(String unparsedInput) throws IllegalValueException {
        requireNonNull(unparsedInput);

        // Split the unparsed input string by whitespace
        String[] parts = unparsedInput.split("\\s+");

        // Check for valid number of parts
        if (parts.length != 4) {
            String errorMsg = "Invalid module input format. Expected: NAME DAY HHMM HHMM" + MESSAGE_CONSTRAINTS;
            throw new IllegalArgumentException(errorMsg);
        }

        String name = parts[0];
        String day = parts[1];
        String startTime = parts[2];
        String endTime = parts[3];
        String timeBlockString = day + " " + startTime + " " + endTime;

        // Check for valid module name format
        if (!isValidModuleName(name)) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }

        return new Module(name, timeBlockString);
    }

    /**
     * Creates a new Module object with the given name while retaining the timings from the original module.
     *
     * @param newName The new name for the module.
     * @return A new Module object with the updated name.
     * @throws IllegalArgumentException If the given new name does not adhere to the module naming constraints.
     */
    public Module editName(String newName) {
        requireNonNull(newName);
        checkArgument(isValidModuleName(newName), MESSAGE_CONSTRAINTS);
        return new Module(newName, super.getTimeBlockString());
    }

    @Override
    public String getName() {
        return moduleName;
    }

    @Override
    public String getType() {
        return "Module";
    }

    /**
     * Checks if the given module name is valid.
     *
     * @param test The module name to check.
     * @return true if the module name is valid, false otherwise.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if the current event is a module.
     *
     * @return True since it is a module.
     */
    @Override
    public boolean isModule() {
        return true;
    }

    @Override
    public String toString() {
        return "Module: [" + moduleName + "] " + super.toString();
    }

    @Override
    public boolean equals(Object e) {
        if (e == this) {
            return true;
        } else if (!(e instanceof Module)) {
            return false;
        } else {
            Module other = (Module) e;
            return this.moduleName.equals(other.moduleName)
                    && this.getTimeBlockString().equals(other.getTimeBlockString());
        }
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
