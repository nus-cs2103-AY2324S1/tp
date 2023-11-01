package seedu.address.model.lessons;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntry;
import seedu.address.model.person.Name;
import seedu.address.model.person.Subject;

/**
 * Represents a student's lesson in the schedule.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson extends ListEntry<Lesson> {
    public static final Lesson DEFAULT_LESSON = new Lesson();
    private Time start;
    private Time end;
    private Name name;
    // Data fields
    private Subject subject;
    private Day day;
    private TaskList taskList;

    /**
     * Constructor for a Lesson Object with at least one student.
     * Note: parse the string before giving it to the constructor.
     *
     * @param start The start time of the lesson
     * @param end The end time of the lesson
     * @param subject The subject of this lesson
     * @param taskList the tasks to be completed
     * @param studentNames The student attending this lesson. Note: Converted to ArrayList when stored
     * @see seedu.address.logic.parser.ParserUtil
     */
    public Lesson(Name name, Time start, Time end, Day day, Subject subject, TaskList taskList, Name... studentNames) {
        requireAllNonNull(name, start, end, day, subject, studentNames);
        this.name = name;
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.day = day;
        this.taskList = taskList;
    }

    /**
     * Used to construct sample data.
     * @param name
     * @param start
     * @param end
     * @param day
     * @param subject
     * @param taskList
     * @throws ParseException
     */
    public Lesson(String name, String start, String end, String day, String subject, TaskList taskList)
            throws ParseException {
        this(new Name(name), new Time(start), new Time(end), Day.of(day), new Subject(subject), taskList);
    }
    private Lesson() {
        this.name = Name.DEFAULT_NAME;
        this.start = Time.DEFAULT_TIME;
        this.end = Time.DEFAULT_TIME;
        this.subject = Subject.DEFAULT_SUBJECT;
        this.day = Day.DEFAULT_DAY;
        this.taskList = TaskList.DEFAULT_TASKLIST;
    }

    public static Lesson getDefaultLesson() {
        return DEFAULT_LESSON.clone();
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setDayIfNotDefault(Day day) {
        if (day != null && !day.equals(Day.DEFAULT_DAY)) {
            setDay(day);
        }
    }

    /**
     * Gets a one-line overview of the lesson.
     *
     * If a lesson is on Tuesday, 10 Oct 10 am - 12 pm, it will be formatted as:
     *
     * 10 am - 12 pm
     * @return A formatted overview of the time of the lesson
     */
    public String getLessonDurationStr() {
        return start + " - " + end;
    }

    /**
     * Gets the date of a lesson.
     * If the start and end date are the same, then only one date string is returned.
     * Else, the date will be returned as: [start date] - [end date]
     * @return
     */
    public String getLessonNameStr() {
        return name.toString();
    }

    /**
     * Gets the name of a lesson.
     * @return
     */
    public String getLessonDateStr() {
        return day.toString();
    }

    /**
     * Returns a comma-separated list of students.
     * @return
     */
    public String getStudentsStr() {
        return "getStudentsStr in lesson is to be implemented";
        //todo, get the students str from model in the future instead of from the lesson
    }

    public Time getStart() {
        return start;
    }
    public void setStart(Time start) {
        if (end != Time.DEFAULT_TIME && start.isAfter(end)) {
            throw new IllegalArgumentException("Start time cannot be after end time");
        }
        this.start = start;
    }
    public void setStartIfNotDefault(Time start) {
        if (start != null && !start.equals(Time.DEFAULT_TIME)) {
            setStart(start);
        }
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        if (start != Time.DEFAULT_TIME && end.isBefore(start)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }
        this.end = end;
    }
    public void setEndIfNotDefault(Time end) {
        if (end != null && !end.equals(Time.DEFAULT_TIME)) {
            setEnd(end);
        }
    }

    /**
     * Updates the start and end time of the lesson if the start is before the end after the update.
     */
    public void updateStartAndEnd(Time start, Time end) throws ParseException {
        if (start == Time.DEFAULT_TIME) {
            start = this.start;
        }
        if (end == Time.DEFAULT_TIME) {
            end = this.end;
        }
        if (start != Time.DEFAULT_TIME && end != Time.DEFAULT_TIME && start.isAfter(end)) {
            throw new ParseException("End time cannot be before start time");
        }
        this.start = start;
        this.end = end;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public void setSubjectIfNotDefault(Subject subject) {
        if (subject != null && !subject.equals(Subject.DEFAULT_SUBJECT)) {
            setSubject(subject);
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns the list of tasks as a set.
     * @return
     */
    public Set<Task> getTasksSet() {
        return taskList.getTaskSetClone();
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
    public void setNameIfNotDefault(Name name) {
        if (name != null && !name.equals(Name.DEFAULT_NAME)) {
            setName(name);
        }
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
                && name.equals(otherLesson.name)
                && day.equals(otherLesson.day)
                && remark.equals(otherLesson.remark);
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, start, end, day, subject);
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     * @param otherLesson The other lesson to compare with
     * @return true if the lessons clash
     */
    public boolean isClashWith(Lesson otherLesson) {
        requireAllNonNull(otherLesson);
        if (otherLesson == this) {
            return true;
        }
        if (this.day == Day.DEFAULT_DAY || otherLesson.getDay() == Day.DEFAULT_DAY) {
            return false;
        }
        if (!this.day.equals(otherLesson.getDay())) {
            return false;
        }
        if (this.start == Time.DEFAULT_TIME || otherLesson.getStart() == Time.DEFAULT_TIME) {
            return false;
        }
        if (this.end == Time.DEFAULT_TIME || otherLesson.getEnd() == Time.DEFAULT_TIME) {
            return false;
        }
        if (otherLesson.getStart().isBefore(this.getStart())) {
            return otherLesson.isClashWith(this);
        }
        return this.getEnd().isAfter(otherLesson.getStart());
    }
    @Override
    public String toString() {
        String subjectStr = subject == null
                             ? ""
                             : " for " + subject;
        return "Lesson from " + start + " to " + end + subjectStr;
    }

    /**
     * Returns a clone of the lesson.
     */
    @Override
    public Lesson clone() {
        Lesson cloned = new Lesson();
        cloned.setStartIfNotDefault(this.start);
        cloned.setEndIfNotDefault(this.end);
        cloned.setSubjectIfNotDefault(this.subject);
        cloned.setNameIfNotDefault(this.name);
        cloned.setDayIfNotDefault(this.day);
        cloned.setRemarkIfNotDefault(this.remark);
        return cloned;
    }
}
