package seedu.address.model.person;

/**
 * Represents a Status in the Person class
 */
public class Status {
    public static final String MESSAGE_CONSTRAINTS = "Status should be either one of the following: 'Preliminary',"
            + "'Interviewed', 'Offered', 'Rejected' and it should not be blank";
    private String value;
    private StatusTypes statusType;


    /**
     * Creates a default Status - Preliminary
     */
    public Status() {
        this.statusType = StatusTypes.PRELIMINARY;
        this.value = "Preliminary";
    }

    /**
     * Creates a Status from String
     * @param value The status type string to used
     */
    public Status(String value) {
        this.value = value;
        switch (value.toLowerCase()) {
        case "offered":
            this.statusType = StatusTypes.OFFERED;
            break;
        case "rejected":
            this.statusType = StatusTypes.REJECTED;
            break;
        case "interviewed":
            this.statusType = StatusTypes.INTERVIEWED;
            break;
        case "preliminary":
            this.statusType = StatusTypes.PRELIMINARY;
            break;
        default:
            break;
        }
    }

    /**
     * Creates a Status from a StatusType
     * @param statusType The StatusType to be used
     */
    public Status(StatusTypes statusType) {
        this.value = statusType.toString();
        this.statusType = statusType;
    }

    public StatusTypes getStatusType() {
        return this.statusType;
    }

    public void setStatusType(StatusTypes newStatus) {
        this.statusType = newStatus;
        this.value = newStatus.toString();
    }


    @Override
    public String toString() {
        return statusType.toString();
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Checks the other object is equals to this one
     *
     * @param other the other Object
     * @return {@code true} if equals, {@code false} otherwise.
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else {
            if (other instanceof Status) {
                return this.statusType == ((Status) other).statusType;
            } else {
                return false;
            }
        }
    }

}
