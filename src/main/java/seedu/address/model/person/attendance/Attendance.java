package seedu.address.model.person.attendance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an employee's attendance.
 */
public class Attendance {
    private LocalDate date;
    private AttendanceType attendanceType;

    /**
     * Constructs a {@code Attendance}.
     * @param date date you want to record
     * @param attendanceType the type of attendance.
     */
    public Attendance(LocalDate date, AttendanceType attendanceType) {
        this.date = date;
        this.attendanceType = attendanceType;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public AttendanceType getType() {
        return this.attendanceType;
    }

    public void markAbsent() {
        this.attendanceType = AttendanceType.ABSENT;
    }

    public void markLate() {
        this.attendanceType = AttendanceType.LATE;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "//" + attendanceType.toString().toLowerCase();
    }

}
