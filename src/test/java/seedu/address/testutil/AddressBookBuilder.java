package seedu.address.testutil;

import seedu.address.model.WellNus;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withStudent("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private WellNus wellNus;

    public AddressBookBuilder() {
        wellNus = new WellNus();
    }

    public AddressBookBuilder(WellNus wellNus) {
        this.wellNus = wellNus;
    }

    /**
     * Adds a new {@code Student} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withStudent(Student student) {
        wellNus.addStudent(student);
        return this;
    }

    public WellNus build() {
        return wellNus;
    }
}
