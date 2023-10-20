package seedu.staffsnap.model.applicant;

/**
 * Status enum for valid statuses
 */
public enum Status {
    UNDECIDED, OFFERED, REJECTED;

    public static final String MESSAGE_CONSTRAINTS = "Status can take the following values: "
            + "[u(ndecided)/o(ffered)/r(ejected)]";

    /**
     * Finds the correct descriptor by the string given
     * @param name name of the descriptor
     * @return the descriptor if valid, null otherwise
     */
    public static Status findByName(String name) {
        Status result = null;
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(name) || status.name().substring(0, 1).equalsIgnoreCase(name)) {
                result = status;
                break;
            }
        }
        System.out.println(result);
        return result;
    }
}
