package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.property.Property;
import seedu.address.model.property.UniquePropertyList;

/**
 * Wraps all data at the PropertyMatch level
 * Duplicates are not allowed (by .isSamePerson comparison)
 * Duplicates are not allowed (by .isSameProperty comparison)
 */
public class PropertyBook implements ReadOnlyPropertyBook {

    private final UniquePropertyList properties;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        properties = new UniquePropertyList();
    }

    public PropertyBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public PropertyBook(ReadOnlyPropertyBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the property list with {@code properties}.
     * {@code properties} must not contain duplicate properties.
     */
    public void setProperties(List<Property> properties) {
        this.properties.setProperties(properties);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPropertyBook newData) {
        requireNonNull(newData);

        setProperties(newData.getPropertyList());
    }

    //// person-level operations

    /**
     * Returns true if a property with the same identity as {@code property} exists in the address book.
     */
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return properties.contains(property);
    }

    /**
     * Adds a property to the address book.
     * The property must not already exist in the address book.
     */
    public void addProperty(Property p) {
        properties.add(p);
    }


    /**
     * Replaces the given person {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the address book.
     * The property identity of {@code editedProperty} must not
     * be the same as another existing property in the address book.
     */
    public void setProperty(Property target, Property editedProperty) {
        requireNonNull(editedProperty);

        properties.setProperty(target, editedProperty);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeProperty(Property key) {
        properties.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("properties", properties)
                .toString();
    }

    @Override
    public ObservableList<Property> getPropertyList() {
        return properties.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PropertyBook)) {
            return false;
        }

        PropertyBook otherPropertyBook = (PropertyBook) other;
        return properties.equals(otherPropertyBook.properties) && properties.equals(otherPropertyBook.properties);
    }

    @Override
    public int hashCode() {
        return properties.hashCode();
    }
}
