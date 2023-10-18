package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMusician.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMusicians.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.musician.Email;
import seedu.address.model.musician.Name;
import seedu.address.model.musician.Phone;

public class JsonAdaptedMusicianTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_INSTRUMENT = "friend";
    private static final String INVALID_GENRE = "friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedInstrument> VALID_INSTRUMENTS = BENSON.getInstruments().stream()
            .map(JsonAdaptedInstrument::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedGenre> VALID_GENRES = BENSON.getGenres().stream()
            .map(JsonAdaptedGenre::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMusicianDetails_returnsMusician() throws Exception {
        JsonAdaptedMusician musician = new JsonAdaptedMusician(BENSON);
        assertEquals(BENSON, musician.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMusician musician =
                new JsonAdaptedMusician(INVALID_NAME,
                        VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_INSTRUMENTS, VALID_GENRES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, musician::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMusician musician = new JsonAdaptedMusician(null,
                VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_INSTRUMENTS, VALID_GENRES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, musician::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedMusician musician =
                new JsonAdaptedMusician(VALID_NAME, INVALID_PHONE,
                        VALID_EMAIL, VALID_TAGS, VALID_INSTRUMENTS, VALID_GENRES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, musician::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedMusician musician = new JsonAdaptedMusician(VALID_NAME, null,
                VALID_EMAIL, VALID_TAGS, VALID_INSTRUMENTS, VALID_GENRES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, musician::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedMusician musician =
                new JsonAdaptedMusician(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_TAGS, VALID_INSTRUMENTS, VALID_GENRES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, musician::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedMusician musician = new JsonAdaptedMusician(VALID_NAME, VALID_PHONE, null,
                VALID_TAGS, VALID_INSTRUMENTS, VALID_GENRES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, musician::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedMusician musician =
                new JsonAdaptedMusician(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        invalidTags, VALID_INSTRUMENTS, VALID_GENRES);
        assertThrows(IllegalValueException.class, musician::toModelType);
    }

    @Test
    public void toModelType_invalidInstruments_throwsIllegalValueException() {
        List<JsonAdaptedInstrument> invalidInstruments = new ArrayList<>(VALID_INSTRUMENTS);
        invalidInstruments.add(new JsonAdaptedInstrument(INVALID_INSTRUMENT));
        JsonAdaptedMusician musician =
                new JsonAdaptedMusician(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TAGS, invalidInstruments, VALID_GENRES);
        assertThrows(IllegalValueException.class, musician::toModelType);
    }

    @Test
    public void toModelType_invalidGenres_throwsIllegalValueException() {
        List<JsonAdaptedGenre> invalidGenres = new ArrayList<>(VALID_GENRES);
        invalidGenres.add(new JsonAdaptedGenre(INVALID_GENRE));
        JsonAdaptedMusician musician =
                new JsonAdaptedMusician(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TAGS, VALID_INSTRUMENTS, invalidGenres);
        assertThrows(IllegalValueException.class, musician::toModelType);
    }
}
