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
}
