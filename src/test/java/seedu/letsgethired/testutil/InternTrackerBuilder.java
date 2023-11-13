package seedu.letsgethired.testutil;

import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.application.InternApplication;

/**
 * A utility class to help with building InternTracker objects.
 * Example usage: <br>
 *     {@code InternTracker ab = new InternTrackerBuilder().withInternApplication(internApplication).build();}
 */
public class InternTrackerBuilder {

    private InternTracker internTracker;

    public InternTrackerBuilder() {
        internTracker = new InternTracker();
    }

    public InternTrackerBuilder(InternTracker internTracker) {
        this.internTracker = internTracker;
    }

    /**
     * Adds a new {@code InternApplication} to the {@code InternTracker} that we are building.
     */
    public InternTrackerBuilder withInternApplication(InternApplication internApplication) {
        internTracker.addApplication(internApplication);
        return this;
    }

    public InternTracker build() {
        return internTracker;
    }
}
