package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.buyer.Buyer;

/**
 * Jackson-friendly version of {@link Buyer}.
 */
public class JsonAdaptedBuyer extends JsonAdaptedPerson {
    private final String info;
    /**
     * Constructs a {@code JsonAdaptedBuyer} with the given buyer details.
     */
    @JsonCreator
    public JsonAdaptedBuyer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("info") String info,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("priority") String priority) {
        super(name, phone, email, address, tags, priority);
        this.info = info;
    }

    /**
     * Converts a given {@code Buyer} into this class for Jackson use.
     */
    public JsonAdaptedBuyer(Buyer source) {
        super(source);
        this.info = source.getHouseInfo().toString();
    }

    /**
     * Converts this Jackson-friendly adapted seller object into the model's {@code Buyer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted seller.
     */
    public Buyer toModelType() throws IllegalValueException {
        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    HouseInfo.class.getSimpleName()));
        }
        if (!HouseInfo.isValidHouseInfo(info)) {
            throw new IllegalValueException(HouseInfo.MESSAGE_CONSTRAINTS);
        }
        HouseInfo houseInfoModel = new HouseInfo(info);
        return new Buyer(getName(), getPhone(), getEmail(), getAddress(), houseInfoModel, getTags(), getPriority());
    }
}
