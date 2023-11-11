package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.interval.Duration;
import seedu.address.model.interval.Interval;
import seedu.address.model.interval.IntervalBegin;
import seedu.address.model.interval.IntervalDay;
import seedu.address.model.interval.IntervalEnd;

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
        assertTrue(freeTimeCommand1.equals(freeTimeCommand1));

        //test for different instances
        assertFalse(freeTimeCommand1.equals(freeTimeCommand2));

        //not equal to null
        assertFalse(freeTimeCommand1.equals(null));
        
    }
}
