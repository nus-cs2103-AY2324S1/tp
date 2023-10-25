package seedu.address.model.company;

import java.util.function.Predicate;

/**
 * Tests that a {@code Company}'s {@code ApplicationStatus} matches the status given.
 */
public class ApplicationStatusPredicate implements Predicate<Company> {
    private final ApplicationStatus status;

    public ApplicationStatusPredicate(ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public boolean test(Company company) {
        return company.getStatus().equals(status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ApplicationStatusPredicate
                && status.equals(((ApplicationStatusPredicate) other).status));
    }
}
