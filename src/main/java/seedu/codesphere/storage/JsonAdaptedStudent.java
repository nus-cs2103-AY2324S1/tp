package seedu.codesphere.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.model.student.Email;
import seedu.codesphere.model.student.Name;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Remark;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String email;
    private final StudentRank tag;
    private final String remark;
    private final String pendingQuestion;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name,
                              @JsonProperty("email") String email, @JsonProperty("remark") String remark,
                              @JsonProperty("pending question") String pendingQuestion,
                              @JsonProperty("tag") StudentRank tag) {
        this.name = name;
        this.email = email;
        this.remark = remark;
        this.pendingQuestion = pendingQuestion;
        this.tag = tag;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        remark = source.getRemark().value;
        pendingQuestion = source.getPendingQuestion().value;
        tag = source.getTag().ranking;
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }

        final Remark modelRemark = new Remark(remark);

        if (pendingQuestion == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PendingQuestion.class.getSimpleName()));
        }

        final PendingQuestion modelPendingQuestion = new PendingQuestion(pendingQuestion);

        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName()));
        }

        final Tag modelTag = new Tag(tag);

        return new Student(modelName, modelEmail, modelRemark, modelPendingQuestion, modelTag);
    }

}
