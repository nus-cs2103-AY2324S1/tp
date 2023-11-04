package seedu.address.model.person.lead;

/**
 * Represents a hot lead.
 * A hot lead has the fastest follow-up period.
 */
public class HotLead extends Lead {

    // Number of weeks before next follow-up
    private static final int FOLLOW_UP_PERIOD = 1;
    private static final HotLead instance = new HotLead();

    private HotLead() {
        super("hot");
    }

    /**
     * Returns a {@code HotLead}.
     * HotLeads only have a single instance.
     *
     * @return the {@code HotLead} instance
     */
    public static HotLead getInstance() {
        return instance;
    }

    @Override
    public int getFollowUpPeriod() {
        return FOLLOW_UP_PERIOD;
    }

    @Override
    public boolean isHot() {
        return true;
    }

    @Override
    public String toString() {
        return "hot";
    }

}
