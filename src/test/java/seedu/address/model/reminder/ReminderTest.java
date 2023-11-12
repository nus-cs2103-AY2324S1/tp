// package seedu.address.model.reminder;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static seedu.address.testutil.TypicalPersons.ALICE;

// import java.time.LocalDate;
// import java.util.List;

// import org.junit.jupiter.api.Test;

// import seedu.address.model.person.Interaction;
// import seedu.address.model.person.Person;

// public class ReminderTest {

//     public static final String FUTURE_VALID_DATE = "3000-01-01";
//     public static final String PAST_VALID_DATE = "2000-01-01";
//     public static final String VALID_OUTCOME = "CLOSED";
//     public static final String VALID_INTERACTION_NOTE = "Valid interaction note";

//     public static final List<Interaction> VALID_FUTURE_INTERACTION_LIST = List.of(
//         new Interaction(VALID_INTERACTION_NOTE,
//             Interaction.Outcome.valueOf(VALID_OUTCOME),
//             LocalDate.parse(FUTURE_VALID_DATE)));

//     public static final Reminder VALID_FUTURE_REMINDER = new Reminder(
//             new Person.PersonBuilder(ALICE).withInteractions(VALID_FUTURE_INTERACTION_LIST).build());

//     public static final List<Interaction> VALID_PAST_INTERACTION_LIST = List.of(
//         new Interaction(VALID_INTERACTION_NOTE,
//             Interaction.Outcome.valueOf(VALID_OUTCOME),
//             LocalDate.parse(PAST_VALID_DATE)));

//     public static final Reminder VALID_PAST_REMINDER = new Reminder(
//             new Person.PersonBuilder(ALICE).withInteractions(VALID_PAST_INTERACTION_LIST).build());

//     @Test
//     public void isAfterNow() {
//         assertTrue(VALID_FUTURE_REMINDER.isAfterNow());
//         assertTrue(!VALID_PAST_REMINDER.isAfterNow());
//     }

//     @Test
//     public void compareTo() {
//         assertTrue(VALID_FUTURE_REMINDER.compareTo(LocalDate.now()) == 1);
//         assertTrue(VALID_PAST_REMINDER.compareTo(LocalDate.now()) == -1);
//     }

//     @Test
//     public void toStringMethod() {
//         String expected = Reminder.class.getCanonicalName() + "{person=" + ALICE
//                 + ", followUpDate=" + VALID_FUTURE_REMINDER.getFollowUpDate() + "}";
//         assertEquals(expected, VALID_FUTURE_REMINDER.toString());
//     }
// }
