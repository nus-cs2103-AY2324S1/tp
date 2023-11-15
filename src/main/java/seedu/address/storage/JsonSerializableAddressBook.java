package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "rtpm-addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE = "list contains duplicate(s).";

    private final List<JsonAdaptedBuyer> buyers = new ArrayList<>();
    private final List<JsonAdaptedSeller> sellers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given buyers and sellers.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("buyers") List<? extends JsonAdaptedBuyer> buyers,
                                       @JsonProperty("sellers") List<? extends JsonAdaptedSeller> sellers) {
        this.buyers.addAll(buyers);
        this.sellers.addAll(sellers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        sellers.addAll(source.getSellerList().stream().map(JsonAdaptedSeller::new).collect(Collectors.toList()));
        buyers.addAll(source.getBuyerList().stream().map(JsonAdaptedBuyer::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedBuyer jsonAdaptedBuyer : buyers) {
            Buyer buyer = jsonAdaptedBuyer.toModelType();
            if (addressBook.hasBuyer(buyer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE);
            }
            addressBook.addBuyer(buyer);
        }
        for (JsonAdaptedSeller jsonAdaptedSeller : sellers) {
            Seller seller = jsonAdaptedSeller.toModelType();
            if (addressBook.hasSeller(seller)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE);
            }
            addressBook.addSeller(seller);
        }
        return addressBook;
    }

}
