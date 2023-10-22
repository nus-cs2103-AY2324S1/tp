package seedu.application.model.job;

import static seedu.application.model.job.Company.COMPANY_SPECIFIER;
import static seedu.application.model.job.Deadline.DEADLINE_SPECIFIER;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;
import static seedu.application.model.job.Status.STATUS_SPECIFIER;

import java.util.Comparator;

/**
 * Compares two jobs based on the field specifier provided.
 */
public class FieldComparator implements Comparator<Job> {
    public static final String EMPTY_COMPARATOR_SPECIFIER = "";
    private final String specifier;

    /**
     * Constructs a {@code FieldComparator} using the specifier given.
     * @param specifier String denoting which field to sort by.
     */
    public FieldComparator(String specifier) {
        this.specifier = specifier;
    }

    /**
     * Checks if a {@code String} is a valid specifier.
     * @param specifier The user String input.
     */
    public static boolean isValidSpecifier(String specifier) {
        return specifier.equals(EMPTY_COMPARATOR_SPECIFIER)
                || specifier.equals(COMPANY_SPECIFIER)
                || specifier.equals(ROLE_SPECIFIER)
                || specifier.equals(DEADLINE_SPECIFIER)
                || specifier.equals(STATUS_SPECIFIER);
    }

    /**
     * Checks if the comparator contains a specifier.
     */
    public boolean hasSpecifier() {
        return !specifier.equals(EMPTY_COMPARATOR_SPECIFIER);
    }

    @Override
    public int compare(Job job1, Job job2) {
        switch (specifier) {
        case COMPANY_SPECIFIER:
            return job1.getCompany().name.compareToIgnoreCase(job2.getCompany().name);
        case ROLE_SPECIFIER:
            return job1.getRole().description.compareToIgnoreCase(job2.getRole().description);
        case DEADLINE_SPECIFIER:
            return job1.getDeadline().compareTo(job2.getDeadline());
        case STATUS_SPECIFIER:
            return job1.getStatus().status.compareToIgnoreCase(job2.getStatus().status);
        default:
            return -1;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FieldComparator)) {
            return false;
        }

        FieldComparator otherSpecifier = (FieldComparator) other;
        return specifier.equals(otherSpecifier.specifier);
    }
}
