package seedu.address.model.person;

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

}
