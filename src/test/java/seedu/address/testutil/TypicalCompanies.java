package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_DESCRIPTION_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_EMAIL_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_PHONE_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.testutil.TypicalInternships.DATA_ANALYST_WITH_DATETIME;
import static seedu.address.testutil.TypicalInternships.DATETIME_A;
import static seedu.address.testutil.TypicalInternships.DATETIME_B;
import static seedu.address.testutil.TypicalInternships.DATETIME_C;
import static seedu.address.testutil.TypicalInternships.DATETIME_D;
import static seedu.address.testutil.TypicalInternships.DATETIME_E;
import static seedu.address.testutil.TypicalInternships.MARKETING_INTERN_WITHOUT_DATETIME;
import static seedu.address.testutil.TypicalInternships.NO_DATETIME_A;
import static seedu.address.testutil.TypicalInternships.NO_DATETIME_B;
import static seedu.address.testutil.TypicalInternships.NO_DATETIME_C;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITHOUT_DATETIME;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITH_DATETIME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.company.Company;
/**
 * A utility class containing a list of {@code Company} objects to be used in tests.
 */
public class TypicalCompanies {
    public static final Company APPLE = new CompanyBuilder().withCompanyName("Apple Inc.")
        .withCompanyEmail("apple@example.com")
        .withCompanyPhone("12345678")
        .withDescription("A technology company.")
        .withTags("tech", "innovative")
        .withInternships(SOFTWARE_ENGINEER_WITH_DATETIME, DATA_ANALYST_WITH_DATETIME).build();

    public static final Company MICROSOFT = new CompanyBuilder().withCompanyName("Microsoft Corporation")
        .withCompanyEmail("microsoft@example.com")
        .withCompanyPhone("23456789")
        .withDescription("A multinational technology company.")
        .withTags("tech", "windows")
        .withInternships(SOFTWARE_ENGINEER_WITHOUT_DATETIME, DATA_ANALYST_WITH_DATETIME,
                MARKETING_INTERN_WITHOUT_DATETIME).build();

    public static final Company GOOGLE = new CompanyBuilder().withCompanyName("Google LLC")
        .withCompanyEmail("google@example.com")
        .withCompanyPhone("34567890")
        .withDescription("An internet-related services and products company.")
        .withTags("tech", "search")
        .withInternships(SOFTWARE_ENGINEER_WITHOUT_DATETIME, MARKETING_INTERN_WITHOUT_DATETIME).build();

    // Manually added
    public static final Company AMAZON = new CompanyBuilder().withCompanyName("Amazon.com Inc.")
        .withCompanyEmail("amazon@example.com")
        .withCompanyPhone("45678901")
        .withDescription("An online retailer and cloud services company.")
        .build();

    public static final Company NETFLIX = new CompanyBuilder().withCompanyName("Netflix Inc.")
        .withCompanyEmail("netflix@example.com")
        .withCompanyPhone("56789012")
        .withDescription("A streaming service company.")
        .build();

    public static final Company ORACLE = new CompanyBuilder().withCompanyName(VALID_COMPANY_NAME_ORACLE)
            .withCompanyPhone(VALID_COMPANY_PHONE_ORACLE).withCompanyEmail(VALID_COMPANY_EMAIL_ORACLE)
            .withDescription(VALID_COMPANY_DESCRIPTION_ORACLE)
            .withTags(VALID_TAG_TECH).build();

    public static final Company C_NO_INTERNSHIP_A = new CompanyBuilder().withCompanyName("No Internship A")
            .withCompanyPhone("12345678").withCompanyEmail("NO_INTERNSHIP_A@example.com").build();

    public static final Company C_NO_INTERNSHIP_B = new CompanyBuilder().withCompanyName("No Internship B")
            .withCompanyPhone("23456789").withCompanyEmail("NO_INTERNSHIP_B@example.com").build();

    public static final Company C_INTERNSHIP_AB = new CompanyBuilder().withCompanyName("Internship AB")
            .withCompanyPhone("34567890").withCompanyEmail("INTERNSHIP_AB@example.com").withInternships(
                DATETIME_A, DATETIME_B, NO_DATETIME_A).build();

    public static final Company C_INTERNSHIP_BC = new CompanyBuilder().withCompanyName("Internship BC")
            .withCompanyPhone("45678901").withCompanyEmail("INTERNSHIP_BC@example.com").withInternships(
                DATETIME_B, DATETIME_C, NO_DATETIME_B).build();

    public static final Company C_INTERNSHIP_CD = new CompanyBuilder().withCompanyName("Internship CD")
            .withCompanyPhone("56789012").withCompanyEmail("INTERNSHIP_CD@example.com").withInternships(
                DATETIME_C, DATETIME_D, NO_DATETIME_C).build();

    public static final Company C_INTERNSHIP_AC = new CompanyBuilder().withCompanyName("Internship AC")
            .withCompanyPhone("56789012").withCompanyEmail("INTERNSHIP_AC@example.com").withInternships(
                DATETIME_A, DATETIME_C, NO_DATETIME_C).build();

    public static final Company C_INTERNSHIP_DE = new CompanyBuilder().withCompanyName("Internship DE")
            .withCompanyPhone("56789012").withCompanyEmail("INTERNSHIP_DED@example.com").withInternships(
                DATETIME_D, DATETIME_E).build();
    public static final String KEYWORD_MATCHING_TECH = "tech"; // A keyword that matches TECH

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

    /**
     * Returns an {@code AddressBook} for sort command integration test.
     */
    public static AddressBook getTypicalAddressBookForSort() {
        AddressBook ab = new AddressBook();
        for (Company company : getTypicalCompaniesForSort()) {
            ab.addCompany(company);
        }
        return ab;
    }
    public static List<Company> getTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(APPLE, MICROSOFT, GOOGLE, AMAZON, NETFLIX));
    }

    public static List<Company> getTypicalCompaniesForSort() {
        return new ArrayList<>(Arrays.asList(C_NO_INTERNSHIP_A, C_NO_INTERNSHIP_B, C_INTERNSHIP_AB,
                C_INTERNSHIP_BC, C_INTERNSHIP_CD, C_INTERNSHIP_AC, C_INTERNSHIP_DE));
    }
}
