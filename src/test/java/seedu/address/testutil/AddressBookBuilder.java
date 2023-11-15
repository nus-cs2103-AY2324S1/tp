package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private final AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Seller} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withSeller(Seller seller) {
        addressBook.addSeller(seller);
        return this;
    }
    /**
     * Adds a new {@code Buyer} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withBuyer(Buyer buyer) {
        addressBook.addBuyer(buyer);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
