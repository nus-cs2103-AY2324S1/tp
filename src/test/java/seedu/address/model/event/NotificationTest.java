package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NotificationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Notification(null, ""));
    }

    @Test
    public void constructor_null_throwsNullPointerException2() {
        assertThrows(NullPointerException.class, () -> new Notification("", null));
    }
    @Test
    public void constructor_success() {
        assertDoesNotThrow(() -> new Notification("title", "description"));
    }

    @Test
    public void getTitle_success() {
        Notification notification = new Notification("title", "description");
        assertEquals(notification.getTitle(), "title");
    }

    @Test
    public void getDescription_success() {
        Notification notification = new Notification("title", "description");
        assertEquals(notification.getDescription(), "description");
    }

}
