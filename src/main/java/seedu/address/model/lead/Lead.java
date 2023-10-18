package seedu.address.model.lead;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the potential of a client.
 */
public class Lead {
    public static final String MESSAGE_CONSTRAINTS = "Lead should only take values hot|warm|cold";

    final private LeadType leadType;

    /**
     * Returns an empty lead. Used as a default, or when no leads are tagged.
     */
    public Lead() {
        this.leadType = null;
    }

    /**
     * Returns a lead given a string. Checks if the string is a valid lead;
     *
     * @param lead the string representation for the lead.
     */
    public Lead(String lead) {
        checkArgument(isValidLead(lead.toUpperCase()), MESSAGE_CONSTRAINTS);
        this.leadType = Enum.valueOf(LeadType.class, lead.toUpperCase());
    }

    public Lead(LeadType lead) {
        this.leadType = lead;
    }

    private boolean isValidLead(String lead) {
        return lead.equals("HOT") || lead.equals("WARM") || lead.equals("COLD");
    }

    @Override
    public String toString() {
        return leadType.name();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Lead)) {
            return false;
        }

        Lead otherLead = (Lead) other;
        return leadType.equals(otherLead.leadType);
    }

    @Override
    public int hashCode() {
        return leadType.hashCode();
    }
}
