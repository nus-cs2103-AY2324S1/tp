package seedu.address.testutil;

import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.group.GroupRemark;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS2103T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.group.Group;
import seedu.address.model.group.GroupRemark;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {
    public static final Group CS = new Group(VALID_GROUP_CS);
    public static final Group CS2100 = new Group("CS2100", new GroupRemark("Test on friday"));
    public static final Group CS2103T = new Group(VALID_GROUP_CS2103T);

    public static final Group CS2102 = new GroupBuilder().withName("CS2102")
                                        .withGroupRemark("CS2102 remark")
                                        .withListOfGroupMates("Amelia", "Benson", "Charles", "David")
                                        .withTimeIntervalList("wed 1500 - wed 1600", "mon 1500 - mon 1600").build();

    public static final Group CS2103 = new GroupBuilder().withName("CS2103")
                                        .withGroupRemark("CS2103 remark")
                                        .withListOfGroupMates("Alice Pauline", "Ben", "Cricket", "Dog").build();


    public static final Group CS2105 = new GroupBuilder().withName("CS2105")
                                        .withGroupRemark("CS2105 remark")
                                        .withListOfGroupMates("Avner", "Bernie", "Coin", "Dawson")
                                        .withTimeIntervalList("mon 1200 - mon 1400", "wed 1600 - thu 1800").build();


    public static GroupList getTypicalPGroup() {

        GroupList groupList = new GroupList();
        Group[] groupArray = {CS2100, CS2102, CS2103, CS2105};

        Arrays.stream(groupArray).forEach(groupList::add);

        return groupList;
    }


    public static List<Group> getTypicalGroup() {
        return new ArrayList<>(Arrays.asList(CS2105));
    }


}
