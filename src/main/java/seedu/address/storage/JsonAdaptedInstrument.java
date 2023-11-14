package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Instrument;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Instrument}.
 */
public class JsonAdaptedInstrument extends JsonAdaptedTag {

    @JsonCreator
    public JsonAdaptedInstrument(String instrument) {
        super(instrument);
    }

    public JsonAdaptedInstrument(Tag source) {
        super(source);
    }

    @Override
    public Tag toModelType() throws IllegalValueException {
        if (!Instrument.isValidInstrumentName(super.getTagName())) {
            throw new IllegalValueException(Instrument.MESSAGE_CONSTRAINTS);
        }
        return new Instrument(super.getTagName());
    }


}
