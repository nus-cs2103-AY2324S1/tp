package seedu.address.storage;

import seedu.address.model.lessons.Lesson;

/**
 * Jackson-friendly version of {@link seedu.address.model.lessons.Lesson}
 */
public class JsonAdaptedLesson {
    private final String start;
    private final String end;
    private final String subject;
    private final String students; // comma-separated

    public JsonAdaptedLesson(Lesson source) {
        start = source.serializeStart();
        end = source.serializeEnd();
        subject = source.serializeSubject();
        students = source.serializeStudents();
    }
}
