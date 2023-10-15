package seedu.address.model.lessons;

/**
 * Encapsulates a lesson timing.
 */
public class LessonTiming {
    // minutes since start
    private int startTime;
    private int endTime;

    /**
     * Constructor for a LessonTiming
     *
     * @param startTime The start time of this lesson
     * @param endTime The end time of this lesson
     */
    public LessonTiming(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
