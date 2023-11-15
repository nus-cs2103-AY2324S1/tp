package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;
import seedu.address.model.item.review.ItemReview;

/**
 * Jackson-friendly version of {@link Item}.
 */
public class JsonAdaptedItem {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    private final String itemName;
    private final String price;
    private JsonAdaptedItemReview itemReview;

    /**
     * Constructs a {@code JsonAdaptedItem} with the given item details.
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("itemName") String itemName,
                           @JsonProperty("price") String price,
                           @JsonProperty("review") JsonAdaptedItemReview itemReview) {
        this.itemName = itemName;
        this.price = price;
        this.itemReview = itemReview;
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        itemName = source.getName().fullName;
        price = source.getPrice().price;
        itemReview = new JsonAdaptedItemReview(source.getItemReview());
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public Item toModelType() throws IllegalValueException {
        if (itemName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItemName.class.getSimpleName()));
        }
        if (!ItemName.isValidItemName(itemName)) {
            throw new IllegalValueException(ItemName.MESSAGE_CONSTRAINTS);
        }
        final ItemName modelName = new ItemName(itemName);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        ItemReview modelItemReview = null;

        if (itemReview != null) {
            modelItemReview = itemReview.toModelType();
        }

        if (modelItemReview != null) {
            return new Item(modelName, modelPrice, modelItemReview);
        } else {
            return new Item(modelName, modelPrice);
        }
    }
}
