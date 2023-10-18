package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.address.model.person.Person;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class SerializablePredicateTest {
    @Test
    public void equals() {
        Predicate<Person> predicate = unused -> true;
        SerializablePredicate serializablePredicate = new SerializablePredicate(predicate);
        assertEquals(serializablePredicate, serializablePredicate);
        assertNotEquals(serializablePredicate, predicate);
        assertEquals(serializablePredicate, new SerializablePredicate(predicate));
    }

    @Test
    public void toStringMethod_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> new SerializablePredicate().toString());
    }
}
