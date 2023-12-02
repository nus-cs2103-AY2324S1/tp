package seedu.address.logic.commands.testdata;

import seedu.address.model.AddressBook;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;

/**
 * A utility class containing a list of {@code Company} objects to be used in SortCommand tests.
 */
public class SortCommandTestCompanies {

    public static final Company FIRST = new CompanyBuilder().withName("Google")
            .withDeadline("01-01-2023").build();
    public static final Company SECOND = new CompanyBuilder().withName("Amazon")
            .withDeadline("01-06-2023").build();
    public static final Company THIRD = new CompanyBuilder().withName("Facebook")
            .withDeadline("01-12-2023").build();


    private SortCommandTestCompanies() {} // prevents instantiation

    public static AddressBook getUnsortedAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addCompany(FIRST);
        ab.addCompany(THIRD);
        ab.addCompany(SECOND);
        return ab;
    }

    public static AddressBook getSortedAscendingAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addCompany(FIRST);
        ab.addCompany(SECOND);
        ab.addCompany(THIRD);
        return ab;
    }

    public static AddressBook getSortedDescendingAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addCompany(THIRD);
        ab.addCompany(SECOND);
        ab.addCompany(FIRST);
        return ab;
    }
}
