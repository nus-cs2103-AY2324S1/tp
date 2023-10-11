package seedu.address.model.attendance;

import java.time.LocalDate;

public class Attendance {
    private final LocalDate date;
    private final boolean isPresent;
    private final String moduleName;

    public Attendance(LocalDate date, boolean isPresent, String moduleName) {
        this.date = date;
        this.isPresent = isPresent;
        this.moduleName = moduleName;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public String getModuleName() {
        return moduleName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Attendance)) {
            return false;
        }
        Attendance otherAttendance = (Attendance) other;
        return date.equals(otherAttendance.date) &&
                isPresent == otherAttendance.isPresent &&
                moduleName.equals(otherAttendance.moduleName);
    }

    @Override
    public int hashCode() {
        return date.hashCode() + (isPresent ? 1 : 0) + moduleName.hashCode();
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Module: " + moduleName + ", Present: " + isPresent;
    }
}
