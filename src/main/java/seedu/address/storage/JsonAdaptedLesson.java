package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Jackson-friendly version of {@link seedu.address.model.lessons.Lesson}
 */
public class JsonAdaptedLesson {
    private final String start;
    private final String end;
    private final String subject;
    private final String students; // comma-separated

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("start") String start, @JsonProperty("end") String end,
                             @JsonProperty("subject") String subject, @JsonProperty("students") String students) {
       this.start = start;
       this.end = end;
       this.subject = subject;
       this.students = students;

    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        start = source.serializeStart();
        end = source.serializeEnd();
        subject = source.serializeSubject();
        students = source.serializeStudents();
    }

    public Lesson toModelType() throws IllegalValueException {
        LocalDateTime start = Lesson.deserializeDate(this.start);
        LocalDateTime end = Lesson.deserializeDate(this.end);
        Subject subject = Lesson.deserializeSubject(this.subject);
        ArrayList<String> students = Lesson.deserializeStudents(this.students);

        return new Lesson(start, end, subject, students);

    }
}
