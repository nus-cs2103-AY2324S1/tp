package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;
import seedu.address.model.schedule.TutorIndex;

/**
 * A utility class to help with building Schedule objects.
 */
public class ScheduleBuilder {
    public static final Integer DEFAULT_TUTOR_INDEX = 1;
    public static final LocalDateTime DEFAULT_START_TIME = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    public static final LocalDateTime DEFAULT_END_TIME = LocalDateTime.of(2023, 1, 2, 0, 0, 0);
    private TutorIndex tutorIndex;
    private StartTime startTime;
    private EndTime endTime;

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        tutorIndex = new TutorIndex(DEFAULT_TUTOR_INDEX);
        startTime = new StartTime(DEFAULT_START_TIME);
        endTime = new EndTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the ScheduleBuilder with the data of {@code scheduleToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        tutorIndex = scheduleToCopy.getTutorIndex();
        startTime = scheduleToCopy.getStartTime();
        endTime = scheduleToCopy.getEndTime();
    }

    /**
     * Sets the {@code TutorIndex} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTutorIndex(Integer tutorIndex) {
        this.tutorIndex = new TutorIndex(tutorIndex);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withStartTime(LocalDateTime startTime) {
        this.startTime = new StartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withEndTime(LocalDateTime endTime) {
        this.endTime = new EndTime(endTime);
        return this;
    }

    public Schedule build() {
        return new Schedule(tutorIndex, startTime, endTime);
    }
}
