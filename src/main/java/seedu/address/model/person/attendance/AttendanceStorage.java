package seedu.address.model.person.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import seedu.address.model.person.JoinDate;

/**
 * Stores the attendances of each person.
 */
public class AttendanceStorage {

    private ArrayList<Attendance> storage;

    /**
     * Constructs a {@code AttendanceStorage}
     */
    public AttendanceStorage() {
        storage = new ArrayList<>();

    }

    /**
     * Constructs a {@code AttendanceStorage}
     * @param attendances A list of attendance records.
     */
    public AttendanceStorage(ArrayList<String> attendances) {
        this();
        for (String attendance : attendances) {
            String[] attendanceArr = attendance.split("//");
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM/yyyy")
                    .toFormatter();
            LocalDate date = LocalDate.parse(attendanceArr[0], formatter);
            AttendanceType attendanceType = AttendanceType.valueOf(attendanceArr[1].toUpperCase());
            storage.add(new Attendance(date, attendanceType));
        }
    }

    /**
     * Gets the attendance given the date.
     *
     * @param date date to query
     * @return the type of attendance of the person on this date
     */
    private Attendance getAttendance(LocalDate date) {
        requireNonNull(storage);
        if (storage.size() == 0) {
            // Anything not in storage is present.
            return null;
        }

        for (Attendance attendance : storage) {
            if (attendance.getDate().equals(date)) {
                return attendance;
            }
        }

        return null;

    }

    /**
     * Get the attendance type on a specific date
     * @param date date of attendance type you want to know
     * @return attendance type. This can either be Late, Absent or Present.
     */
    public AttendanceType getType(LocalDate date) {
        requireNonNull(date);
        if (getAttendance(date) == null) {
            return AttendanceType.PRESENT;
        }
        requireNonNull(getAttendance(date));
        return getAttendance(date).getType();
    }

    /**
     * Marks a person to be absent.
     * @param date The date on which the person is absent.
     */
    public void markAbsent(LocalDate date) {
        requireNonNull(date);
        if (getAttendance(date) == null) {
            storage.add(new Attendance(date, AttendanceType.ABSENT));
        } else {
            requireNonNull(getAttendance(date));
            getAttendance(date).markAbsent();
        }
    }

    /**
     * Marks a person to be late.
     * @param date The date on which the person is late.
     */
    public void markLate(LocalDate date) {
        requireNonNull(date);
        if (getAttendance(date) == null) {
            storage.add(new Attendance(date, AttendanceType.LATE));
        } else {
            requireNonNull(getAttendance(date));
            getAttendance(date).markLate();
        }
    }

    /**
     * Marks a person to be present.
     * @param date The date on which the person is present.
     */
    public void markPresent(LocalDate date) {
        requireNonNull(date);
        if (getAttendance(date) != null) {
            storage.remove(getAttendance(date));
        }
    }

    /**
     * Returns the attendanceType of the employee on the current date.
     */
    public AttendanceType getTodayAttendance() {
        LocalDate currentDate = LocalDate.now();
        return getType(currentDate);
    }

    /**
     * Gets the number of attendances for the given type.
     *
     * @param type the type of attendance
     * @param joinDate the date the employee joined
     * @param numOfLeave the number of leaves taken
     * @return
     */
    private int getCount(AttendanceType type, JoinDate joinDate, int numOfLeave) {
        //TODO decide if the param should be JoinDate or LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateJoined = LocalDate.parse(joinDate.value, formatter);

        if (type == AttendanceType.PRESENT) {
            int numOfDaysSinceJoining = dateJoined.until(LocalDate.now()).getDays();
            int numOfDaysPresent = numOfDaysSinceJoining - getCount(AttendanceType.ABSENT, joinDate, numOfLeave)
                    - getCount(AttendanceType.LATE, joinDate, numOfLeave) - numOfLeave;
            return numOfDaysPresent;
        }
        return (int) storage.stream()
                .filter(attendance -> attendance.getType() == type)
                .count();
    }

    /**
     * Gets the number of attendances for the given type.
     *
     * @return count of number of attendances
     */
    public int[] getAttendanceReport(JoinDate joinDate, int numOfLeave) {
        int numOfDaysOnLeave = numOfLeave;
        int numOfDaysAbsent = getCount(AttendanceType.ABSENT, joinDate, numOfLeave);
        int numOfDaysLate = getCount(AttendanceType.LATE, joinDate, numOfLeave);

        return new int[]{numOfDaysOnLeave, numOfDaysAbsent, numOfDaysLate};
    }

    /**
     * Gets value of storage as an array list of Strings.
     *
     * @return array list of Attendance values as string
     */
    public ArrayList<String> getValue() {
        // Assuming storage is an ArrayList<Attendance>
        ArrayList<Attendance> attendancesCopy = new ArrayList<>(storage);

        ArrayList<String> result = new ArrayList<>();
        for (Attendance attendance : attendancesCopy) {
            result.add(attendance.toString());
        }

        return result;
    }
}
