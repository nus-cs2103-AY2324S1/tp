public class Appointment {
    private DateTime dateTime;
    private StudentName studentName;
    private Description description;

    public Appointment(DateTime dateTime, StudentName studentName, Description description) {
        this.dateTime = dateTime;
        this.studentName = studentName;
        this.description = description;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public StudentName getStudentName() {
        return studentName;
    }

    public void setStudentName(StudentName studentName) {
        this.studentName = studentName;
    }
}