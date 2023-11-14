package seedu.address.model.company;

// CompanyDateRangePredicate.java

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.model.company.internship.InternshipInterviewDateTime;

/**
 * Tests that a {@code Company}'s {@code Internship}'s {@code InternshipInterviewDateTime} is within the given range.
 */
public class CompanyDateRangePredicate implements Predicate<Company> {
    private final Optional<InternshipInterviewDateTime> startDateTime;
    private final Optional<InternshipInterviewDateTime> endDateTime;

    /**
     * Constructor for CompanyDateRangePredicate.
     *
     * @param startDateTime The start date and time of the range.
     * @param endDateTime The end date and time of the range.
     */
    public CompanyDateRangePredicate(Optional<InternshipInterviewDateTime> startDateTime,
                                     Optional<InternshipInterviewDateTime> endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean test(Company company) {
        return company.getInternshipList().stream()
            .flatMap(internship -> Stream.ofNullable(internship.getInternshipDateTime().orElse(null)))
            .anyMatch(date -> {
                if (startDateTime.isPresent() && endDateTime.isPresent()) {
                    return date.isAfter(startDateTime.get()) && date.isBefore(endDateTime.get());
                }
                if (startDateTime.isPresent()) {
                    return date.isAfter(startDateTime.get());
                }
                if (endDateTime.isPresent()) {
                    return date.isBefore(endDateTime.get());
                }
                return true;
            });
    }
}
