package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCompanies.AMAZON;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.testutil.TypicalInternships;

public class CompanyDateRangePredicateTest {

    private Company companyWithDates;

    @BeforeEach
    public void setUp() {
        companyWithDates = new Company(AMAZON.getCompanyName(), AMAZON.getCompanyPhone(), AMAZON.getCompanyEmail(),
            AMAZON.getCompanyDescription(), AMAZON.getTags());
        companyWithDates.addInternship(TypicalInternships.SOFTWARE_ENGINEER_WITH_DATETIME);
        companyWithDates.addInternship(TypicalInternships.DATA_ANALYST_WITH_DATETIME);
        companyWithDates.addInternship(TypicalInternships.MARKETING_INTERN_WITHOUT_DATETIME);
        companyWithDates.addInternship(TypicalInternships.FINANCE_INTERN_WITH_DATETIME);
        companyWithDates.addInternship(TypicalInternships.HR_INTERN_WITHOUT_DATETIME);
        companyWithDates.addInternship(TypicalInternships.UX_DESIGNER_WITH_DATETIME);
    }

    @Test
    public void test_withinRange_returnsTrue() {
        CompanyDateRangePredicate predicate =
            new CompanyDateRangePredicate(
                Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2023, 12, 15, 0, 0))),
                Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 20, 0, 0)))
            );
        assertTrue(predicate.test(companyWithDates)); // Because DATA_ANALYST_WITH_DATETIME is within this range
    }

    @Test
    public void test_outsideRange_returnsFalse() {
        CompanyDateRangePredicate predicate =
            new CompanyDateRangePredicate(
                Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2022, 1, 1, 0, 0))),
                Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2023, 1, 1, 0, 0)))
            );
        assertFalse(predicate.test(companyWithDates)); // None of the internships are within this range
    }

    @Test
    public void test_startDateOnly_returnsTrue() {
        CompanyDateRangePredicate predicate =
            new CompanyDateRangePredicate(
                Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2023, 12, 1, 0, 0))),
                Optional.empty()
            );
        assertTrue(predicate.test(companyWithDates)); // Because several internships are after this start date
    }

    @Test
    public void test_endDateOnly_returnsTrue() {
        CompanyDateRangePredicate predicate =
            new CompanyDateRangePredicate(
                Optional.empty(),
                Optional.of(new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 10, 0, 0)))
            );
        assertTrue(predicate.test(companyWithDates)); // Because SOFTWARE_ENGINEER_WITH_DATETIME is before this end date
    }

    @Test
    public void test_noDates_returnsTrue() {
        CompanyDateRangePredicate predicate =
            new CompanyDateRangePredicate(Optional.empty(), Optional.empty());
        assertTrue(predicate.test(companyWithDates)); // Should always return true since no specific range is provided
    }
}

