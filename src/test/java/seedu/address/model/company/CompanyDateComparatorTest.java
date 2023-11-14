package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCompanies.AMAZON;
import static seedu.address.testutil.TypicalCompanies.APPLE;
import static seedu.address.testutil.TypicalInternships.DATA_ANALYST_WITH_DATETIME;
import static seedu.address.testutil.TypicalInternships.FINANCE_INTERN_WITH_DATETIME;
import static seedu.address.testutil.TypicalInternships.HR_INTERN_WITHOUT_DATETIME;
import static seedu.address.testutil.TypicalInternships.MARKETING_INTERN_WITHOUT_DATETIME;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITH_DATETIME;
import static seedu.address.testutil.TypicalInternships.UX_DESIGNER_WITH_DATETIME;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.company.internship.InternshipInterviewDateTime;

public class CompanyDateComparatorTest {

    private Company companyA;
    private Company companyB;

    @BeforeEach
    public void setUp() {
        companyA = new Company(AMAZON.getCompanyName(), AMAZON.getCompanyPhone(), AMAZON.getCompanyEmail(),
                AMAZON.getCompanyDescription(), AMAZON.getTags());
        companyA.addInternship(SOFTWARE_ENGINEER_WITH_DATETIME);
        companyA.addInternship(DATA_ANALYST_WITH_DATETIME);
        companyA.addInternship(FINANCE_INTERN_WITH_DATETIME);

        companyB = new Company(APPLE.getCompanyName(), APPLE.getCompanyPhone(), APPLE.getCompanyEmail(),
                APPLE.getCompanyDescription(), APPLE.getTags());
        companyB.addInternship(HR_INTERN_WITHOUT_DATETIME);
        companyB.addInternship(UX_DESIGNER_WITH_DATETIME);
        companyB.addInternship(MARKETING_INTERN_WITHOUT_DATETIME);
    }

    @Test
    public void compare_companyAIsEarlier() {
        Predicate<InternshipInterviewDateTime> allDates = date -> true;
        CompanyDateComparator comparator = new CompanyDateComparator(allDates);
        // SOFTWARE_ENGINEER_WITH_DATETIME is the earliest
        assertEquals(-1, comparator.compare(companyA, companyB));
    }

    @Test
    public void compare_companyBIsEarlier() {
        Predicate<InternshipInterviewDateTime> onlyJanuaryDates =
            date -> date.isAfter(new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 16, 0, 0)));
        CompanyDateComparator comparator = new CompanyDateComparator(onlyJanuaryDates);
        // UX_DESIGNER_WITH_DATETIME is the earliest
        assertEquals(1, comparator.compare(companyA, companyB));
    }

    @Test
    public void compare_bothCompaniesEqual() {
        Predicate<InternshipInterviewDateTime> onlyDecemberDates =
            date -> date.isBefore(new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 1, 0, 0)));
        CompanyDateComparator comparator = new CompanyDateComparator(onlyDecemberDates);
        // Comparing company to itself should be 0
        assertEquals(0, comparator.compare(companyA, companyA));
    }
}
