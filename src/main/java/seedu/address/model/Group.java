package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

public class Group {
    ObservableList<Person> listOfGroupMates = FXCollections.observableArrayList();
    String groupName;

    /**
     * Name field must be present and not null.
     */
    public Group(String groupName) {
        requireNonNull(groupName);
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    /**
     * Returns true if both groups have the same name.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getGroupName().equals(getGroupName());
    }

    /**
     * Removes the person from the group.
     * The person must exist in the group.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!listOfGroupMates.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return listOfGroupMates.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        listOfGroupMates.add(toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Group name", groupName)
                .toString();
    }

}
