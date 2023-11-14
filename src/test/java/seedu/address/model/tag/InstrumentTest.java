package seedu.address.model.tag;

import static seedu.address.model.tag.Instrument.VALID_INSTRUMENTS;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class InstrumentTest extends TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Instrument(null));
    }

    @Test
    public void constructor_invalidInstrumentName_throwsIllegalArgumentException() {
        String invalidInstrumentName = "foo";
        if (!VALID_INSTRUMENTS.contains(invalidInstrumentName)) {
            assertThrows(IllegalArgumentException.class, () -> new Genre(invalidInstrumentName));
        }
    }
}
