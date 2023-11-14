package seedu.codesphere.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.model.student.exceptions.DuplicateStudentException;
import seedu.codesphere.model.student.exceptions.StudentNotFoundException;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(student)}.
 * As such, adding and updating of students uses Student#isSamestudent(student)
 * for equality to ensure that the student being added or updated is
 * unique in terms of identity in the UniquestudentList. However, the removal of a student uses Student#equals(Object)
 * to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudent(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudent);
    }

    /**
     * Clears all students in the student list.
     */
    public void clearAll() {
        internalList.clear();
    }

    /**
     * Returns the total number of students in the unique student list with tag GOOD.
     */
    public int getGoodTagCount() {
        return Math.toIntExact(internalList.stream().filter(
                s -> s.getTag().equals(new Tag(StudentRank.GOOD))).count());
    }

    /**
     * Returns the total number of students in the unique student list with tag AVERAGE.
     */
    public int getAverageTagCount() {
        return Math.toIntExact(internalList.stream().filter(
                s -> s.getTag().equals(new Tag(StudentRank.AVERAGE))).count());
    }

    /**
     * Returns the total number of students in the unique student list with non-empty pending question field.
     */
    public int getPendingQuestionCount() {
        return Math.toIntExact(internalList.stream().filter(
                s -> !s.getPendingQuestion().value.isEmpty()).count());
    }

    /**
     * Returns the total number of students in the unique student list with tag POOR.
     */
    public int getPoorTagCount() {
        return Math.toIntExact(internalList.stream().filter(
                s -> s.getTag().equals(new Tag(StudentRank.POOR))).count());
    }

    /**
     * Returns the size of the unique student list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.isSameStudent(editedStudent) && contains(editedStudent)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public Student getStudent(Index index) {
        return internalList.get(index.getZeroBased());
    }

    public void setStudents(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(students);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueStudentList)) {
            return false;
        }

        UniqueStudentList otherUniqueStudentList = (UniqueStudentList) other;
        return internalList.equals(otherUniqueStudentList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameStudent(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
