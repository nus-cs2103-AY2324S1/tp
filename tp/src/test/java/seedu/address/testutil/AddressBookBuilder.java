package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Member} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withMember(Member member) {
        addressBook.addMember(member);
        return this;
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
