package seedu.address.storage;

import static seedu.address.model.util.SerializeUtil.deserialize;
import static seedu.address.model.util.SerializeUtil.serialize;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lessons.Day;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.TaskList;
import seedu.address.model.lessons.Time;
import seedu.address.model.person.Name;
import seedu.address.model.person.Subject;


/**
 * Jackson-friendly version of {@link Lesson}
 */
public class JsonAdaptedLesson {
    private final String start;
    private final String end;
    private final String day;
    private final String subject;
    private final String name;
    private final List<JsonAdaptedTask> taskList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("name") String name,
                             @JsonProperty("start") String start,
                             @JsonProperty("end") String end,
                             @JsonProperty("day") String day,
                             @JsonProperty("subject") String subject,
                             @JsonProperty("remarks") String remark,
                             @JsonProperty("taskList") List<JsonAdaptedTask> taskList) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.day = day;
        this.subject = subject;
        if (taskList != null) {
            this.taskList.addAll(taskList);
        }
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        name = serialize(source.getName());
        start = serialize(source.getStart());
        end = serialize(source.getEnd());
        day = serialize(source.getDay());
        subject = serialize(source.getSubject());
        taskList.addAll(source.getTaskListClone().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }
    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        try {
            Name name = deserialize(Name.DEFAULT_NAME, Name::of, this.name);
            Time start = deserialize(Time.DEFAULT_TIME, Time::deserialize, this.start);
            Time end = deserialize(Time.DEFAULT_TIME, Time::deserialize, this.end);
            Day day = deserialize(Day.DEFAULT_DAY, Day::deserialize, this.day);
            Subject subject = deserialize(Subject.DEFAULT_SUBJECT, Subject::of, this.subject);
            TaskList taskList = TaskList.of(this.taskList);

            return new Lesson(name, start, end, day, subject, taskList);
        } catch (Exception e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
