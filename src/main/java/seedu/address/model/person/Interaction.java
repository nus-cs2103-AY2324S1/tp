package seedu.address.model.person;

/**
 * Represents an interaction with the client in the addressbook.
 */
public class Interaction {

    /**
     * Represents the outcome of the interaction.
     */
    private enum Outcome {
        INTERESTED,
        NOT_INTERESTED,
        FOLLOWUP_REQUIRED,
        UNKNOWN
    }

    public final String interactionNote;
    public final Outcome outcome;

    /**
    * Constructs a {@code Interaction} with a default LeadStatus of UNKNOWN.
    *
    * @param interactionNote A valid Interaction note.
    * @param outcome A valid outcome.
    */
    public Interaction(String interactionNote) {
        this.interactionNote = interactionNote;
        this.outcome = Outcome.UNKNOWN;
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
    }

    @Override
    public String toString() {
        return interactionNote + "\nThe outcome of this interaction is: " + outcome;
    }
}
