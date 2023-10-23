package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Optional;

public class AddressBookManager {
    private HashMap<String, AddressBook> addressBooks;
    private AddressBook currentAddressBook;
    private String currentCourseCode;

    public AddressBookManager() {
        this.addressBooks = new HashMap<>();
        this.currentCourseCode = "";
    }

    public AddressBookManager(HashMap<String, AddressBook> addressBooks, String currentCourseCode) {
        this.addressBooks = addressBooks;
        this.currentCourseCode = currentCourseCode;
    }

    // TODO: Check if this function is necessary
    public Optional<AddressBook> getAddressBook(String courseCode) {
        requireNonNull(courseCode);
        return this.addressBooks.get(courseCode.toUpperCase()) == null
                ? Optional.empty()
                : Optional.of(this.addressBooks.get(courseCode.toUpperCase()));
    }

    public AddressBook getActiveAddressBook() {
        // if (this.currentAddressBook == null) {
        //     throw new IllegalArgumentException("No active address book");
        // }

        return this.currentAddressBook;
    }

    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        requireNonNull(addressBook);
        this.currentAddressBook = new AddressBook(addressBook.getCourseCode(), addressBook);
    }

    public void setActiveAddressBook(String courseCode) {
        requireNonNull(courseCode);
        this.currentCourseCode = courseCode.toUpperCase();

        if (!this.addressBooks.containsKey(courseCode.toUpperCase())) {
            throw new IllegalArgumentException("Address book does not exist");
        }

        this.currentAddressBook = this.addressBooks.get(courseCode.toUpperCase());
    }

    public boolean hasAddressBook(String courseCode) {
        requireNonNull(courseCode);
        return this.addressBooks.containsKey(courseCode.toUpperCase());
    }

    public void addAddressBook(String courseCode, AddressBook addressBook) {
        requireAllNonNull(courseCode, addressBook);

        if (this.addressBooks.containsKey(courseCode.toUpperCase())) {
            throw new IllegalArgumentException("Address book already exists");
        }

        if (!courseCode.equalsIgnoreCase(addressBook.getCourseCode())) {
            throw new IllegalArgumentException("Course code does not match");
        }

        this.addressBooks.put(courseCode.toUpperCase(), addressBook);
    }

    public void removeAddressBook(String courseCode) {
        requireNonNull(courseCode);

        if (!this.addressBooks.containsKey(courseCode.toUpperCase())) {
            throw new IllegalArgumentException("Address book does not exist");
        }

        this.addressBooks.remove(courseCode.toUpperCase());
    }
}
