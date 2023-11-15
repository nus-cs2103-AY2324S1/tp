package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.seller.Seller;
/**
 * Jackson-friendly version of {@link Seller}.
 */
public class JsonAdaptedSeller extends JsonAdaptedPerson {
    private final String info;
    private final String sellingAddress;
    /**
     * Constructs a {@code JsonAdaptedSeller} with the given seller details.
     */
    @JsonCreator
    public JsonAdaptedSeller(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email, @JsonProperty("address") String address,
                            @JsonProperty("sellingAddress") String sellingAddress,
                            @JsonProperty("info") String info,
                            @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("priority") String priority) {
        super(name, phone, email, address, tags, priority);
        this.info = info;
        this.sellingAddress = sellingAddress;
    }
    /**
     * Converts a given {@code Seller} into this class for Jackson use.
     */
    public JsonAdaptedSeller(Seller source) {
        super(source);
        this.sellingAddress = source.getSellingAddress().value;
        this.info = source.getHouseInfo().toString();
    }

    /**
     * Converts this Jackson-friendly adapted seller object into the model's {@code Seller} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted seller.
     */
    public Seller toModelType() throws IllegalValueException {
        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    HouseInfo.class.getSimpleName()));
        }
        if (!HouseInfo.isValidHouseInfo(info)) {
            throw new IllegalValueException(HouseInfo.MESSAGE_CONSTRAINTS);
        }
        HouseInfo houseInfoModel = new HouseInfo(info);
        if (sellingAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(sellingAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        Address sellerAddressModel = new Address(sellingAddress);
        return new Seller(getName(), getPhone(), getEmail(), getAddress(), sellerAddressModel,
                houseInfoModel, getTags(), getPriority());
    }
}
