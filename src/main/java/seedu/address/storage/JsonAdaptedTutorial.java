package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Course;
import seedu.address.model.person.Tutorial;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
class JsonAdaptedTutorial {

    private final String tutorialString;

    /**
     * Constructs a {@code JsonAdaptedTutorial} with the given {@code courseName} and
     * {@code tutorialName}. Course will be linked when converting to a modelType.
     */
    @JsonCreator
    public JsonAdaptedTutorial(String tutorialString) {
        this.tutorialString = tutorialString;
    }

    /**
     * Converts a given {@code Tutorial} into this class for Jackson use. Course will be linked when converting
     * to a modelType.
     */
    public JsonAdaptedTutorial(Tutorial source) {
        tutorialString = source.getFullTutorialString();
    }

    @JsonValue
    public String getTutorialString() {
        return tutorialString;
    }

    public Course getCourse() {
        return new Course(tutorialString.split(Tutorial.COURSE_TUTORIAL_DELIMITER)[0]);
    }

    /**
     * Converts this Jackson-friendly adapted tutorial object into the model's {@code Tutorial} object.
     * This requires a reference to an already-converted course to maintain a reference.
     *
     * @param course the course to refer to.
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutorial.
     */
    public Tutorial toModelType(Course course) throws IllegalValueException {
        if (!Tutorial.isValidTutorialString(tutorialString)) {
            throw new IllegalValueException(Tutorial.MESSAGE_CONSTRAINTS);
        }
        return new Tutorial(course, tutorialString);
    }

}
