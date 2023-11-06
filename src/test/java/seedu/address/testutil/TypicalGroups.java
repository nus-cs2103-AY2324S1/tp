package seedu.address.testutil;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.TimeInterval;
import seedu.address.model.TimeIntervalList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupRemark;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS2103T;

public class TypicalGroups {
    public static final Group CS = new Group(VALID_GROUP_CS);
    public static final Group CS2100 = new Group("CS2100", new GroupRemark("Test on friday"));
    public static final Group CS2103T = new Group(VALID_GROUP_CS2103T);
}
