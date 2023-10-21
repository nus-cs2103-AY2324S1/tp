package seedu.address.model;

import seedu.address.logic.parser.ParserUtil;

public class TimeInterval {

    private final Time start;
    private final Time end;

    public TimeInterval(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return start.toString() + " - " + end.toString() + " ";
    }
}
