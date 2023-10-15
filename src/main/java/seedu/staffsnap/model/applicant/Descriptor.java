package seedu.staffsnap.model.applicant;

/**
 * Enum for valid Descriptors
 */
public enum Descriptor {
    NAME, PHONE;

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptors should be one of the following:\n"
            + "Name, Phone";

    /**
     * Finds the correct descriptor by the string given
     * @param name name of the descriptor
     * @return the descriptor if valid, null otherwise
     */
    public static Descriptor findByName(String name) {
        Descriptor result = null;
        for (Descriptor descriptor : values()) {
            if (descriptor.name().equalsIgnoreCase(name)) {
                result = descriptor;
                break;
            }
        }
        return result;
    }
}
