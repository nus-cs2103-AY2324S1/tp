package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.parser.ParserUtil;

public class TimeInterval {

    public static final String MESSAGE_CONSTRAINTS_SYNTAX = "The format of an interval should be: mon 1200 - tue 1400";

    public static final String MESSAGE_CONSTRAINTS_LOGIC = "Your end time cannot be before your start time ";

    public static final String MESSAGE_CONSTRAINTS_OVERLAP = "No overlap is allowed in your interval. Eg. " +
            "mon 1200 - mon 1600 ;mon 1400 - mon 1800 is not allowed. Write it as mon 1200 - mon 1800";


    public static final String VALIDATION_REGEX = ".* .* - .* .*";
    private final Time start;
    private final Time end;

    public TimeInterval(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    public int compareToStartTime(TimeInterval other) {
        requireNonNull(other);
        return this.start.compareTo(other.start);
    }

    public static boolean isTimeIntervalOverlap(ArrayList<TimeInterval> intervals) {
        intervals.sort(TimeInterval::compareToStartTime);
        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i).end.compareTo(intervals.get(i + 1).start) > -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidTimeIntervalSyntax(String timeInterval) {
        return timeInterval.matches(VALIDATION_REGEX);
    }

    public static boolean isValidTimeIntervalLogic(Time start, Time end) {
        return start.compareTo(end) <= -1;
    }


    @Override
    public String toString() {
        return start.toString() + " - " + end.toString() + " ";
    }
}
