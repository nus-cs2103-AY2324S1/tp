package connexion.model.person;

import static connexion.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's job in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJob(String)}
 */
public class Job implements PersonListDetailField<String> {

    public static final String MESSAGE_CONSTRAINTS = "Job can take any values, and it should not be blank";

    /*
     * The first character of the job must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String value;

    /**
     * Constructs an {@code Job}.
     *
     * @param job A valid job.
     */
    public Job(String job) {
        requireNonNull(job);
        checkArgument(isValidJob(job), MESSAGE_CONSTRAINTS);
        value = job;
    }

    /**
     * Returns true if a given string is a valid job.
     */
    public static boolean isValidJob(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return value.equals(otherJob.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String getDetailString() {
        return "Job: " + value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getListString() {
        return value;
    }
}
