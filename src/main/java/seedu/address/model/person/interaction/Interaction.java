package seedu.address.model.person.interaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    private final String interactionNote;
    private final Outcome outcome;
    private final LocalDate date;

    /**
    * Constructs a {@code Interaction} with a default LeadStatus of UNKNOWN.
    *
    * @param interactionNote A valid Interaction note.
    * @param outcome A valid outcome.
    */
    public Interaction(String interactionNote) {
        this.interactionNote = interactionNote;
        this.outcome = Outcome.UNKNOWN;
        this.date = LocalDate.now();
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
        this.date = LocalDate.now();
    }

    /**
     * Constructs a {@code Interaction}.
     *
     * @param interactionNote A valid Interaction note.
     * @param outcome A valid LeadStatus.
     * @param date A valid date.
     */
    public Interaction(String interactionNote, Outcome outcome, LocalDate date) {
        this.interactionNote = interactionNote;
        this.outcome = outcome;
        this.date = date;
    }

    public String getInteractionNote() {
        return this.interactionNote;
    }

    public Outcome getOutcome() {
        return this.outcome;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns true if the outcome of the interaction is the same as the given outcome.
     * @param outcome Outcome to be compared.
     */
    public boolean isOutcome(Outcome outcome) {
        return this.outcome == outcome;
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
