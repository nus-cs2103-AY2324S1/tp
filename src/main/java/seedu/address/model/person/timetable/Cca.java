package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Co-Curricular Activity (CCA) in the address book.
 * Guarantees: immutability.
 */
public class Cca extends TimeBlock {
    public static final String MESSAGE_CONSTRAINTS =
            "Cca name should only contain alphanumeric characters and spaces, and it should not be blank";
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private final String ccaName;

    /**
     * Constructs a CCA object.
     *
     * @param name Name of the CCA.
     * @param timeBlockString String representation of the time block.
     */
    public Cca(String name, String timeBlockString) {
        super(timeBlockString);

        requireNonNull(name);
        checkArgument(isValidCcaName(name), MESSAGE_CONSTRAINTS);

        this.ccaName = name;
    }

    /**
     * Factory method to create a new CCA object.
     *
     * @param unparsedInput The input string containing CCA details.
     * @return A new CCA object.
     */
    public static Cca newCca(String unparsedInput) throws IllegalValueException {
        requireNonNull(unparsedInput);

        // Split the unparsed input string based on the last three whitespace occurrences
        String[] parts = unparsedInput.split("\\s+");

        // Check for valid number of parts (at least 4 parts are required)
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid CCA input format. Expected: CCA_NAME DAY HHMM HHMM");
        }

        // Extracting the last three parts for day, startTime, and endTime
        String day = parts[parts.length - 3];
        String startTime = parts[parts.length - 2];
        String endTime = parts[parts.length - 1];

        // Constructing the CCA name by joining the earlier parts
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < parts.length - 3; i++) {
            nameBuilder.append(parts[i]);
            if (i < parts.length - 4) {
                nameBuilder.append(" "); // Add space between words in the CCA name
            }
        }
        String name = nameBuilder.toString();
        String timeBlockString = day + " " + startTime + " " + endTime;

        // Check for valid CCA name format
        if (!isValidCcaName(name)) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }

        // Further validations can be added (e.g., for valid day, startTime, endTime) if necessary

        return new Cca(name, timeBlockString);
    }

    /**
     * Checks if the current TimeBlock object is a CCA.
     *
     * @return Always true for Cca objects.
     */
    @Override
    public boolean isCca() {
        return true;
    }

    /**
     * Validates if the given CCA name is in the correct format.
     *
     * @param test The CCA name to be tested.
     * @return True if the name is valid, false otherwise.
     */
    public static boolean isValidCcaName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String getName() {
        return this.ccaName;
    }

    @Override
    public String getType() {
        return "CCA";
    }

    @Override
    public String toString() {
        return "Cca: [" + ccaName + "] " + super.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Cca)) {
            return false;
        }

        Cca otherCca = (Cca) other;
        return otherCca.getName().equals(getName())
                && otherCca.getTimeBlockString().equals(getTimeBlockString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
