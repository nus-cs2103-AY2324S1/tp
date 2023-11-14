package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Name;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
class JsonAdaptedAssignment {

    private final String assignmentName;
    private final String score;
    private final String maxScore;

    /**
     * Constructs a {@code JsonAdaptedAssignment}.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("assignmentName") String assignmentName,
                                 @JsonProperty("score") String score,
                                 @JsonProperty("maxScore") String maxScore) {
        this.assignmentName = (new Name(assignmentName)).fullName;
        this.score = score;
        this.maxScore = maxScore;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        assignmentName = source.name.fullName;
        score = String.valueOf(source.getScore());
        maxScore = String.valueOf(source.getMaxScore());
    }


    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (!Assignment.isValidAssignment(new Name(assignmentName))) {
            throw new IllegalValueException(Assignment.MESSAGE_CONSTRAINTS);
        }
        if (!Assignment.isValidScore(Integer.valueOf(score), Integer.valueOf(maxScore))) {
            throw new IllegalValueException(Assignment.MESSAGE_INVALID_SCORE);
        }
        Assignment newAssignment = new Assignment(new Name(assignmentName), Integer.valueOf(maxScore));
        newAssignment.setScore(Integer.valueOf(score));
        return newAssignment;
    }
}
