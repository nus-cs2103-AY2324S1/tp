package seedu.address.model.band;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBands.ACE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BandBuilder;

public class BandTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Band band = new BandBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> band.getGenres().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Band.class.getCanonicalName() + "{name=" + ACE.getName()
                + ", genres=" + ACE.getGenres() + ", musicians=" + ACE.getMusicians() + "}";
        assertEquals(expected, ACE.toString());
    }
}
