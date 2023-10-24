package seedu.address.model.group;

import javafx.collections.ObservableList;

public class GroupList {

    ObservableList<Group> groupList;

    /**
     * Only add group if it does not exist in the list
     * @param groupName name of the group to be added
     * @return the group that was added
     */
    public Group createNewGroup(String groupName) {
        for (Group group: groupList) {
            if (group.getGroupName().equals(groupName)) {
                return group;
            }
        }
        Group newGroup = new Group(groupName);
        groupList.add(newGroup);
        return newGroup;
    }

    public void deleteGroup(Group group) {
        groupList.remove(group);
    }
}
