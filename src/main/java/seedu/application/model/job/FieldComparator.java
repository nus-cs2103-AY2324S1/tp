package seedu.application.model.job;

import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Comparator;

import seedu.application.logic.parser.Prefix;

/**
 * Compares two jobs based on the field specifier provided.
 */
public class FieldComparator implements Comparator<Job> {
    private final Prefix prefix;

    /**
     * Constructs a {@code FieldComparator} using the specifier given.
     * @param prefix String denoting which field to sort by.
     */
    public FieldComparator(Prefix prefix) {
        this.prefix = prefix;
    }

    /**
     * Checks if a {@code String} is a valid specifier.
     * @param prefix The user String input.
     */
    public static boolean isValidPrefix(String prefix) {
        return prefix.equals(PREFIX_COMPANY.getPrefix())
                || prefix.equals(PREFIX_ROLE.getPrefix())
                || prefix.equals(PREFIX_STATUS.getPrefix())
                || prefix.equals(PREFIX_INDUSTRY.getPrefix())
                || prefix.equals(PREFIX_DEADLINE.getPrefix())
                || prefix.equals(PREFIX_JOB_TYPE.getPrefix());

    }

    @Override
    public int compare(Job job1, Job job2) {
        if (prefix.equals(PREFIX_COMPANY)) {
            return job1.getCompany().name.compareToIgnoreCase(job2.getCompany().name);
        }

        if (prefix.equals(PREFIX_ROLE)) {
            return job1.getRole().description.compareToIgnoreCase(job2.getRole().description);
        }

        if (prefix.equals(PREFIX_STATUS)) {
            return job1.getStatus().status.compareToIgnoreCase(job2.getStatus().status);
        }

        if (prefix.equals(PREFIX_INDUSTRY)) {
            return job1.getIndustry().industry.compareToIgnoreCase(job2.getIndustry().industry);
        }

        if (prefix.equals(PREFIX_DEADLINE)) {
            return job1.getDeadline().compareTo(job2.getDeadline());
        }

        if (prefix.equals(PREFIX_JOB_TYPE)) {
            return job1.getJobType().jobType.compareToIgnoreCase(job2.getJobType().jobType);
        }

        return -1;
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

        FieldComparator otherComparator = (FieldComparator) other;
        return prefix.equals(otherComparator.prefix);
    }
}
