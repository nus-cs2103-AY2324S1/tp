package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void constructor_success() {
        assertDoesNotThrow(() -> new Event("title", "description", LocalDateTime.MIN));
    }

    @Test
    void addMember_validMember_success() {
        LocalDateTime now = LocalDateTime.now();
        Event event = new Event("title", "description", LocalDateTime.MIN);
    }

    @Test
    void addReminder_validReminder_success() {
        assertDoesNotThrow(() -> new Event("title", "description", LocalDateTime.MIN));
    }

    @Test
    void getNotificationAtTime_noNotification_success() {
        LocalDateTime eventTime = LocalDateTime.MAX;
        Event event = new Event("title", "description", eventTime);
        assertTrue(event.getNotificationAtTime(eventTime.minusHours(1)).isEmpty());
        assertTrue(event.getNotificationAtTime(eventTime.minusHours(5)).isEmpty());
        assertTrue(event.getNotificationAtTime(eventTime.minusHours(10)).isEmpty());
    }

    @Test
    void getNotificationAtTime_hasNotification_success() {
        LocalDateTime eventTime = LocalDateTime.MAX;
        Duration reminder = Duration.ofDays(1);
        Event event = new Event("title", "description", eventTime);
        event.addReminder(reminder);
        assertTrue(event.getNotificationAtTime(eventTime.minusHours(1)).isPresent());
        assertTrue(event.getNotificationAtTime(eventTime.minusHours(5)).isPresent());
        assertTrue(event.getNotificationAtTime(eventTime.minusHours(10)).isPresent());
        assertFalse(event.getNotificationAtTime(eventTime.minusHours(25)).isPresent());
    }
}
