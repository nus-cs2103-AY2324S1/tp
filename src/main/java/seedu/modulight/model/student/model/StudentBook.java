package seedu.modulight.model.student.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.modulight.commons.util.ToStringBuilder;
import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.student.Student;
import seedu.modulight.model.student.StudentId;
import seedu.modulight.model.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class StudentBook implements ReadOnlyStudentBook {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public StudentBook() {}

    /**
     * Creates an StudentBook using the Students in the {@code toBeCopied}
     */
    public StudentBook(ReadOnlyStudentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code StudentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyStudentBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing
     * student in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }


    /**
     * Removes {@code key} from this {@code StudentBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Clears all data from this {@code StudentBook}.
     */
    public void clearStudent() {
        students.clear();
    }

    /**
     * Sorts all students from this {@code StudentBook}.
     */
    public void sortStudent(String order, boolean isReverse) {
        students.sort(order, isReverse);
    }

    /**
     * Sorts all students from this {@code StudentBook} based on their performance
     * under a given graded component.
     */
    public void sortStudentScore(GcName gcName, boolean isReverse) {
        students.sortScore(gcName, isReverse);
    }

    /**
     * Gets the number of students in this {@code StudentBook}.
     */
    public int getSize() {
        return this.students.getSize();
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("students", students)
                .toString();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentBook)) {
            return false;
        }

        StudentBook otherStudentBook = (StudentBook) other;
        return students.equals(otherStudentBook.students);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    /**
     * Finds and returns a {@code Student} based on the given {@code StudentId}.
     */
    public Student getStudentById(StudentId studentId) {
        requireNonNull(studentId);
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}
