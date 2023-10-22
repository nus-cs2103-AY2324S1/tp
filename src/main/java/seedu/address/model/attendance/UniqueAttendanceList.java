package seedu.address.model.attendance;

import java.util.ArrayList;

import seedu.address.model.event.Event;
import seedu.address.model.member.Member;

/**
 * Represents a list of Attendance.
 */
public class UniqueAttendanceList extends ArrayList<Attendance> {
    public void addAttendance(Attendance attendance) {
        this.add(attendance);
    }

    public void removeAttendance(Attendance attendance) {
        this.remove(attendance);
    }

    public ArrayList<Attendance> getFilteredListByMember(Member member) {
        ArrayList<Attendance> filteredList = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            Attendance attendance = this.get(i);
            if (attendance.getMemberName().equals(member.getName())) {
                filteredList.add(attendance);
            }
        }

        return filteredList;
    }

    public ArrayList<Attendance> getFilteredListByEvent(Event event) {
        ArrayList<Attendance> filteredList = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            Attendance attendance = this.get(i);
            if (attendance.getEventName().equals(event.getName())) {
                filteredList.add(attendance);
            }
        }

        return filteredList;
    }
}
