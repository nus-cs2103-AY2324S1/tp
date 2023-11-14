package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.SameCustomerPredicate;
import seedu.address.model.property.Property;
import seedu.address.model.property.SamePropertyPredicate;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final PropertyBook propertyBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;

    private final FilteredList<Property> filteredProperties;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook,
            ReadOnlyPropertyBook propertyBook, ReadOnlyUserPrefs userPrefs) {

        requireAllNonNull(addressBook, propertyBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", property book:" + propertyBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.propertyBook = new PropertyBook(propertyBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.addressBook.getCustomerList());
        filteredProperties = new FilteredList<>(this.propertyBook.getPropertyList());
    }

    public ModelManager() {
        this(new AddressBook(), new PropertyBook(), new UserPrefs());
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
    public Path getPropertyBookFilePath() {
        return userPrefs.getPropertyBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setPropertyBookFilePath(Path propertyBookFilePath) {
        requireNonNull(propertyBookFilePath);
        userPrefs.setAddressBookFilePath(propertyBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public void setPropertyBook(ReadOnlyPropertyBook propertyBook) {
        this.propertyBook.resetData(propertyBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public ReadOnlyPropertyBook getPropertyBook() {
        return propertyBook;
    }

    @Override
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return addressBook.hasCustomer(customer);
    }

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return propertyBook.hasProperty(property);
    }

    @Override
    public void deleteCustomer(Customer target) {
        addressBook.removeCustomer(target);
    }


    @Override
    public void deleteProperty(Property target) {
        propertyBook.removeProperty(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        addressBook.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void addProperty(Property property) {
        propertyBook.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        addressBook.setCustomer(target, editedCustomer);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);

        propertyBook.setProperty(target, editedProperty);
    }

    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    @Override
    public void updateMatchedCustomerList(Customer targetCustomer, Predicate<Property> predicate) {
        requireAllNonNull(targetCustomer, predicate);
        Predicate<Customer> custPredicate = new SameCustomerPredicate(targetCustomer);
        filteredCustomers.setPredicate(custPredicate);
        filteredProperties.setPredicate(predicate);
    }

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Property} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return filteredProperties;
    }

    @Override
    public void updateFilteredPropertyList(Predicate<Property> predicate) {
        requireNonNull(predicate);
        filteredProperties.setPredicate(predicate);
    }

    @Override
    public void updateMatchedPropertyList(Property targetProperty, Predicate<Customer> predicate) {
        requireAllNonNull(targetProperty, predicate);
        Predicate<Property> propPredicate = new SamePropertyPredicate(targetProperty);
        filteredProperties.setPredicate(propPredicate);
        filteredCustomers.setPredicate(predicate);
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
                && propertyBook.equals(otherModelManager.propertyBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredCustomers.equals(otherModelManager.filteredCustomers)
                && filteredProperties.equals(otherModelManager.filteredProperties);
    }

}
