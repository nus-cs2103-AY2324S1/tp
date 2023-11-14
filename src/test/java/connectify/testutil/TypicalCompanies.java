package connectify.testutil;

import connectify.model.company.Company;

/**
 * A utility class to help with building Company objects.
 * It should only be used together with TypicalPersons.java
 */
public class TypicalCompanies {
    public static final Company DUMMY_COMPANY = new CompanyBuilder().withName("Unassigned").build();
    public static final Company COMPANY_1 = new CompanyBuilder().withName("Company1").withPhone("64232346")
            .withEmail("company1@gmail.com").withAddress("Blk 456, Ang Mo Kio Ave 10, #-09-123")
            .withIndustry("Customer Service").withLocation("Blk 345, Yio Chu Kang Ave")
            .withNote("").build();
    public static final Company COMPANY_2 = new CompanyBuilder().withName("Company2").withPhone("83464463")
            .withEmail("company2@gmail.com").withAddress("Blk 456, Ang Mo Kio Ave 10, #-09-123")
            .withIndustry("Retail")
            .withNote("")
            .withLocation("Blk 234, Khatib Drive").build();

    public static final Company COMPANY_3 = new CompanyBuilder().withName("Company3").withPhone("64232346")
            .withEmail("company3@gmail.com").withAddress("Blk 456, Ang Mo Kio Ave 10, #-09-123").build();


    private TypicalCompanies() {} // prevents instantiation

}
