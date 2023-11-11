package seedu.address.ui.util;

import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;

/**
 * A helper class to create a object that contains a group and a single timeInterval
 */
public class GroupTimeContainer {
    private final Group group;

    private final TimeInterval timeInterval;

    /**
     * Constructor for GroupTimeContainer
     */
    public GroupTimeContainer(Group group, TimeInterval timeInterval) {
        this.group = group;
        this.timeInterval = timeInterval;
    }

    public Group getGroup() {
        return group;
    }

    public TimeInterval getTimeInterval() {
        return timeInterval;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof GroupTimeContainer)) {
            return false;
        }
        GroupTimeContainer otherGrpTimeContainer = (GroupTimeContainer) other;
        return this.group.equals(otherGrpTimeContainer.group)
            && this.timeInterval.equals(otherGrpTimeContainer.timeInterval);
    }

}
