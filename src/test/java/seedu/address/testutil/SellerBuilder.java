package seedu.address.testutil;


import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.model.util.SampleDataUtil;

/**
 * Builds a seller.
 */
public class SellerBuilder extends PersonBuilder {

    public static final String DEFAULT_SELLING_ADDRESS = "Placeholder Street, Singapore";
    public static final String DEFAULT_INFO = "Placeholder house";

    private Address sellingAddress;
    private HouseInfo houseInfo;

    /**
     * Creates a {@code SellerBuilder} with the default details.
     */
    public SellerBuilder() {
        super();
        sellingAddress = new Address(DEFAULT_SELLING_ADDRESS);
        houseInfo = new HouseInfo(DEFAULT_INFO);
    }

    /**
     * Initializes the SellerBuilder with the data of {@code buyerToCopy}.
     */
    public SellerBuilder(Seller sellerToCopy) {
        super(sellerToCopy);
        sellingAddress = sellerToCopy.getSellingAddress();
        houseInfo = sellerToCopy.getHouseInfo();
    }

    /**
     * Sets the {@code Name} of the {@code Seller} that we are building.
     */
    @Override
    public SellerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    @Override
    public SellerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Seller} that we are building.
     */
    @Override
    public SellerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Seller} that we are building.
     */
    @Override
    public SellerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Seller} that we are building.
     */
    @Override
    public SellerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code SellingAddress} of the {@code Seller} that we are building.
     */
    public SellerBuilder withSellingAddress(String sellingAddress) {
        this.sellingAddress = new Address(sellingAddress);
        return this;
    }

    /**
     * Sets the {@code HouseInfo} of the {@code Seller} that we are building.
     */
    public SellerBuilder withInfo(String info) {
        this.houseInfo = new HouseInfo(info);
        return this;
    }

    @Override
    public Seller build() {
        return new Seller(super.name, super.phone, super.email,
                super.address, sellingAddress, houseInfo, super.tags);
    }
}
