package seedu.address.model.person.lead;

/**
 * Represents a warm lead.
 * A warm lead has a default follow-up period.
 */
public class WarmLead extends Lead {

    // Number of weeks before next follow-up
    private static final int FOLLOW_UP_PERIOD = 4;
    private static final WarmLead instance = new WarmLead();

    private WarmLead() {
        super("warm");
    }

    public static WarmLead getInstance() {
        return instance;
    }

    @Override
    public int getFollowUpPeriod() {
        return FOLLOW_UP_PERIOD;
    }

    @Override
    public boolean isWarm() {
        return true;
    }

    @Override
    public String toString() {
        return "warm";
    }
}
