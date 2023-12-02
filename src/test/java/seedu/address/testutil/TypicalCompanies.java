package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECRUITER_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECRUITER_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_TIKTOK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.company.Company;

/**
 * A utility class containing a list of {@code Company} objects to be used in tests.
 */
public class TypicalCompanies {

    public static final Company META = new CompanyBuilder().withName("Meta")
            .withEmail("alice@example.com").withPhone("94351253")
            .withRole("Software Engineer").withDeadline("10-10-2023").withStatus("PA")
            .withRecruiterName("John Doe").withPriority("LOW").withRemark("No remarks").build();
    public static final Company AMAZON = new CompanyBuilder().withName("Amazon")
            .withEmail("janed@example.com").withPhone("98765432")
            .withRole("Software Engineer").withDeadline("11-11-2023").withStatus("PA")
            .withRecruiterName("Jane Doe").withPriority("MEDIUM").withRemark("JavaScript&CSS").build();
    public static final Company MICROSOFT = new CompanyBuilder().withName("Microsoft").withEmail("heinz@example.com")
            .withPhone("95352563").withRole("Data Engineer").withDeadline("11-12-2023").withStatus("PA")
            .withRecruiterName("Jim Doe").withPriority("HIGH").withRemark("www.bing.com").build();
    public static final Company DSTA = new CompanyBuilder().withName("DSTA").withEmail("cornelia@example.com")
            .withPhone("87652533").withRole("Data Engineer").withDeadline("03-12-2023").withStatus("PA")
            .withRecruiterName("Tim Doe").withPriority("LOW").withRemark("Java").build();
    public static final Company APPLE = new CompanyBuilder().withName("Apple").withEmail("werner@example.com")
            .withPhone("9482224").withRole("UX Designer").withDeadline("09-11-2023").withStatus("PA")
            .withRecruiterName("Tom Doe").withPriority("MEDIUM").withRemark("Python").build();
    public static final Company ACCENTURE = new CompanyBuilder().withName("Accenture").withEmail("lydia@example.com")
            .withPhone("9482427").withRole("UI Designer").withDeadline("08-11-2023").withStatus("PA")
            .withRecruiterName("Mary Doe").withPriority("HIGH").withRemark("No remarks").build();
    public static final Company NETFLIX = new CompanyBuilder().withName("Netflix").withEmail("anna@example.com")
            .withPhone("9482442").withRole("Web Developer").withDeadline("05-12-2023").withStatus("PA")
            .withRecruiterName("Timmy Doe").withPriority("HIGH").withRemark("No remarks").build();

    // Manually added
    public static final Company TESLA = new CompanyBuilder().withName("Tesla").withEmail("stefan@example.com")
            .withPhone("8482424").withRole("Mobile App Developer").withDeadline("11-11-2023").withStatus("PA")
            .withRecruiterName("Harry Styles").withPriority("HIGH").withRemark("No remarks").build();
    public static final Company NVIDIA = new CompanyBuilder().withName("Nvidia").withEmail("hans@example.com")
            .withPhone("8482131").withRole("Software Engineer").withDeadline("12-12-2023").withStatus("PA")
            .withRecruiterName("Inez Lim").withPriority("HIGH").withRemark("No remarks").build();

    // Manually added - Company's details found in {@code CommandTestUtil}
    public static final Company GOOGLE = new CompanyBuilder().withName(VALID_NAME_GOOGLE).withPhone(VALID_PHONE_GOOGLE)
            .withEmail(VALID_EMAIL_GOOGLE).withRole(VALID_ROLE_GOOGLE).withDeadline(VALID_DEADLINE_GOOGLE)
            .withStatus(VALID_STATUS_GOOGLE).withRecruiterName(VALID_RECRUITER_NAME_GOOGLE)
            .withPriority(VALID_PRIORITY_GOOGLE).withRemark(VALID_REMARK_GOOGLE)
            .build();
    public static final Company TIKTOK = new CompanyBuilder().withName(VALID_NAME_TIKTOK).withPhone(VALID_PHONE_TIKTOK)
            .withEmail(VALID_EMAIL_TIKTOK).withRole(VALID_ROLE_TIKTOK).withDeadline(VALID_DEADLINE_TIKTOK)
            .withStatus(VALID_STATUS_TIKTOK).withRecruiterName(VALID_RECRUITER_NAME_TIKTOK)
            .withPriority(VALID_PRIORITY_TIKTOK).withRemark(VALID_REMARK_TIKTOK)
            .build();

    private TypicalCompanies() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical companies.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Company company : getTypicalCompanies()) {
            ab.addCompany(company);
        }
        return ab;
    }

    public static List<Company> getTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(META, AMAZON, MICROSOFT, DSTA, APPLE, ACCENTURE, NETFLIX));
    }
}
