package seedu.address.testutil;

import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;

/**
 * A utility class to help with building Schedule objects.
 * Example usage: <br>
 *     {@code Schedule ab = new ScheduleBuilder().withModule("CS2103T").build();}
 */
public class ScheduleBuilder {

    private Schedule schedule;

    public ScheduleBuilder() {
        schedule = new Schedule();
    }

    public ScheduleBuilder(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Adds a new {@code Module} to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withModule(Module module) {
        schedule.addModule(module);
        return this;
    }

    /**
     * Adds a new {@code Cca} to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withCca(Cca cca) {
        schedule.addCca(cca);
        return this;
    }

    /**
     * Adds a new {@code DatedEvent} to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withDatedEvent(DatedEvent datedEvent) {
        schedule.addDatedEvent(datedEvent);
        return this;
    }


    public Schedule build() {
        return schedule;
    }
}
