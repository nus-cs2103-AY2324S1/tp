package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.enums.InputSource;
import seedu.address.testutil.PersonBuilder;

public class AppointmentOverlapsPredicateTest {
    private static final String QUERY_APPT_MAIN = "1-Aug-23 0900 1200";
    private static final String QUERY_APPT_NO_OVERLAP_START = "1-Aug-23 0400 0859";
    private static final String QUERY_APPT_NO_OVERLAP_END = "1-Aug-23 1201 1400";
    private static final String QUERY_APPT_OVERLAP_END_START = "1-Aug-23 0859 0900";
    private static final String QUERY_APPT_OVERLAP_EXACT_START = "1-Aug-23 0900 0900";
    private static final String QUERY_APPT_OVERLAP_EXACT_END = "1-Aug-23 1200 1200";
    private static final String QUERY_APPT_OVERLAP_START_END = "1-Aug-23 1200 1201";
    private static final String QUERY_APPT_OVERLAP_PERIOD = "1-Aug-23 0800 1000";
    private static final String QUERY_APPT_DIFFERENT_DATE = "2-Aug-23 0900 1200";

    private static final List<Appointment> queries = new ArrayList<>();

    @BeforeAll
    public static void initializeQueriesArray() throws Exception {
        queries.add(Appointment.of(QUERY_APPT_MAIN, InputSource.USER_INPUT));
        queries.add(Appointment.of(QUERY_APPT_NO_OVERLAP_START, InputSource.USER_INPUT));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentOverlapsPredicate(null));
    }

    @Test
    public void equals() {
        AppointmentOverlapsPredicate firstPredicate = new AppointmentOverlapsPredicate(queries.get(0));
        AppointmentOverlapsPredicate secondPredicate = new AppointmentOverlapsPredicate(queries.get(1));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentOverlapsPredicate firstPredicateCopy = new AppointmentOverlapsPredicate(queries.get(0));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(Objects.hash(PREFIX_APPOINTMENT, queries.get(0)),
                new AppointmentOverlapsPredicate(queries.get(0)).hashCode());
        assertEquals(Objects.hash(PREFIX_APPOINTMENT, queries.get(1)),
                new AppointmentOverlapsPredicate(queries.get(1)).hashCode());
    }

    @Test
    public void test_apptOverlaps_returnsTrue() {
        // Exact overlap
        AppointmentOverlapsPredicate predicate = new AppointmentOverlapsPredicate(queries.get(0));
        assertTrue(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_MAIN).build()));

        // End-Start overlap
        assertTrue(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_OVERLAP_END_START).build()));

        // Exact-Start overlap
        assertTrue(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_OVERLAP_EXACT_START).build()));

        // Start-End overlap
        assertTrue(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_OVERLAP_START_END).build()));

        // Exact-End overlap
        assertTrue(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_OVERLAP_EXACT_END).build()));

        // Period Overlap
        predicate = new AppointmentOverlapsPredicate(queries.get(1));
        assertTrue(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_OVERLAP_PERIOD).build()));
        predicate = new AppointmentOverlapsPredicate(queries.get(0));
        assertTrue(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_OVERLAP_PERIOD).build()));
    }

    @Test
    public void test_apptDoesNotOverlap_returnsFalse() {
        // Test against no appointment
        AppointmentOverlapsPredicate predicate = new AppointmentOverlapsPredicate(queries.get(0));
        assertFalse(predicate.test(new PersonBuilder().withoutAppointment().build()));

        // No overlap from start
        assertFalse(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_NO_OVERLAP_START).build()));

        // No overlap from end
        assertFalse(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_NO_OVERLAP_END).build()));

        // Different dates
        assertFalse(predicate.test(new PersonBuilder().withAppointment(QUERY_APPT_DIFFERENT_DATE).build()));
    }

    @Test
    public void toStringMethod() {
        AppointmentOverlapsPredicate predicate = new AppointmentOverlapsPredicate(queries.get(0));

        String expected = AppointmentOverlapsPredicate.class.getCanonicalName()
                + "{query=" + queries.get(0).toSaveString() + "}";
        assertEquals(expected, predicate.toString());
    }
}
