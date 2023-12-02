package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.company.Company;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Company> filteredCompanies;
    private final FilteredList<Company> currentViewedCompany;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCompanies = new FilteredList<>(this.addressBook.getCompanyList());
        currentViewedCompany = new FilteredList<>(this.addressBook.getCurrentViewedCompany());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return addressBook.hasCompany(company);
    }

    @Override
    public Company getDuplicateCompany(Company company) {
        requireNonNull(company);
        return addressBook.getDuplicateCompany(company);
    }

    @Override
    public void deleteCompany(Company target) {
        addressBook.removeCompany(target);
    }

    @Override
    public void addCompany(Company company) {
        addressBook.addCompany(company);
        updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
    }

    @Override
    public void setCompany(Company target, Company editedCompany) {
        requireAllNonNull(target, editedCompany);

        addressBook.setCompany(target, editedCompany);
    }

    @Override
    public void setCurrentViewedCompany(Company company) {
        requireNonNull(company);
        addressBook.setCurrentViewedCompany(company);
    }

    @Override
    public void checkDelete(Company company) {
        if (currentViewedCompany != null && currentViewedCompany.contains(company)) {
            addressBook.clearDetailPanel();
        }
    }

    @Override
    public void clearCompanyDetailPanel() {
        addressBook.clearDetailPanel();
    }

    @Override
    public void filterCompaniesByStatus(Predicate<Company> predicate) {
        addressBook.clearDetailPanel();
        updateFilteredCompanyList(predicate);
    }

    @Override
    public void findCompanies(Predicate<Company> predicate) {
        addressBook.clearDetailPanel();
        updateFilteredCompanyList(predicate);
    }

    @Override
    public void setAllCompanies(List<Company> companies) {
        addressBook.setCompanies(companies);
    }

    //=========== Filtered Company List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Company} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Company> getFilteredCompanyList() {
        return filteredCompanies;
    }

    @Override
    public void updateFilteredCompanyList(Predicate<Company> predicate) {
        requireNonNull(predicate);
        filteredCompanies.setPredicate(predicate);
    }

    @Override
    public void updateCurrentViewedCompany(Predicate<Company> predicate) {
        requireNonNull(predicate);
        currentViewedCompany.setPredicate(predicate);
    }

    @Override
    public ObservableList<Company> getCurrentViewedCompany() {
        return currentViewedCompany;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredCompanies.equals(otherModelManager.filteredCompanies);
    }

    @Override
    public int getDuplicateIndexFromOriginalAddressbook(Company company) {
        return addressBook.getDuplicateIndex(company);
    }

    @Override
    public int getDuplicateIndexFromFilteredAddressbook(Company company) {
        return filteredCompanies.indexOf(company);
    }
}
