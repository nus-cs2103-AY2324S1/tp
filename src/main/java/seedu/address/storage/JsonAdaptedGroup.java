package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TimeIntervalList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupRemark;

/**
 * Jackson-friendly version of {@link Group}.
 */
class JsonAdaptedGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String groupName;
    private final String groupRemark;
    private final List<JsonAdaptedTime> meetingTimeList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given {@code groupName}.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("name") String groupName, @JsonProperty("groupRemark") String groupRemark,
                            @JsonProperty("meetingTimeList") List<JsonAdaptedTime> meetingTimeList) {
        this.groupName = groupName;
        this.groupRemark = groupRemark;

        if (meetingTimeList != null) {
            this.meetingTimeList.addAll(meetingTimeList);
        }
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        requireNonNull(source);
        groupName = source.getGroupName();
        groupRemark = source.getGroupRemark().value;

        if (meetingTimeList != null) {
            meetingTimeList.addAll(source.getTime().toStream()
                    .map(JsonAdaptedTime::new)
                    .collect(Collectors.toList()));
        }
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code JsonAdaptedGroup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Group toModelType() throws IllegalValueException {
        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Group.class.getSimpleName()));
        }
        if (!Group.isValidGroupName(groupName)) {
            throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
        }

        if (groupRemark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupRemark.class.getSimpleName()));
        }

        TimeIntervalList modelTimeIntervalListList = new TimeIntervalList();
        for (JsonAdaptedTime freeTime : meetingTimeList) {
            modelTimeIntervalListList.addTime(freeTime.toModelType());
        }

        return new Group(groupName, new GroupRemark(groupRemark), modelTimeIntervalListList);
    }

}
