package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_AND_EVENTS_SHOWN_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.MEETING_3_DAYS_AFTER_TODAY;
import static seedu.address.testutil.TypicalMeetings.MEETING_6_DAYS_AFTER_TODAY;
import static seedu.address.testutil.TypicalPersons.JANE;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalPersons.getBirthdayAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.BirthdayWithinDaysPredicate;
import seedu.address.model.person.EventWithinDaysPredicate;

class RemindCommandTest {
    private Model model = new ModelManager(getBirthdayAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getBirthdayAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        int firstDays = 1;
        int secondDays = 2;
        BirthdayWithinDaysPredicate firstBirthdayPredicate =
                new BirthdayWithinDaysPredicate(firstDays);
        BirthdayWithinDaysPredicate secondBirthdayPredicate =
                new BirthdayWithinDaysPredicate(secondDays);
        EventWithinDaysPredicate firstEventPredicate =
                new EventWithinDaysPredicate(firstDays);
        EventWithinDaysPredicate secondEventPredicate =
                new EventWithinDaysPredicate(secondDays);

        RemindCommand remindFirstCommand =
                new RemindCommand(firstBirthdayPredicate, firstEventPredicate, firstDays);
        RemindCommand remindSecondCommand =
                new RemindCommand(secondBirthdayPredicate, secondEventPredicate, secondDays);

        //same object -> returns true
        assertTrue(remindFirstCommand.equals(remindFirstCommand));

        //same values -> returns true
        RemindCommand remindFirstCommandCopy =
                new RemindCommand(firstBirthdayPredicate, firstEventPredicate, firstDays);
        assertTrue(remindFirstCommand.equals(remindFirstCommandCopy));

        //different types -> returns false
        assertFalse(remindFirstCommand.equals(1));

        //null -> returns false
        assertFalse(remindFirstCommand.equals(null));

        //different person -> returns false
        assertFalse(remindFirstCommand.equals(remindSecondCommand));
    }

    @Test
    public void execute_notWithinDays_noPersonOrEventFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_SHOWN_OVERVIEW, 1);
        BirthdayWithinDaysPredicate predicate = new BirthdayWithinDaysPredicate(1);
        EventWithinDaysPredicate eventPredicate = new EventWithinDaysPredicate(1);
        RemindCommand command = new RemindCommand(predicate, eventPredicate, 1);
        expectedModel.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredEventList(eventPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(Collections.emptyList(), model.getEventList());
    }

    @Test
    public void execute_withinDays_personAndEventFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_SHOWN_OVERVIEW, 4);
        BirthdayWithinDaysPredicate predicate = new BirthdayWithinDaysPredicate(4);
        EventWithinDaysPredicate eventWithinDaysPredicate = new EventWithinDaysPredicate(4);
        RemindCommand command = new RemindCommand(predicate, eventWithinDaysPredicate, 4);
        expectedModel.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredEventList(eventWithinDaysPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JOHN), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_3_DAYS_AFTER_TODAY), model.getEventList());
    }

    @Test
    public void execute_withinDays_multiplePersonAndEventFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_AND_EVENTS_SHOWN_OVERVIEW, 7);
        BirthdayWithinDaysPredicate predicate = new BirthdayWithinDaysPredicate(7);
        EventWithinDaysPredicate eventWithinDaysPredicate = new EventWithinDaysPredicate(7);
        RemindCommand command = new RemindCommand(predicate, eventWithinDaysPredicate, 7);
        expectedModel.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredEventList(eventWithinDaysPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JOHN, JANE), model.getFilteredPersonList());
        assertEquals(Arrays.asList(MEETING_3_DAYS_AFTER_TODAY,
                MEETING_6_DAYS_AFTER_TODAY), model.getEventList());
    }

    @Test
    public void toStringMethod() {
        int days = 1;
        BirthdayWithinDaysPredicate birthdayPredicate =
                new BirthdayWithinDaysPredicate(days);
        EventWithinDaysPredicate eventPredicate =
                new EventWithinDaysPredicate(days);
        RemindCommand remindCommand =
                new RemindCommand(birthdayPredicate, eventPredicate, days);
        String expected = RemindCommand.class.getCanonicalName()
                + "{birthdayPredicate=" + birthdayPredicate
                + ", eventPredicate=" + eventPredicate
                + ", days=" + days + "}";
        assertEquals(expected, remindCommand.toString());
    }
}
