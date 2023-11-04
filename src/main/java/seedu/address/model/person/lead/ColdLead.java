package seedu.address.model.person.lead;

/**
 * Represents a cold lead.
 * A cold lead has the slowest follow-up period.
 */
public class ColdLead extends Lead {

    // Number of weeks before next follow-up
    private static final int FOLLOW_UP_PERIOD = 8;
    private static final ColdLead instance = new ColdLead();

    private ColdLead() {
        super("cold");
    }

    public static ColdLead getInstance() {
        return instance;
    }

    @Override
    public int getFollowUpPeriod() {
        return FOLLOW_UP_PERIOD;
    }

    @Override
    public boolean isCold() {
        return true;
    }

    @Override
    public String toString() {
        return "cold";
    }
}
