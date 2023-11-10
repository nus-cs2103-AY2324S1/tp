package seedu.address.testutil;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.TimeInterval;
import seedu.address.model.TimeIntervalList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.group.GroupRemark;

import java.util.Arrays;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS2103T;

public class TypicalGroups {
    public static final Group CS = new Group(VALID_GROUP_CS);
    public static final Group CS2100 = new Group("CS2100", new GroupRemark("Test on friday"));
    public static final Group CS2103T = new Group(VALID_GROUP_CS2103T);

    public static final Group CS2102 = new GroupBuilder().withName("CS2102")
            .withGroupRemark("CS2102 remark").withListOfGroupMates("Amelia", "Benson", "Charles", "David")
            .withTimeIntervalList("mon 1200 - mon 1400", "wed 1600 - thur 1800").build();

    public static final Group CS2103 = new GroupBuilder().withName("CS2103")
            .withGroupRemark("CS2103 remark").withListOfGroupMates("Annie", "Ben", "Cricket", "Dog")
            .withTimeIntervalList("mon 1200 - mon 1400", "wed 1600 - thur 1800").build();
    public static final Group CS2105 = new GroupBuilder().withName("CS2105")
            .withGroupRemark("CS2105 remark").withListOfGroupMates("Avner", "Bernie", "Coin", "Dawson")
            .withTimeIntervalList("mon 1200 - mon 1400", "wed 1600 - thur 1800").build();


    public static GroupList getTypicalPGroup() {

        GroupList groupList = new GroupList();
        Group[] groupArray = {CS2100, CS2102, CS2103, CS2105};

        Arrays.stream(groupArray).forEach(groupList::add);

        return groupList;


    }

}
