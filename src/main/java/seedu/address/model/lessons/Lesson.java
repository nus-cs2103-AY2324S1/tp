package seedu.address.model.lessons;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Subject;

/**
 * Represents a student's lesson in the schedule.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    // Lesson fields
    private LocalDateTime start;
    private LocalDateTime end;
    // Data fields
    private Subject subject;
    private ArrayList<String> students;

    /**
     * Constructor for a Lesson Object with one student.
     * Note: parse the string before giving it to the constructor.
     *
     * @param start The start time of the lesson
     * @param end The end time of the lesson
     * @param subject The subject of this lesson
     * @param studentName The student attending this lesson. Note: Converted to ArrayList when stored
     * @see seedu.address.logic.parser.ParserUtil
     */
    public Lesson(LocalDateTime start, LocalDateTime end, Subject subject, String studentName) {
        requireAllNonNull(start, end, subject, studentName);
        this.start = start;
        this.end = end;
        this.subject = subject;
        ArrayList<String> p = new ArrayList<>();
        p.add(studentName);
        this.students = p;
    }

    /**
     * Constructor for a Lesson Object
     * Note: This constructor is for the case where the students is already an ArrayList
     * Note: parse the string before giving it to the constructor.
     *
     * @param start The start time of the lesson
     * @param end The end time of the lesson
     * @param subject The subject of this lesson
     * @param students The student attending this lesson. Note: Converted to ArrayList when stored
     * @see seedu.address.logic.parser.ParserUtil
     */
    public Lesson(LocalDateTime start, LocalDateTime end, Subject subject, ArrayList<String> students) {
        requireAllNonNull(start, end, subject, students);
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.students = students;
    }

    // TODO: Tasks
    // private TaskContainer tasks;

    public LocalDateTime getStart() {
        return start;
    }
    public LocalDateTime getEnd() {
        return end;
    }
    /**
     * Serializes the start date to a String
     * @return stringified version of start date
     */
    public String serializeStart() {
        return this.start.toString();
    }
    /**
     * Serializes the end date to a String
     * @return stringified version of end date
     */
    public String serializeEnd() {
        return this.end.toString();
    }
    /**
     * Serializes the subject to a String
     * @return stringified version of subject
     */
    public String serializeSubject() {
        return this.subject.subjectName.toString(); // TODO public access
    }
    /**
     * Serializes the students to a String
     * @return stringified version of students
     */
    public String serializeStudents() {
        return String.join(",", this.students);
    }

    /**
     * Deserialize the dates to a String
     * @return stringified version of dates
     */
    public static LocalDateTime deserializeDate(String date) throws IllegalValueException {
        return LocalDateTime.parse(date);
    }

    /**
     * Deserialize the subject to a String
     * @return stringified version of subjects
     */
    public static Subject deserializeSubject(String subject) throws IllegalValueException {
        return Subject.parseSubject(subject);
    }

    /**
     * Deserialize the students to a String
     * @return stringified version of students
     */
    public static ArrayList<String> deserializeStudents(String students) throws IllegalValueException {
        // comma delimited
        return new ArrayList<>(Arrays.asList(students.split(",")));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return start.equals(otherLesson.start)
                && end.equals(otherLesson.end)
                && subject.equals(otherLesson.subject)
                && students.equals(otherLesson.students);

    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(start, end, subject, students);
    }
    /**
     * Returns true if both lessons have the same start and end time.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getStart().equals(getStart())
                && otherLesson.getEnd().equals(getEnd());
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("start", start)
                .add("end", end)
                .add("subject", subject)
                .add("students", students)
                .toString();
    }
}
