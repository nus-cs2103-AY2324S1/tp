package seedu.address.model.person;

import seedu.address.model.lessons.Lesson;

import java.util.ArrayList;


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
     * Gets the list of lessons
     * @return
     */
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }


    /**
     * Serializes the student's schedule
     * TODO: make this override something
     * TODO: Implementation
     */
    public String serialize() {

        return "";

    }


    public Schedule deserialize() {
        // TODO
        return new Schedule();
    };

}
