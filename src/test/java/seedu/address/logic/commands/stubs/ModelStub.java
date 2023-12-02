package seedu.address.logic.commands.stubs;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.company.Company;

/**
 * A default model stub that have all the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCompany(Company company) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCompany(Company company) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCompany(Company target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCompany(Company target, Company editedCompany) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAllCompanies(List<Company> companies) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Company> getFilteredCompanyList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCompanyList(Predicate<Company> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCurrentViewedCompany(Company company) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Company> getCurrentViewedCompany() {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public void updateCurrentViewedCompany(Predicate<Company> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void clearCompanyDetailPanel() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void checkDelete(Company company) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void filterCompaniesByStatus(Predicate<Company> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Company getDuplicateCompany(Company company) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getDuplicateIndexFromFilteredAddressbook(Company company) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getDuplicateIndexFromOriginalAddressbook(Company company) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void findCompanies(Predicate<Company> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
