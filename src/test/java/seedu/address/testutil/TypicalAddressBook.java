package seedu.address.testutil;

import static seedu.address.testutil.TypicalBuyers.getTypicalBuyers;
import static seedu.address.testutil.TypicalSellers.getTypicalSellers;

import seedu.address.model.AddressBook;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;

/**
 * Represents a typical addressbook.
 */
public class TypicalAddressBook {
    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Buyer buyer : getTypicalBuyers()) {
            ab.addBuyer(buyer);
        }
        for (Seller seller : getTypicalSellers()) {
            ab.addSeller(seller);
        }
        return ab;
    }
}
