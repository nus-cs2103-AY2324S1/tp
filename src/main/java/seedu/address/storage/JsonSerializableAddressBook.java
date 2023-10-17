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
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_MUSICIAN = "Persons list contains duplicate musician(s).";
    public static final String MESSAGE_DUPLICATE_BAND = "Band list contains duplicate band(s).";

    private final List<JsonAdaptedMusician> musicians = new ArrayList<>();
    private final List<JsonAdaptedBand> bands = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given musicians.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("musicians") List<JsonAdaptedMusician> musicians, @JsonProperty("bands") List<JsonAdaptedBand> bands) {
        this.musicians.addAll(musicians);
        this.bands.addAll(bands);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        musicians.addAll(source.getMusicianList().stream().map(JsonAdaptedMusician::new).collect(Collectors.toList()));
        bands.addAll(source.getBandList().stream().map(JsonAdaptedBand::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedMusician jsonAdaptedMusician : musicians) {
            Musician musician = jsonAdaptedMusician.toModelType();
            if (addressBook.hasMusician(musician)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MUSICIAN);
            }
            addressBook.addMusician(musician);
        }
        for (JsonAdaptedBand jsonAdaptedBand : bands) {
            Band band = jsonAdaptedBand.toModelType();
            if (addressBook.hasBand(band)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BAND);
            }
            addressBook.addBand(band);
        }

        return addressBook;
    }

}
