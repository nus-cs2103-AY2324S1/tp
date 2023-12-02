package seedu.address.logic.commands.testdata;

import static seedu.address.testutil.TypicalCompanies.ACCENTURE;
import static seedu.address.testutil.TypicalCompanies.APPLE;
import static seedu.address.testutil.TypicalCompanies.MICROSOFT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;

/**
 * A utility class containing a list of {@code Company} objects to be used in FindCommand tests.
 */
public class FindCommandTestCompanies {

    public static final Company APPLESTORE = new CompanyBuilder().withName("AppleStore").build();
    public static final Company APPLE_STORES = new CompanyBuilder().withName("Apple Stores").build();
    public static final Company BANANASTORE = new CompanyBuilder().withName("BananaStore").build();
    public static final Company B = new CompanyBuilder().withName("B").build();
    public static final Company C = new CompanyBuilder().withName("C").build();

    private FindCommandTestCompanies() {} // prevents instantiation

    public static AddressBook getFindCommandTestAddressBook() {
        AddressBook ab = new AddressBook();
        for (Company company : getFindCommandTestCompanies()) {
            ab.addCompany(company);
        }
        return ab;
    }

    public static List<Company> getFindCommandTestCompanies() {
        return new ArrayList<>(Arrays.asList(APPLE, APPLESTORE, APPLE_STORES, BANANASTORE, B, C, MICROSOFT, ACCENTURE));
    }
}
