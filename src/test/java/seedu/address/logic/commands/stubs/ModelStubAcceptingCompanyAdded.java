package seedu.address.logic.commands.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.company.Company;

/**
 * A Model stub that always accept the company being added.
 */
public class ModelStubAcceptingCompanyAdded extends ModelStub {
    public final ArrayList<Company> companiesAdded = new ArrayList<>();
    public final ArrayList<Company> companiesToView = new ArrayList<>();

    @Override
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return companiesAdded.stream().anyMatch(company::isSameCompany);
    }

    @Override
    public void addCompany(Company company) {
        requireNonNull(company);
        companiesAdded.add(company);
    }

    @Override
    public void setCurrentViewedCompany(Company company) {
        requireNonNull(company);
        companiesToView.add(company);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
