package seedu.address.testutil;

import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Hours;
import seedu.address.model.attendance.Remark;
import seedu.address.model.shared.Name;

/**
 * A utility class to help with building Attendance objects.
 */
public class AttendanceBuilder {

    public static final String DEFAULT_MEMBER_NAME = "Amy Bee";
    public static final String DEFAULT_EVENT_NAME = "Rock climbing session 1";
    public static final String DEFAULT_HOURS = "0";
    public static final String DEFAULT_REMARK = "Absent due to Covid";

    private Name memberName;
    private Name eventName;
    private Hours hours;
    private Remark remark;

    /**
     * Creates an {@code AttendanceBuilder} with the default details.
     */
    public AttendanceBuilder() {
        memberName = new Name(DEFAULT_MEMBER_NAME);
        eventName = new Name(DEFAULT_EVENT_NAME);
        hours = new Hours(DEFAULT_HOURS);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the AttendanceBuilder with the data of {@code attendanceToCopy}.
     */
    public AttendanceBuilder(Attendance attendanceToCopy) {
        memberName = attendanceToCopy.getMemberName();
        eventName = attendanceToCopy.getEventName();
        hours = attendanceToCopy.getHours();
        remark = attendanceToCopy.getRemark();
    }

    /**
     * Sets the Member's {@code Name} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withMemberName(String name) {
        this.memberName = new Name(name);
        return this;
    }

    /**
     * Sets the Event's {@code Name} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withEventName(String name) {
        this.eventName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Hours} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withHours(String hours) {
        this.hours = new Hours(hours);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withLocation(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Attendance build() {
        return new Attendance(memberName, eventName, hours, remark);
    }

}
