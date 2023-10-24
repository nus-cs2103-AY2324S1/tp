package seedu.address.model.person;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an interaction with the client in the addressbook.
 */
public class Interaction {

    /**
     * Represents the outcome of the interaction.
     */
    public static enum Outcome {
        CLOSED,
        INTERESTED,
        NOT_INTERESTED,
        FOLLOWUP_REQUIRED,
        UNKNOWN;

        public static final String MESSAGE_CONSTRAINTS = "Outcome should be one of the following: "
                + "CLOSED, INTERESTED, NOT_INTERESTED, FOLLOWUP_REQUIRED, UNKNOWN "
                + "and it should not be blank";

        /**
        * Returns true if a given string is a valid outcome.
        */
        public static boolean isValidOutcome(String test) {
            return test.matches("CLOSED|INTERESTED|NOT_INTERESTED|FOLLOWUP_REQUIRED|UNKNOWN");
        }
    }

    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

    private final String interactionNote;
    private final Outcome outcome;
    private final Date date;

    /**
    * Constructs a {@code Interaction} with a default LeadStatus of UNKNOWN.
    *
    * @param interactionNote A valid Interaction note.
    * @param outcome A valid outcome.
    */
    public Interaction(String interactionNote) {
        this.interactionNote = interactionNote;
        this.outcome = Outcome.UNKNOWN;
        this.date = new Date();
    }

    /**
    * Constructs a {@code Interaction}.
    *
    * @param interactionNote A valid Interaction note.
    * @param outcome A valid LeadStatus.
    */
    public Interaction(String interactionNote, Outcome outcome) {
        this.interactionNote = interactionNote;
        this.outcome = outcome;
        this.date = new Date();
    }

    /**
     * Constructs a {@code Interaction}.
     *
     * @param interactionNote A valid Interaction note.
     * @param outcome A valid LeadStatus.
     * @param date A valid date.
     */
    public Interaction(String interactionNote, Outcome outcome, Date date) {
        this.interactionNote = interactionNote;
        this.outcome = outcome;
        this.date = date;
    }

    public String getInteraction() {
        return this.interactionNote;
    }

    public String getOutcome() {
        return this.outcome.toString();
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return interactionNote
            + "\nThe outcome of this interaction is: " + outcome
            + "\nDate: " + DEFAULT_DATE_FORMAT.format(date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interaction)) {
            return false;
        }

        Interaction otherInteraction = (Interaction) other;
        return otherInteraction.interactionNote.equals(this.interactionNote)
                && otherInteraction.outcome.equals(this.outcome);
    }
}
