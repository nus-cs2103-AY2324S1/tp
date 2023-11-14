package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * An outcome for a job application
 */
public class ApplicationOutcome {

    private static final List<String> possibleOutcomes =
            new LinkedList<>(Arrays.asList("follow-up", "ghosted", "offered", "rejected", "accepted", "awaiting"));

    private final Outcome outcome;

    /**
     * Creates an application outcome
     * @param outcome of an application
     */
    public ApplicationOutcome(Outcome outcome) {
        requireNonNull(outcome);
        this.outcome = outcome;
    }

    public static boolean isValidApplicationOutcome(String outcome) {
        return possibleOutcomes.contains(outcome);
    }

    public Outcome getApplicationOutcome() {
        return outcome;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationOutcome)) {
            return false;
        }

        ApplicationOutcome otherOutcome = (ApplicationOutcome) other;
        return outcome.equals(otherOutcome.outcome);
    }

    @Override
    public String toString() {
        return outcome.toString();
    }

    @Override
    public int hashCode() {
        return outcome.hashCode();
    }

}
