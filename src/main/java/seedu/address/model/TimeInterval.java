package seedu.address.model;

import seedu.address.logic.parser.ParserUtil;

public class TimeInterval {

    private Time start;
    private Time end;
    public TimeInterval(String interval) {
        setInterval(interval);
    }
    private void setInterval(String interval) {
        String[] data = ParserUtil.parseEachInterval(interval);
        this.start = new Time(data[0]);
        this.end = new Time(data[1]);
    }
    @Override
    public String toString() {
        return start.toString() + " - " + end.toString() + " ";
    }
}
