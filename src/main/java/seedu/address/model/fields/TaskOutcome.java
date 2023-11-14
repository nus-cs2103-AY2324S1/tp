package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * An outcome of a task for a job application
 */
public class TaskOutcome {

    private static final List<String> possibleOutcomes =
            new LinkedList<>(Arrays.asList("follow-up", "ghosted", "offered", "rejected", "awaiting"));

    private final Outcome outcome;

    /**
     * Creates a TaskOutcome object
     * @param outcome of a task
     */
    public TaskOutcome(Outcome outcome) {
        requireNonNull(outcome);
        this.outcome = outcome;
    }

    public static boolean isValidTaskOutcome(String outcome) {
        return possibleOutcomes.contains(outcome);
    }

    public Outcome getTaskOutcome() {
        requireNonNull(outcome);
        return outcome;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskOutcome)) {
            return false;
        }

        TaskOutcome otherOutcome = (TaskOutcome) other;
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
