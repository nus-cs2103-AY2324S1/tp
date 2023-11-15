package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.testutil.UserBuilder;

public class UserTest {

    @Test
    public void constructor() {
        User user = new UserBuilder().build();
        User expectedUser = new User(user);
        User expectedUser2 = new User(user, user.getDatedEvents());
        assertEquals(user, expectedUser);
        assertEquals(user, expectedUser2);
    }

    @Test
    public void returnRemindedDatedEvents() {
        User user = new UserBuilder().build();
        user.getSchedule().addDatedEvent(new DatedEvent("Walk Dog", "Monday 1030 1130",
                LocalDate.now().toString(), true));
        String expectedMessage = "Walk Dog Monday 1030 1130\n";
        assertEquals(expectedMessage, user.returnRemindedEvent());
    }

}
