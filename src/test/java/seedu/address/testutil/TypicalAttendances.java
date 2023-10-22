package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.attendance.Attendance;
/**
 * A utility class containing a list of {@code Attendance} objects to be used in tests.
 */
public class TypicalAttendances {

    public static final Attendance ALICE_AURORA = new AttendanceBuilder().withMemberName("Alice Pauline")
            .withEventName("Aurora Borealis").withHours("3").withRemark("Role: Photographer").build();

    public static final Attendance BENSON_BOXING = new AttendanceBuilder().withMemberName("Benson Meier")
            .withEventName("Boxing Day").withHours("7").withRemark("Bring boxing gloves").build();

    public static final Attendance CARL_CNY = new AttendanceBuilder().withMemberName("Carl Kurz")
            .withEventName("Chinese New Year").withHours("3").withRemark("Brought red packets").build();
    public static final Attendance DANIEL_DOG_DAY = new AttendanceBuilder().withMemberName("Daniel Meier")
            .withEventName("Dog Celebration Day").withHours("5").withRemark("Bringing dogs").build();

    public static final Attendance ELLE_EHCO_DAY = new AttendanceBuilder().withMemberName("Elle Meyer")
            .withEventName("Echo Day").withHours("0").withRemark("Absent without reason").build();

    public static final Attendance FIONA_FESTIVAL = new AttendanceBuilder().withMemberName("Fiona Kunz")
            .withEventName("Festival").withHours("2").withRemark("Usher").build();

    public static final Attendance GEORGE_GRAVITY_DISCOVERY_DAY = new AttendanceBuilder().withMemberName("George Best")
            .withEventName("Gravity Discovery Day").withHours("1").withRemark("Discovered gravity").build();

    private TypicalAttendances() {} // prevents instantiation

    public static List<Attendance> getTypicalAttendance() {
        return new ArrayList<>(Arrays.asList(ALICE_AURORA, BENSON_BOXING, CARL_CNY, DANIEL_DOG_DAY, ELLE_EHCO_DAY,
                FIONA_FESTIVAL, GEORGE_GRAVITY_DISCOVERY_DAY));
    }
}
