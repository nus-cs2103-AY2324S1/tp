package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.event.SingleDayEventList;

/**
 * A utility class to help with building SingleDayEventList objects.
 */
public class SingleDayEventListBuilder {
    private static final LocalDate validDate = LocalDate.of(2023, 1, 1);
    private LocalDate date;

    /**
     * Construct a SingleDayEventListBuilder object with default attributes.
     */
    public SingleDayEventListBuilder() {
        this.date = validDate;
    }

    /**
     * Change the date of the SingleDayEventListBuilder object.
     *
     * @param newDate date to be changed to.
     * @return SingleDayEventListBuilder object with updated date.
     */
    public SingleDayEventListBuilder withDate(LocalDate newDate) {
        this.date = newDate;
        return this;
    }

    /**
     * Create the SingleDayEventList object with the attributes of this SingleDayEventListBuilder object.
     *
     * @return SingleDayEventList object with date corresponding to the SingleDayEventListBuilder object's date
     *     attribute.
     */
    public SingleDayEventList build() {
        return new SingleDayEventList(date);
    }
}
