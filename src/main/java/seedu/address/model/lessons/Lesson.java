package seedu.address.model.lessons;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
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
    private ArrayList<String> students; // TOOD: change to student object

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

    /**
     * Gets the start time formatted in 12h
     */
    public String getStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return start.format(formatter);
    }

    /**
     * Gets the end time formatted in 12h
     */
    public String getEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return end.format(formatter);
    }

    /**
     * Gets a one-line overview of the lesson.
     *
     * If a lesson is on Thursday, 10 Oct 10 am - 12 pm, it will be formatted as:
     *
     * 10 am - 12 pm // TODO
     * @return A formatted overview of the time of the lesson
     */
    public String getLessonOverview() {
        return getLessonDate() + " • " + getSubject();
    }
    /**
     * Gets a one-line overview of the lesson.
     *
     * If a lesson is on Thursday, 10 Oct 10 am - 12 pm, it will be formatted as:
     *
     * 10 am - 12 pm
     * @return A formatted overview of the time of the lesson
     */
    public String getLessonDuration() {
        return getStartTime() + " - " + getEndTime();
    }

    /**
     * Gets the date of a lesson.
     * If the start and end date are the same, then only one date string is returned.
     * Else, the date will be returned as: [start date] - [end date]
     * @return
     */
    public String getLessonDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String formattedStart = start.format(formatter);
        if (isSameDay()) {
            return formattedStart;
        } else {
            String formattedEnd = end.format(formatter);
            return formattedStart + " - " + formattedEnd;
        }
    }

    /**
     * Returns a comma-separated list of students.
     * @return
     */
    public String getStudents() {
        return StringUtil.joinArray(this.students, ", ");
    }

    /**
     * Returns the subject.
     * @return
     */
    public String getSubject() {
        return serializeSubject(); // TODO - change to something more concrete
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

    /**
     * Returns true if this Lesson's start and end date are the same.
     * @return
     */
    private boolean isSameDay() {
        return start.toLocalDate().isEqual(end.toLocalDate());
    }
}
