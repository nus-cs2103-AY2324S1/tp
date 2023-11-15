//@@author ruiyangzh-unused
//This class was meant to be representative of a house in the final application. Not enough time was available
//to implement the automatching buyers-with-houses feature, so we decided to cut this class and
//focus on core features instead.
package seedu.address.model.displayable.house;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.layout.Region;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Displayable;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Price;
import seedu.address.model.tag.Tag;
import seedu.address.ui.HouseCard;
import seedu.address.ui.UiPart;

/**
 * Represents a house object that can be displayed.
 */
public class House implements Displayable {
    private final Name name;
    private final Price price;
    private final Address address;
    private final HouseInfo houseInfo;
    private final Set<Tag> tags = new HashSet<>();
    /**
     * Constructs an instance of House.
     */
    public House(Name name, Price price, Address address, HouseInfo houseInfo, Set<Tag> tags) {
        this.name = name;
        this.price = price;
        this.address = address;
        this.houseInfo = houseInfo;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return this.name;
    }
    public Price getPrice() {
        return this.price;
    }
    public Address getAddress() {
        return this.address;
    }
    public HouseInfo getHouseInfo() {
        return this.houseInfo;
    }
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof House)) {
            return false;
        }

        House otherHouse = (House) other;
        return this.name.equals(otherHouse.name)
                && this.price.equals(otherHouse.price)
                && this.address.equals(otherHouse.address)
                && this.houseInfo.equals(otherHouse.houseInfo)
                && this.tags.equals(otherHouse.tags);
    }
    public boolean isSameHouse(House h) {
        return h != null && this.address.equals(h.address);
    }
    @Override
    public boolean isSameDisplayable(Displayable d) {
        return ((d instanceof House)) && isSameHouse((House) d);
    }
    @Override
    public UiPart<Region> display(int displayIndex) {
        return new HouseCard(this, displayIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("price", price)
                .add("address", address)
                .add("info", houseInfo)
                .add("tags", tags)
                .toString();
    }
    @Override
    public boolean isSimilarDisplayable(Displayable displayable) {
        if (displayable == this) {
            return true;
        }
        return getName().isSameNameFuzzyMatch(displayable.getName());
    }
}
