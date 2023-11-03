package seedu.address.ui;

import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;

public class GroupTimeContainer {
    private final Group group;

    private final TimeInterval timeInterval;

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
        return this.group.equals(otherGrpTimeContainer.group) &&
            this.timeInterval.equals(otherGrpTimeContainer.timeInterval);
    }

}
