package seedu.address.model.student.grades;

/**
 * Represents an Assignment of a Student in the address book.
 */
public class Assignment {

    private Integer marks = 0;

    public Assignment() {
    }

    public Assignment(Integer marks) {
        this.marks = marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Integer getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return marks + " / 100";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return marks.equals(otherAssignment.marks);
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }
}
