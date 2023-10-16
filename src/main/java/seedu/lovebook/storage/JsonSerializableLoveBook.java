package seedu.lovebook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.person.Date;

/**
 * An Immutable LoveBook that is serializable to JSON format.
 */
@JsonRootName(value = "LoveBook")
class JsonSerializableLoveBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate date(s).";

    private final List<JsonAdaptedDate> dates = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLoveBook} with the given dates.
     */
    @JsonCreator
    public JsonSerializableLoveBook(@JsonProperty("dates") List<JsonAdaptedDate> dates) {
        this.dates.addAll(dates);
    }

    /**
     * Converts a given {@code ReadOnlyLoveBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLoveBook}.
     */
    public JsonSerializableLoveBook(ReadOnlyLoveBook source) {
        dates.addAll(source.getPersonList().stream().map(JsonAdaptedDate::new).collect(Collectors.toList()));
    }

    /**
     * Converts this lovebook book into the model's {@code LoveBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LoveBook toModelType() throws IllegalValueException {
        LoveBook loveBook = new LoveBook();
        for (JsonAdaptedDate jsonAdaptedDate : dates) {
            Date date = jsonAdaptedDate.toModelType();
            if (loveBook.hasPerson(date)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            loveBook.addPerson(date);
        }
        return loveBook;
    }

}
