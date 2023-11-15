package seedu.address.model.displayable.seller;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import javafx.scene.layout.Region;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Person;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.Priority;
import seedu.address.model.tag.Tag;
import seedu.address.ui.SellerCard;
import seedu.address.ui.UiPart;


/**
 * Represents a Seller in the address book.
 */
public class Seller extends Person {
    private final Address sellingAddress;
    private final HouseInfo houseInfo;

    /**
     * Constructs a seller instance.
     * Every field must be present and not null (super class does these checks too)
     *
     * @param name           name of the seller.
     * @param phone          phone number of the seller.
     * @param email          email of the seller.
     * @param address        the home address of the seller.
     * @param sellingAddress the address that the seller is listing.
     * @param houseInfo      info about the listing.
     * @param tags           tags of the seller.
     */
    public Seller(Name name, Phone phone, Email email, Address address, Address sellingAddress,
                  HouseInfo houseInfo, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(sellingAddress, houseInfo);
        this.sellingAddress = sellingAddress;
        this.houseInfo = houseInfo;
    }

    /**
     * Constructs a seller instance.
     * NOTE: This overloaded constructor is temporal, to be refactored soon. Refer to Person.java
     * for full explanation.
     * Every field must be present and not null (super class does these checks too)
     *
     * @param name           name of the seller.
     * @param phone          phone number of the seller.
     * @param email          email of the seller.
     * @param address        the home address of the seller.
     * @param sellingAddress the address that the seller is listing.
     * @param houseInfo      info about the listing.
     * @param tags           tags of the seller.
     * @param priority       priority level of the seller.
     */
    public Seller(Name name, Phone phone, Email email, Address address, Address sellingAddress,
                  HouseInfo houseInfo, Set<Tag> tags, Priority priority) {
        super(name, phone, email, address, tags, priority);
        requireAllNonNull(sellingAddress, houseInfo);
        this.sellingAddress = sellingAddress;
        this.houseInfo = houseInfo;
    }

    public Address getSellingAddress() {
        return this.sellingAddress;
    }

    public HouseInfo getHouseInfo() {
        return houseInfo;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Seller)) {
            return false;
        }

        Seller otherSeller = (Seller) other;
        return this.equalsHelper(otherSeller)
                && sellingAddress.equals(otherSeller.sellingAddress)
                && houseInfo.equals(otherSeller.houseInfo);
    }
    @Override
    public String toString() {
        return toStringBuild()
                .add("selling address", sellingAddress)
                .add("house info", houseInfo)
                .toString();
    }
    @Override
    public UiPart<Region> display(int displayIndex) {
        return new SellerCard(this, displayIndex);
    }
}
