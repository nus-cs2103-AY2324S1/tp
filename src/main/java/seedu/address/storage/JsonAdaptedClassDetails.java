package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.ClassDetails;
import seedu.address.model.student.grades.AssignmentTracker;
import seedu.address.model.student.grades.AttendanceTracker;
import seedu.address.model.student.grades.ClassParticipationTracker;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link ClassDetails}.
 */
public class JsonAdaptedClassDetails {

    private final String classNumber;
    private final List<Boolean> attendanceTracker = new ArrayList<>();
    private final List<Integer> assignmentTracker = new ArrayList<>();
    private final List<Boolean> classParticipationTracker = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClassDetails} with the given {@code classDetails}.
     */
    @JsonCreator
    public JsonAdaptedClassDetails(@JsonProperty("classNumber") String classNumber,
                                   @JsonProperty("attendanceTracker") List<Boolean> attendanceTracker,
                                   @JsonProperty("assignmentTracker") List<Integer> assignmentTracker,
                                   @JsonProperty("classParticipationTracker")
                                       List<Boolean> classParticipationTracker) {
        this.classNumber = classNumber;
        if (attendanceTracker != null) {
            this.attendanceTracker.addAll(attendanceTracker);
        }
        if (assignmentTracker != null) {
            this.assignmentTracker.addAll(assignmentTracker);
        }
        if (classParticipationTracker != null) {
            this.classParticipationTracker.addAll(classParticipationTracker);
        }
    }

    /**
     * Converts this Jackson-friendly adapted ClassDetails object into the model's {@code ClassDetails} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ClassDetails.
     */
    public ClassDetails toModelType() throws IllegalValueException {
        AssignmentTracker assignmentTracker = new AssignmentTracker(this.assignmentTracker);
        AttendanceTracker attendanceTracker = new AttendanceTracker(this.attendanceTracker);
        ClassParticipationTracker classParticipationTracker = new ClassParticipationTracker(
                this.classParticipationTracker);
        return new ClassDetails(classNumber, attendanceTracker, assignmentTracker, classParticipationTracker);
    }

}
