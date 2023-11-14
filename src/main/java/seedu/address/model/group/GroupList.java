package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;

/**
 * A list containing groups
 */
public class GroupList implements Iterable<Group> {

    private final ObservableList<Group> internalList = FXCollections.observableArrayList();
    private final ObservableList<Group> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent group as the given argument.
     */
    public boolean contains(Group toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameGroup);
    }

    /**
     * Adds a group to the list.
     * The group must not already exist in the list.
     */
    public void add(Group toAdd) {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateGroupException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setGroups(List<Group> groups) {
        requireAllNonNull(groups);
        if (!groupsAreUnique(groups)) {
            throw new DuplicateGroupException();
        }

        internalList.setAll(groups);
    }

    /**
     * Returns true if {@code groups} contains only unique persons.
     */
    private boolean groupsAreUnique(List<Group> groups) {
        for (int i = 0; i < groups.size() - 1; i++) {
            for (int j = i + 1; j < groups.size(); j++) {
                if (groups.get(i).isSameGroup(groups.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Removes the equivalent group from the list.
     * The group must exist in the list.
     */
    public void remove(Group toRemove) {
        requireNonNull(toRemove);
        boolean isRemoved = internalList.remove(toRemove);
        if (!isRemoved) {
            throw new GroupNotFoundException();
        }
    }

    /**
     * Returns a group with the {@code groupName} from the GroupList.
     * @param groupName Name of group to look for
     * @return The group with the group name
     * @throws CommandException If GroupList does not contain a group with the name
     */
    public Group getGroup(String groupName) throws CommandException {
        for (Group group : this.internalList) {
            if (group.nameEquals(groupName)) {
                return group;
            }
        }
        throw new CommandException(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
    }

    /**
     * Returns a group with the {@code groupName} from the GroupList.
     * @param groupToGet Group to look for
     * @return The group with the group name
     * @throws CommandException If GroupList does not contain a group with the name
     */
    public Group getGroup(Group groupToGet) throws CommandException {
        for (Group group : this.internalList) {
            if (group.nameEquals(groupToGet.getGroupName())) {
                return group;
            }
        }
        throw new CommandException(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
    }

    /**
     * Converts the internal list to streams.
     *
     * @return Internal list into streams.
     */
    public Stream<Group> toStream() {
        return internalList.stream();
    }

    @Override
    public Iterator<Group> iterator() {
        return internalList.iterator();
    }

    public ObservableList<Group> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupList)) {
            return false;
        }

        GroupList otherGroupList = (GroupList) other;
        return internalList.equals(otherGroupList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

}
