package seedu.staffsnap.model.applicant;

public enum Status {
    UNDECIDED, OFFERED, REJECTED;

    /**
     * Finds the correct descriptor by the string given
     * @param name name of the descriptor
     * @return the descriptor if valid, null otherwise
     */
    public static Status findByName(String name) {
        Status result = null;
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(name) || status.name().substring(0,1).equalsIgnoreCase(name)) {
                result = status;
                break;
            }
        }
        return result;
    }
}
