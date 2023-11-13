package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.fields.InterviewTime;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;

/**
 * Jackson-friendly version of {@link Applicant}.
 */
public class JsonAdaptedApplicant {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Applicant's %s field is missing!";

    private final String name;
    private final String phone;
    private final String interviewTime;

    /**
     * Constructs a {@code JsonAdaptedApplicant} with the given applicant details.
     */
    @JsonCreator
    public JsonAdaptedApplicant(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
        @JsonProperty("interviewTime") String interviewTime) {
        this.name = name;
        this.phone = phone;
        this.interviewTime = interviewTime == null ? "cancel" : interviewTime;
    }

    /**
     * Converts a given {@code Applicant} into this class for Jackson use.
     */
    public JsonAdaptedApplicant(Applicant source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        interviewTime = source.getInterviewTime().getTime() == null ? "cancel" : source.getInterviewTime().getTime();
    }

    /**
     * Converts this Jackson-friendly adapted applicant object into the model's {@code Applicant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted applicant.
     */
    public Applicant toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }

        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }

        final Phone modelPhone = new Phone(phone);

        if (!InterviewTime.isValidTime(interviewTime)) {
            throw new IllegalValueException(InterviewTime.MESSAGE_CONSTRAINTS);
        }

        final InterviewTime interviewTime = new InterviewTime(this.interviewTime);

        return new Applicant(modelName, modelPhone, interviewTime);
    }
}
