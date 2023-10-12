package seedu.address.model.person;

public class Status {
    private StatusTypes statusType;


    public Status() {
        this.statusType = StatusTypes.PRELIMINARY;
    }

    public StatusTypes getStatusType() {
        return this.statusType;
    }

    public void setStatusType(StatusTypes newStatus) {
        this.statusType = newStatus;
    }

    @Override
    public String toString() {
        return statusType.toString();
    }
}
