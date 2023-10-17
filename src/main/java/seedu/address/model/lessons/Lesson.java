package seedu.address.model.lessons;

import seedu.address.model.person.Person;
import seedu.address.model.person.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a student's lesson
 */
public class Lesson {
    private LocalDateTime start;
    private LocalDateTime end;
    private Subject subject;

    private ArrayList<Person> students;

    /**
     * Constructor for a Lesson Object
     * Note: parse the string before giving it to the constructor.
     * @see seedu.address.logic.parser.ParserUtil
     *
     * @param start The start time of the lesson
     * @param end The end time of the lesson
     * @param subject The subject of this lesson
     * @param student The student attending this lesson. Note: Converted to ArrayList when stored
     */
    public Lesson(LocalDateTime start, LocalDateTime end, Subject subject, Person student) {
        this.start = start;
        this.end = end;
        this.subject = subject;
        ArrayList<Person> p = new ArrayList<>();
        p.add(student);
        this.students = p;
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
        return ""; // TODO: serialzie as string
    }
}
