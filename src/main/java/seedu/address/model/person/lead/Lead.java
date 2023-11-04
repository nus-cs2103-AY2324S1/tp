package seedu.address.model.person.lead;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents the potential of a client.
 */
public abstract class Lead {

    public static final String MESSAGE_CONSTRAINTS = "Lead should only take values hot|warm|cold|unknown";

    /**
     * Returns a lead given a string. Checks if the string is a valid lead.
     * This should only be called by subclasses.
     *
     * @param lead the string representation for the lead
     */
    public Lead(String lead) throws IllegalArgumentException {
        assert Objects.nonNull(lead) : "Lead should take non-null values";
        checkArgument(isValidLead(lead), MESSAGE_CONSTRAINTS);
    }

    /**
     * The factory method for leads given a string. Checks if the string is non-null;
     *
     * @param lead the string representation for the {@code Lead}
     * @return the {@code Lead} object corresponding to the type
     */
    public static Lead of(String lead) throws IllegalArgumentException {
        assert Objects.nonNull(lead) : "Lead should take non-null values";
        checkArgument(isValidLead(lead), MESSAGE_CONSTRAINTS);
        LeadType leadType = Enum.valueOf(LeadType.class, lead.toUpperCase());
        return Lead.of(leadType);
    }

    /**
     * The factory method for leads given the {@code LeadType} enum. Checks if the leadType is non-null;
     *
     * @param leadType the type of lead
     * @return the {@code Lead} object corresponding to the type
     */
    public static Lead of(LeadType leadType) throws IllegalArgumentException {
        assert Objects.nonNull(leadType);
        switch (leadType) {
        case HOT:
            return HotLead.getInstance();
        case WARM:
            return WarmLead.getInstance();
        case COLD:
            return ColdLead.getInstance();
        default:
            return UnknownLead.getInstance();
        }
    }

    /**
     * Determines whether a given string is a valid lead.
     * A valid lead is either "hot", "warm", "cold", or "unknown".
     *
     * @param lead the string to check
     * @return true if the string is a valid lead, false otherwise
     */
    public static boolean isValidLead(String lead) {
        assert Objects.nonNull(lead) : "lead should not be null";
        for (LeadType leadType : LeadType.values()) {
            if (lead.equalsIgnoreCase(leadType.name())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Returns the follow-up period for the corresponding lead in weeks.
     *
     * @return the follow-up period in weeks.
     */
    public abstract int getFollowUpPeriod();

    public abstract String toString();

    /**
     * Returns true if the lead is hot, false otherwise.
     */
    public boolean isHot() {
        return false;
    }

    /**
     * Returns true if the lead is warm, false otherwise.
     */
    public boolean isWarm() {
        return false;
    }

    /**
     * Returns true if the lead is cold, false otherwise.
     */
    public boolean isCold() {
        return false;
    }
}
