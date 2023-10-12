package seedu.address.model.appointment;

public class StudentName {
    Person student;

    public StudentName(Person student) {
        this.student = student;
    }

    public Person getStudent() {
        return student;
    }
}