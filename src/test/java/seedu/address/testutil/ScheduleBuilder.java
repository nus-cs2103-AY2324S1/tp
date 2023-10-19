package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.person.Person;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;
import seedu.address.model.schedule.Status;

/**
 * A utility class to help with building Schedule objects.
 */
public class ScheduleBuilder {
    public static final Person DEFAULT_TUTOR = TypicalPersons.ALICE;
    public static final LocalDateTime DEFAULT_START_TIME = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    public static final LocalDateTime DEFAULT_END_TIME = LocalDateTime.of(2023, 1, 2, 0, 0, 0);
    private Person tutor;
    private StartTime startTime;
    private EndTime endTime;
    private Status status;

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        tutor = DEFAULT_TUTOR;
        startTime = new StartTime(DEFAULT_START_TIME);
        endTime = new EndTime(DEFAULT_END_TIME);
        status = Status.PENDING;
    }

    /**
     * Initializes the ScheduleBuilder with the data of {@code scheduleToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        tutor = scheduleToCopy.getTutor();
        startTime = scheduleToCopy.getStartTime();
        endTime = scheduleToCopy.getEndTime();
        status = scheduleToCopy.getStatus();
    }

    /**
     * Sets the {@code Person} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTutor(Person tutor) {
        this.tutor = tutor;
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

    /**
     * Sets the {@code Status} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public Schedule build() {
        return new Schedule(tutor, startTime, endTime, status);
    }
}
