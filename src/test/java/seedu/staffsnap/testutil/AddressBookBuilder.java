package seedu.staffsnap.testutil;

import seedu.staffsnap.model.AddressBook;
import seedu.staffsnap.model.applicant.Applicant;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withApplicant("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Applicant} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withApplicant(Applicant applicant) {
        addressBook.addApplicant(applicant);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
