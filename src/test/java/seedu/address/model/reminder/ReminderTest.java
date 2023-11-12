package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.interaction.Interaction;

public class ReminderTest {

    public static final String FUTURE_VALID_DATE = "3000-01-01";
    public static final String PAST_VALID_DATE = "2000-01-01";
    public static final String VALID_OUTCOME = "CLOSED";
    public static final String VALID_INTERACTION_NOTE = "Valid interaction note";

    public static final Interaction VALID_FUTURE_INTERACTION = new Interaction(VALID_INTERACTION_NOTE,
            Interaction.Outcome.valueOf(VALID_OUTCOME),
            LocalDate.parse(FUTURE_VALID_DATE));

    public static final Reminder VALID_FUTURE_REMINDER = new Reminder(
            new Person.PersonBuilder(ALICE).addInteraction(VALID_FUTURE_INTERACTION).build());

    public static final Interaction VALID_PAST_INTERACTION = new Interaction(VALID_INTERACTION_NOTE,
            Interaction.Outcome.valueOf(VALID_OUTCOME),
            LocalDate.parse(PAST_VALID_DATE));

    public static final Reminder VALID_PAST_REMINDER = new Reminder(
            new Person.PersonBuilder(ALICE).addInteraction(VALID_PAST_INTERACTION).build());

    @Test
    public void getName() {
        assertEquals(ALICE.getName(), VALID_FUTURE_REMINDER.getName());
        assertEquals(ALICE.getName(), VALID_PAST_REMINDER.getName());
    }

    @Test
    public void getLead() {
        assertEquals(ALICE.getLead(), VALID_FUTURE_REMINDER.getLead());
        assertEquals(ALICE.getLead(), VALID_PAST_REMINDER.getLead());
    }

    @Test
    public void getFollowUpDate() {
        LocalDate futureDate = LocalDate.parse(FUTURE_VALID_DATE).plusWeeks(ALICE.getLead().getFollowUpPeriod());
        LocalDate pastDate = LocalDate.parse(PAST_VALID_DATE).plusWeeks(ALICE.getLead().getFollowUpPeriod());
        assertEquals(futureDate, VALID_FUTURE_REMINDER.getFollowUpDate());
        assertEquals(pastDate, VALID_PAST_REMINDER.getFollowUpDate());
    }

    @Test
    public void isAfterYesterday() {
        assertTrue(VALID_FUTURE_REMINDER.isAfterYesterday());
        assertTrue(!VALID_PAST_REMINDER.isAfterYesterday());
    }

    @Test
    public void compareTo() {
        assertTrue(VALID_FUTURE_REMINDER.compareTo(LocalDate.now()) == 1);
        assertTrue(VALID_PAST_REMINDER.compareTo(LocalDate.now()) == -1);
    }

    @Test
    public void toStringMethod() {
        String expected = Reminder.class.getCanonicalName() + "{person=" + ALICE
                + ", followUpDate=" + VALID_FUTURE_REMINDER.getFollowUpDate() + "}";
        assertEquals(expected, VALID_FUTURE_REMINDER.toString());
    }
}
