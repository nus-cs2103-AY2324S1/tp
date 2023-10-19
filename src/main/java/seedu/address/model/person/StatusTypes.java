package seedu.address.model.person;
/**
 * Represents a StatusType in the Status class.
 */
public enum StatusTypes {
    PRELIMINARY("Preliminary"),
    INTERVIEWED("Interviewed"),
    REJECTED("Rejected"),
    OFFERED("Offered");

    private final String statusName;

    StatusTypes(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return this.statusName;
    }

    public static boolean isValidStatusType(String input){
        return input.equals("offered") || input.equals("preliminary")
                || input.equals("rejected")
                || input.equals("offered");
    }

}
