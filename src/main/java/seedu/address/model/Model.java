package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.company.Company;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Company> PREDICATE_SHOW_ALL_COMPANIES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a company with the same identity as {@code company} exists in the address book.
     */
    boolean hasCompany(Company company);

    Company getDuplicateCompany(Company company);

    /**
     * Deletes the given company.
     * The company must exist in the address book.
     */
    void deleteCompany(Company target);

    /**
     * Adds the given company.
     * {@code company} must not already exist in the address book.
     */
    void addCompany(Company company);

    /**
     * Replaces the given company {@code target} with {@code editedCompany}.
     * {@code target} must exist in the address book.
     * The company identity of {@code editedCompany} must not be the same as another existing company in the address
     * book.
     */
    void setCompany(Company target, Company editedCompany);

    /**
     * Sets all the companies in the address book.
     *
     * @param companies the list of companies to be set.
     */
    void setAllCompanies(List<Company> companies);

    /**
     * Returns an unmodifiable view of the filtered company list.
     *
     * @return the filtered company list.
     */
    ObservableList<Company> getFilteredCompanyList();

    /**
     * Updates the filter of the filtered company list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompanyList(Predicate<Company> predicate);

    /**
     * Sets the current viewed company to the company {@code company}.
     *
     * @param company the company to be set as the current viewed company.
     */
    void setCurrentViewedCompany(Company company);

    /**
     * Returns the current viewed company.
     *
     * @return the current viewed company.
     */
    public ObservableList<Company> getCurrentViewedCompany();

    /**
     * Updates the current viewed company to the company {@code company}.
     *
     * @param predicate the predicate to be used to update the current viewed company.
     */
    public void updateCurrentViewedCompany(Predicate<Company> predicate);

    /**
     * Checks if the company to be deleted is the current viewed company.
     * If it is, the detail panel will be cleared.
     *
     * @param company the company to be deleted.
     */
    public void checkDelete(Company company);


    /**
     * Clears the company detail panel.
     */
    public void clearCompanyDetailPanel();

    /**
     * Filters the companies in the address book by their application status.
     *
     * @param predicate the predicate to be used to filter the companies.
     */
    void filterCompaniesByStatus(Predicate<Company> predicate);

    /**
     * Returns the index of the duplicate company in the original address book.
     *
     * @param company the company to be checked.
     * @return the index of the duplicate company in the original address book.
     */
    int getDuplicateIndexFromOriginalAddressbook(Company company);

    /**
     * Returns the index of the duplicate company in the filtered address book.
     *
     * @param company the company to be checked.
     * @return the index of the duplicate company in the filtered address book.
     */
    int getDuplicateIndexFromFilteredAddressbook(Company company);

    /**
     * Finds the companies in the address book by their application status.
     *
     * @param predicate the predicate to be used to find the companies.
     */
    void findCompanies(Predicate<Company> predicate);
}
