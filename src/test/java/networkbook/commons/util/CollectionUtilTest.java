package networkbook.commons.util;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;
import static networkbook.testutil.Assert.assertThrowsAssertionError;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CollectionUtilTest {
    @Test
    public void requireAllNonNullVarargs() {
        // no arguments
        assertAssertionErrorNotThrown();

        // any non-empty argument list
        assertAssertionErrorNotThrown(new Object(), new Object());
        assertAssertionErrorNotThrown("test");
        assertAssertionErrorNotThrown("");

        // argument lists with just one null at the beginning
        assertAssertionErrorThrown((Object) null);
        assertAssertionErrorThrown(null, "", new Object());
        assertAssertionErrorThrown(null, new Object(), new Object());

        // argument lists with nulls in the middle
        assertAssertionErrorThrown(new Object(), null, null, "test");
        assertAssertionErrorThrown("", null, new Object());

        // argument lists with one null as the last argument
        assertAssertionErrorThrown("", new Object(), null);
        assertAssertionErrorThrown(new Object(), new Object(), null);

        // null reference
        assertAssertionErrorThrown((Object[]) null);

        // confirms nulls inside lists in the argument list are not considered
        List<Object> containingNull = Arrays.asList((Object) null);
        assertAssertionErrorNotThrown(containingNull, new Object());
    }

    @Test
    public void requireAllNonNullCollection() {
        // lists containing nulls in the front
        assertAssertionErrorThrown(Arrays.asList((Object) null));
        assertAssertionErrorThrown(Arrays.asList(null, new Object(), ""));

        // lists containing nulls in the middle
        assertAssertionErrorThrown(Arrays.asList("spam", null, new Object()));
        assertAssertionErrorThrown(Arrays.asList("spam", null, "eggs", null, new Object()));

        // lists containing nulls at the end
        assertAssertionErrorThrown(Arrays.asList("spam", new Object(), null));
        assertAssertionErrorThrown(Arrays.asList(new Object(), null));

        // null reference
        assertAssertionErrorThrown((Collection<Object>) null);

        // empty list
        assertAssertionErrorNotThrown(Collections.emptyList());

        // list with all non-null elements
        assertAssertionErrorNotThrown(Arrays.asList(new Object(), "ham", Integer.valueOf(1)));
        assertAssertionErrorNotThrown(Arrays.asList(new Object()));

        // confirms nulls inside nested lists are not considered
        List<Object> containingNull = Arrays.asList((Object) null);
        assertAssertionErrorNotThrown(Arrays.asList(containingNull, new Object()));
    }

    @Test
    public void isAnyNonNull() {
        assertFalse(CollectionUtil.isAnyNonNull());
        assertFalse(CollectionUtil.isAnyNonNull((Object) null));
        assertFalse(CollectionUtil.isAnyNonNull((Object[]) null));
        assertTrue(CollectionUtil.isAnyNonNull(new Object()));
        assertTrue(CollectionUtil.isAnyNonNull(new Object(), null));
    }

    /**
     * Asserts that {@code CollectionUtil#requireAllNonNull(Object...)} throw {@code NullPointerException}
     * if {@code objects} or any element of {@code objects} is null.
     */
    private void assertAssertionErrorThrown(Object... objects) {
        assertThrowsAssertionError(() -> requireAllNonNull(objects));
    }

    /**
     * Asserts that {@code CollectionUtil#requireAllNonNull(Collection<?>)} throw {@code NullPointerException}
     * if {@code collection} or any element of {@code collection} is null.
     */
    private void assertAssertionErrorThrown(Collection<?> collection) {
        assertThrowsAssertionError(() -> requireAllNonNull(collection));
    }

    private void assertAssertionErrorNotThrown(Object... objects) {
        requireAllNonNull(objects);
    }

    private void assertAssertionErrorNotThrown(Collection<?> collection) {
        requireAllNonNull(collection);
    }
}
