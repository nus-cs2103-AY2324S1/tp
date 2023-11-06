package seedu.address.model.person.lead;

/**
 * Represents an unknown lead.
 * An unknown lead has a default follow-up period.
 */
public class UnknownLead extends Lead {

    // Number of weeks before next follow-up
    public static final int FOLLOW_UP_PERIOD = 4;
    private static final UnknownLead instance = new UnknownLead();

    private UnknownLead() {
        super("unknown");
    }

    public static UnknownLead getInstance() {
        return instance;
    }

    @Override
    public int getFollowUpPeriod() {
        return FOLLOW_UP_PERIOD;
    }

    @Override
    public String toString() {
        return "unknown";
    }
}
