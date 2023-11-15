package seedu.address.model.displayable.buyer;

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
import seedu.address.ui.BuyerCard;
import seedu.address.ui.UiPart;

/**
 * Represents a client looking to buy a house in the address book.
 */
public class Buyer extends Person {

    private final HouseInfo houseInfo;

    /**
     * Constructs a Buyer instance.
     *
     * @param name Name of the buyer.
     * @param phone Phone number of the buyer.
     * @param email Email of the buyer.
     * @param address Home address of the buyer.
     * @param houseInfo Information on the house that the buyer is interested in.
     * @param tags Optional tags.
     */
    public Buyer(Name name, Phone phone, Email email, Address address,
                 HouseInfo houseInfo, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(houseInfo);
        this.houseInfo = houseInfo;
    }

    /**
     * Constructs a Buyer instance with Priority.
     * NOTE: This is a temporal method, to be refactored soon. Refer to explanation in
     * Person.java.
     *
     * @param name Name of the buyer.
     * @param phone Phone number of the buyer.
     * @param email Email of the buyer.
     * @param address Home address of the buyer.
     * @param houseInfo Information on the house that the buyer is interested in.
     * @param tags Optional tags.
     * @param priority Priority level of the buyer.
     */
    public Buyer(Name name, Phone phone, Email email, Address address,
                 HouseInfo houseInfo, Set<Tag> tags, Priority priority) {
        super(name, phone, email, address, tags, priority);
        requireAllNonNull(houseInfo);
        this.houseInfo = houseInfo;
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
        if (!(other instanceof Buyer)) {
            return false;
        }

        Buyer otherBuyer = (Buyer) other;
        return this.equalsHelper(otherBuyer)
                && houseInfo.equals(otherBuyer.houseInfo);
    }
    @Override
    public String toString() {
        return toStringBuild()
                .add("house info", houseInfo)
                .toString();
    }
    @Override
    public UiPart<Region> display(int displayIndex) {
        return new BuyerCard(this, displayIndex);
    }
}
