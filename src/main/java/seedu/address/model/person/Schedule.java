package seedu.address.model.person;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Week;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

/**
 * Encaspulates a student's tutoring schedule.
 */
public class Schedule {
    private ArrayList<Lesson> lessons = new ArrayList<>();

    /**
     * Constructor for a Schedule.
     */
    public Schedule() {

    }
    /**
     * Serializes the student's schedule
     * TODO: make this override something
     * TODO: Implementation
     */
    public String serialize() {

        return "";

    }

    public Schedule deserialize()

}
