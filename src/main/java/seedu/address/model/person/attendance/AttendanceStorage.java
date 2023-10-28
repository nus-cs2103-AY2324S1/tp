package seedu.address.model.person.attendance;

import seedu.address.model.person.JoinDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

/**
 * Stores the attendances of each person.
 */
public class AttendanceStorage {

    private ArrayList<Attendance> storage;

    public AttendanceStorage() {
        storage = new ArrayList<>();

    }
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

    public AttendanceType getType(LocalDate date) {
        if (getAttendance(date) == null) {
            return AttendanceType.PRESENT;
        }
        requireNonNull(getAttendance(date));
        return getAttendance(date).getType();
    }


    public void markAbsent(LocalDate date) {
        if (getAttendance(date) == null) {
            storage.add(new Attendance(date, AttendanceType.ABSENT));
        } else {
            requireNonNull(getAttendance(date));
            getAttendance(date).markAbsent();
        }
    }

    public void markLate(LocalDate date) {
        if (getAttendance(date) == null) {
            storage.add(new Attendance(date, AttendanceType.LATE));
        } else {
            requireNonNull(getAttendance(date));
            getAttendance(date).markLate();
        }
    }

    public void markPresent(LocalDate date) {
        if (getAttendance(date) != null) {
            storage.remove(getAttendance(date));
        }
    }

    /**
     * Gets the number of attendances for the given type.
     *
     * @param type type of attendance to count
     * @return count of number of attendances
     */
    public int getCount(AttendanceType type, JoinDate joinDate) { //TODO decide if the param should be JoinDate or LocalDate
        LocalDate dateJoined = LocalDate.parse(joinDate.value);

        if (type == AttendanceType.PRESENT) {
            int numOfDaysSinceJoining = dateJoined.until(LocalDate.now()).getDays();
            int numOfDaysPresent = numOfDaysSinceJoining - getCount(AttendanceType.ABSENT, joinDate) - getCount(AttendanceType.LATE, joinDate);
            return numOfDaysPresent;
        }
        int result = 0;
        return (int) storage.stream()
                .filter(attendance -> attendance.getType() == type)
                .count();
    }

    /**
     * Gets value of storage.
     *
     * @return
     */
    public ArrayList<String> getValue() {
        ArrayList<Attendance> attendancesCopy = new ArrayList<>(storage); // Assuming storage is an ArrayList<Attendance>

        ArrayList<String> result = new ArrayList<>();
        for (Attendance attendance : attendancesCopy) {
            result.add(attendance.toString());
        }

        return result;
    }







}
