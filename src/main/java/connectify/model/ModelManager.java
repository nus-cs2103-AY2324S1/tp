package connectify.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import connectify.commons.core.GuiSettings;
import connectify.commons.core.LogsCenter;
import connectify.commons.util.CollectionUtil;
import connectify.model.company.Company;
import connectify.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the address book data.
 * A key flag in this model is the current entity type, which can be either people, companies or all.
 * Depending on the current entity type, the filtered list will be updated accordingly.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Company> filterCompanies;
    private enum EntityType {
        PEOPLE, COMPANIES, ALL
    }
    private EntityType currEntity = EntityType.ALL;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filterCompanies = new FilteredList<>(this.addressBook.getCompanyList());
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return addressBook.hasCompany(company);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        currEntity = EntityType.PEOPLE;
    }

    @Override
    public void addPerson(Person person) {
        assert person.getParentCompany() != null : "Person that is added to Connectify must have a parent company";
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        currEntity = EntityType.PEOPLE;
    }

    @Override
    public void addCompany(Company company) {
        addressBook.addCompany(company);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        currEntity = EntityType.COMPANIES;
    }

    @Override
    public void deleteCompany(Company target) {
        addressBook.removeCompany(target);
        currEntity = EntityType.COMPANIES;
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        CollectionUtil.requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
        currEntity = EntityType.PEOPLE;
    }

    @Override
    public void setCompany(Company target, Company editedCompany) {
        CollectionUtil.requireAllNonNull(target, editedCompany);

        addressBook.setCompany(target, editedCompany);
        currEntity = EntityType.COMPANIES;
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Company> getFilteredCompanyList() {
        return filterCompanies;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        logger.info("Updating list of filtered persons");
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        currEntity = EntityType.PEOPLE;
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        logger.info("Updating list of sorted persons");
        requireNonNull(comparator);
        List<Person> sortedList = new ArrayList<>(addressBook.getPersonList());
        sortedList.sort(comparator);
        addressBook.setPersons(sortedList);
    }

    @Override
    public void updateFilteredEntityList(Predicate<Entity> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        filterCompanies.setPredicate(predicate);
        currEntity = EntityType.ALL;
    }

    @Override
    public void updateFilteredCompanyList(Predicate<Company> predicate) {
        logger.info("Updating list of filtered companies");
        requireNonNull(predicate);
        filterCompanies.setPredicate(predicate);
        currEntity = EntityType.COMPANIES;
    }

    /**
     * Returns the current entity type that is being filtered.
     */
    @Override
    public String getCurrEntity() {
        if (currEntity == EntityType.PEOPLE) {
            return "people";
        } else if (currEntity == EntityType.COMPANIES) {
            return "companies";
        } else {
            return "all";
        }
    }

    /**
     * Sets the current entity type that is being filtered.
     */
    @Override
    public void setCurrEntity(String entityType) throws InvalidEntityException {
        if (entityType.equals("people")) {
            currEntity = EntityType.PEOPLE;
        } else if (entityType.equals("companies")) {
            currEntity = EntityType.COMPANIES;
        } else if (entityType.equals("all")) {
            currEntity = EntityType.ALL;
        } else {
            throw new InvalidEntityException("Invalid entity type: " + entityType + ". Please enter either "
                    + "people, companies or all.");
        }
    }

    /**
     * Returns an ObservableList of entities that is currently being filtered.
     */
    @Override
    public ObservableList<Entity> getFilteredEntityList() {
        // Create a new ObservableList which contains all the elements from filteredCompanies and filteredPersons
        logger.info("Returning list of all entities");
        ObservableList<Entity> allEntityList = FXCollections.observableArrayList();
        allEntityList.addAll(filterCompanies);
        allEntityList.addAll(filteredPersons);
        return new FilteredList<>(allEntityList);

    }

    /**
     * Returns the number of entities that is currently being filtered.
     */
    @Override
    public Integer getNumberOfEntities() {
        return getFilteredEntityList().size();
    }

    /**
     * Returns the number of people that is currently being filtered.
     */
    @Override
    public Integer getNumberOfPeople() {
        return filteredPersons.size();
    }

    /**
     * Returns the number of companies that is currently being filtered.
     */
    @Override
    public Integer getNumberOfCompanies() {
        return filterCompanies.size();
    }

    /**
     * Returns the number of all entities in the address book.
     */
    @Override
    public Integer getNumberOfAllEntities() {
        return addressBook.getPersonList().size() + addressBook.getCompanyList().size();
    }

    @Override
    public Boolean isEmpty() {
        return getNumberOfEntities() == 0;
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
                && userPrefs.equals(otherModelManager.userPrefs);
    }

    @Override
    public String toString() {
        String msg = "There are " + getNumberOfAllEntities() + " entities in the address book.\n";
        msg += "There are " + getNumberOfPeople() + " people in the address book.\n";
        msg += "There are " + getNumberOfCompanies() + " companies in the address book.\n";
        return msg;
    }

}
