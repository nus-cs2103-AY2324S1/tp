package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class NoDateTest {

    @Test
    public void getDate() {
        NoDate nd = new NoDate();
        assertEquals(nd.getDate(), Optional.empty());
    }

    @Test
    public void equals() {
        NoDate nd1 = new NoDate();
        NoDate nd2 = new NoDate();
        Date d2 = new IsoDate(LocalDateTime.now());

        assertEquals(nd1, nd2);
        assertNotEquals(nd1, d2);
        assertNotEquals(nd1, null);
    }

    @Test
    public void toSaveDate() {
        NoDate nd = new NoDate();
        assertNull(nd.toSaveData());
    }

    @Test
    public void toStringTest() {
        NoDate nd = new NoDate();
        assertEquals(nd.toString(), "");
    }

    @Test
    public void hashCodeTest() {
        NoDate nd = new NoDate();
        assertEquals(0, nd.hashCode());
    }

    @Test
    public void compareToTest() {
        NoDate nd = new NoDate();
        assertEquals(0, nd.compareTo(new NoDate()));
        assertEquals(0, nd.compareTo(new IsoDate(LocalDateTime.now())));
    }
}
