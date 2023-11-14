package seedu.address.model.internship;

import java.util.Comparator;

/**
 * Provides comparators for different properties of an Internship.
 */
public class InternshipComparators {
    public static final Comparator<Internship> BY_COMPANY_NAME =
            Comparator.comparing(Internship::getCompanyName);

    public static final Comparator<Internship> BY_ROLE =
            Comparator.comparing(Internship::getRole);

    public static final Comparator<Internship> BY_APPLICATION_STATUS =
            Comparator.comparing(Internship::getApplicationStatus);

    public static final Comparator<Internship> BY_DEADLINE =
            Comparator.comparing(Internship::getDeadline);

    public static final Comparator<Internship> BY_START_DATE =
            Comparator.comparing(Internship::getStartDate);

    public static final Comparator<Internship> BY_DURATION =
            Comparator.comparing(Internship::getDuration);
}
