package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.testutil.TypicalTimeIntervals;


public class TimeIntervalListTest {

    @Test
    public void addTime_arrayList_true() {
        TimeIntervalList list = new TimeIntervalList();
        TimeInterval timeIntervalFirst = TypicalTimeIntervals.TIME_INTERVAL_MONTUE_ONE; // MON 1100 - TUE 1400
        TimeInterval timeIntervalSecond = TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_B; // MON 0500 - MON 1200;
        list.addTime(timeIntervalFirst);
        ArrayList<TimeInterval> toAdd = new ArrayList<>();
        toAdd.add(timeIntervalSecond);

        ArrayList<TimeInterval> toAddNoClash = new ArrayList<>();
        TimeInterval timeIntervalThird = TypicalTimeIntervals.TIME_INTERVAL_THUSAT_TWO;
        toAddNoClash.add(timeIntervalThird);

        StringBuilder errorMessage = new StringBuilder("There is a clash in these input timings with "
                                                       + "your existing timings:\n");
        errorMessage.append("MON 0500 - MON 1200 \n");

        StringBuilder passMessage = new StringBuilder("These times have been added:\n");
        passMessage.append("THU 1200 - SAT 1800 \n");

        assertTrue(list.addTime(toAdd).equals(errorMessage.toString()));
        assertTrue(list.addTime(toAddNoClash).equals(passMessage.toString()));
    }

    @Test
    public void deleteTime_arrayList_true() {
        TimeIntervalList list = new TimeIntervalList();
        TimeInterval timeIntervalFirst = TypicalTimeIntervals.TIME_INTERVAL_MONTUE_ONE; // MON 1100 - TUE 1400
        TimeInterval timeIntervalSecond = TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_B; // MON 0500 - MON 1200;
        list.addTime(timeIntervalFirst);
        ArrayList<TimeInterval> toAdd = new ArrayList<>();
        toAdd.add(timeIntervalFirst);

        ArrayList<TimeInterval> toAddNoMatch = new ArrayList<>();
        toAdd.add(timeIntervalSecond);

        StringBuilder errorMessage = new StringBuilder("These times were not in the list:\n");
        errorMessage.append("MON 0500 - MON 1200 \n");
        errorMessage.append("These times have been deleted:\n");
        errorMessage.append("MON 1100 - TUE 1400 \n");
        StringBuilder passMessage = new StringBuilder("These times have been deleted:\n");

        try {
            assertTrue(list.deleteTime(toAdd).equals(errorMessage.toString()));
            assertTrue(list.deleteTime(toAddNoMatch).equals(passMessage.toString()));
        } catch (CommandException ignore) {
            // do nothing
        }

    }


    @Test
    public void execute_findOverlap_true() {
        TimeIntervalList firstList = new TimeIntervalList();
        TimeIntervalList secondList = new TimeIntervalList();

        Duration duration = new Duration(30);

        firstList.addTime(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A); // MON 1030 - MON 1130
        secondList.addTime(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A); // MON 1100 - MON 1200

        firstList.addTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_OVERLAP_A); // MON 1200 - MON 1300
        secondList.addTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_HALFOVERLAP_A); // MON 1230 - MON 1330

        TimeIntervalList expectedList = new TimeIntervalList();
        TimeInterval expectedTimeFirst = TypicalTimeIntervals.TIME_INTERVAL_OVERLAPONE; // MON 1100 - MON 1130
        TimeInterval expectedTimeSecond = TypicalTimeIntervals.TIME_INTERVAL_OVERLAPTWO; // MON 1230 - MON 1300
        expectedList.addTime(expectedTimeFirst);
        expectedList.addTime(expectedTimeSecond);

        assertTrue(firstList.findOverlap(secondList, duration).equals(expectedList));
    }






}
