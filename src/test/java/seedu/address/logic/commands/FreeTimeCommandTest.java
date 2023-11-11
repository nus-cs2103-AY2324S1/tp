package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.interval.Duration;
import seedu.address.model.interval.Interval;
import seedu.address.model.interval.IntervalBegin;
import seedu.address.model.interval.IntervalDay;
import seedu.address.model.interval.IntervalEnd;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FreeTimeCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FreeTimeCommand(null));
    }

    @Test
    public void equals() {
        Interval interval1 = new Interval(new IntervalDay("Thu"), new Duration("120"), new IntervalBegin("0800"),
                new IntervalEnd("1000"));

        Interval interval2 = new Interval(new IntervalDay("Wed"), new Duration("120"), new IntervalBegin("0800"),
                new IntervalEnd("1000"));

        FreeTimeCommand freeTimeCommand1 = new FreeTimeCommand(interval1);
        FreeTimeCommand freeTimeCommand2 = new FreeTimeCommand(interval2);

        //test for same instance
        assertEquals(freeTimeCommand1, freeTimeCommand1);

        //test for different instances
        assertNotEquals(freeTimeCommand1, freeTimeCommand2);

        //not equal to null
        assertNotEquals(freeTimeCommand1, null);
    }

    @Test
    public void execute() throws CommandException {
        List<Person> persons = new ArrayList<>();
        Person validPerson1 = new PersonBuilder()
                .withDay("Sat")
                .withBegin("1500")
                .withEnd("1600")
                .build();
        Person validPerson2 = new PersonBuilder()
                .withDay("Sat")
                .withBegin("1000")
                .withEnd("1200")
                .build();
        persons.add(validPerson1);
        persons.add(validPerson2);

        Interval interval = new Interval(new IntervalDay("Sat"), new Duration("120"), new IntervalBegin("0800"),
                new IntervalEnd("2200"));
        FreeTimeCommand freeTimeCommand = new FreeTimeCommand(interval);
        ModelStub modelStub = new ModelStubWithPersonList(persons);

        CommandResult commandResult = freeTimeCommand.execute(modelStub);
        String listOfFreeTime = "Free from 08:00 - 10:00\n"
                + "Free from 12:00 - 15:00\n"
                + "Free from 16:00 - 22:00\n";
        assertEquals(String.format(FreeTimeCommand.MESSAGE_SUCCESS, listOfFreeTime),
                commandResult.getFeedbackToUser());
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPersonList extends ModelStub {
        private final List<Person> persons;

        ModelStubWithPersonList(List<Person> persons) {
            requireNonNull(persons);
            this.persons = persons;
        }

        @Override
        public List<String> findInterval(Interval interval) {
            return this.persons.stream().map(person -> person.getLesson().getTimeSlot()).collect(Collectors.toList());
        }
    }


}
