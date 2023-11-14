package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Property objects.
 */
public class PropertyBuilder {

    public static final String DEFAULT_NAME = "Light House";
    public static final String DEFAULT_ADDRESS = "25 Prince George's Park, Singapore 118424";
    public static final String DEFAULT_PHONE = "85462384";
    public static final String DEFAULT_PRICE = "1230000";

    private PropName name;
    private PropAddress address;
    private PropPhone phone;
    private Price price;
    private Set<Tag> tags;

    /**
     * Creates a {@code PropertyrBuilder} with the default details.
     */
    public PropertyBuilder() {
        name = new PropName(DEFAULT_NAME);
        address = new PropAddress(DEFAULT_ADDRESS);
        phone = new PropPhone(DEFAULT_PHONE);
        price = new Price(DEFAULT_PRICE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PropertyBuilder with the data of {@code propertyToCopy}.
     */
    public PropertyBuilder(Property propertyToCopy) {
        name = propertyToCopy.getName();
        address = propertyToCopy.getAddress();
        phone = propertyToCopy.getPhone();
        price = propertyToCopy.getPrice();
        tags = new HashSet<>(propertyToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Property} that we are building.
     */
    public PropertyBuilder withName(String name) {
        this.name = new PropName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Property} that we are building.
     */
    public PropertyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPrice(String price) {
        this.price = new Price(price.trim());
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPhone(String phone) {
        this.phone = new PropPhone(phone);
        return this;
    }

    /**
     * Sets the {@code PropAddress} of the {@code Property} that we are building.
     */
    public PropertyBuilder withAddress(String address) {
        this.address = new PropAddress(address);
        return this;
    }

    public Property build() {
        return new Property(name, address, phone, price, tags);
    }

}
