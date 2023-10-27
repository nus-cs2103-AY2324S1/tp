package seedu.address.model.attendance;

import java.time.LocalDate;

public class Attendance {
    private LocalDate date;
    private AttendanceType attendanceType;

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

}
