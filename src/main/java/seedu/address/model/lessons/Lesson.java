package seedu.address.model.lessons;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a student's lesson
 */
public class Lesson {
    private LocalDateTime start;
    private LocalDateTime end;
    private Subject subject;

    private ArrayList<String> students;

    /**
     * Constructor for a Lesson Object
     * Note: parse the string before giving it to the constructor.
     * @see seedu.address.logic.parser.ParserUtil
     *
     * @param start The start time of the lesson
     * @param end The end time of the lesson
     * @param subject The subject of this lesson
     * @param studentName The student attending this lesson. Note: Converted to ArrayList when stored
     */
    public Lesson(LocalDateTime start, LocalDateTime end, Subject subject, String studentName) {
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
     * @see seedu.address.logic.parser.ParserUtil
     *
     * @param start The start time of the lesson
     * @param end The end time of the lesson
     * @param subject The subject of this lesson
     * @param students The student attending this lesson. Note: Converted to ArrayList when stored
     */
    public Lesson(LocalDateTime start, LocalDateTime end, Subject subject, ArrayList<String> students) {
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.students = students;
    }

    // TODO: Tasks
    // private TaskContainer tasks;

    /**
     * Serializes the start to a String
     * @return stringified version
     */
    public String serializeStart() {
        return this.start.toString();
    }
    /**
     * Serializes the start to a String
     * @return stringified version
     */
    public String serializeEnd() {
        return this.end.toString();
    }
    /**
     * Serializes the start to a String
     * @return stringified version
     */
    public String serializeSubject() {
        return this.subject.subjectName.toString(); // TODO public access
    }
    /**
     * Serializes the start to a String
     * @return stringified version
     */
    public String serializeStudents() {
        return String.join(",", this.students);
    }

    public static LocalDateTime deserializeDate(String date) throws IllegalValueException {
        return LocalDateTime.parse(date);
    }

    public static Subject deserializeSubject(String subject) throws IllegalValueException {
        return Subject.parseSubject(subject);
    }

    public static ArrayList<String> deserializeStudents(String students) throws IllegalValueException {
        // comma delimited
        return new ArrayList<>(Arrays.asList(students.split(",")));
    }
}
