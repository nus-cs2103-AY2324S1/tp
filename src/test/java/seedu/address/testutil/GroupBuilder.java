package seedu.address.testutil;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TimeIntervalList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupRemark;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {
    public static final String DEFAULT_GROUP_NAME = "CS2103T";
    public static final String DEFAULT_REMARK = " ";

    private String groupName;
    private GroupRemark groupRemark;
    private ObservableList<Person> listOfGroupMates;
    private TimeIntervalList timeIntervalList;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public GroupBuilder() {
        groupName = DEFAULT_GROUP_NAME;
        groupRemark = new GroupRemark(DEFAULT_REMARK);
        listOfGroupMates = FXCollections.observableArrayList();
        timeIntervalList = new TimeIntervalList();
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        groupName = groupToCopy.getGroupName();
        groupRemark = groupToCopy.getGroupRemark();
        listOfGroupMates = groupToCopy.getListOfGroupMates();
        timeIntervalList = groupToCopy.getTimeIntervalList();
    }

    /**
     * Sets the {@code groupName} of the {@code Group} that we are building.
     */
    public seedu.address.testutil.GroupBuilder withName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Group} that we are building.
     */
    public seedu.address.testutil.GroupBuilder withGroupRemark(String remark) {
        this.groupRemark = new GroupRemark(remark);
        return this;
    }

    /**
     * Sets the {@code People} of the {@code Group} that we are building.
     */
    public seedu.address.testutil.GroupBuilder withListOfGroupMates(String ...args) {
        ObservableList<Person> listOfGroupMatesCopy = FXCollections.observableArrayList();
        Arrays.stream(args).forEach(person -> listOfGroupMatesCopy.add(new PersonBuilder().withName(person).build()));
        this.listOfGroupMates = listOfGroupMatesCopy;
        return this;
    }

    /**
     * Sets the {@code People} of the {@code Group} that we are building.
     */
    public seedu.address.testutil.GroupBuilder withTimeIntervalList(String ...args) {
        TimeIntervalList tempList = new TimeIntervalList();
        Arrays.stream(args).forEach(time -> {
            try {
                tempList.addTime(ParserUtil.parseEachInterval(time));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        this.timeIntervalList = tempList;
        return this;
    }


    public Group build() {
        return new Group(groupName, groupRemark, listOfGroupMates, timeIntervalList);
    }
}

