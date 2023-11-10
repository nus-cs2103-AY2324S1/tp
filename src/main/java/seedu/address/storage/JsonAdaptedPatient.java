package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Patient}.
 */
public class JsonAdaptedPatient extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String emergencyContact;
    private final String condition;
    private final String bloodType;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("remark") String remark, @JsonProperty("gender") String gender,
                              @JsonProperty("ic") String ic, @JsonProperty("tags") List<JsonAdaptedTag> tags,
                              @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
                              @JsonProperty("condition") String condition, @JsonProperty("bloodType") String bloodType,
                              @JsonProperty("emergencyContact") String emergencyContact) {
        super(name, phone, email, address, remark, gender, ic, appointments, tags);
        this.condition = condition;
        this.bloodType = bloodType;
        this.emergencyContact = emergencyContact;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        super(source);
        this.emergencyContact = source.getEmergencyContact().value;
        this.bloodType = source.getBloodType().value;
        this.condition = source.getCondition().value;
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Patient toModelType() throws IllegalValueException {
        final Name modelName = checkName();
        final Phone modelPhone = checkPhone();
        final Phone modelEmergencyContact = checkEmergencyContact();
        final Email modelEmail = checkEmail();
        final Address modelAddress = checkAddress();
        final Remark modelRemark = checkRemark();
        final Gender modelGender = checkGender();
        final Ic modelIc = checkIc();
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : this.getTags()) {
            personTags.add(tag.toModelPatientType());
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Condition modelCondition = checkCondition();
        final BloodType modelBloodType = checkBloodType();
        final List<Appointment> personAppointments = checkAppointments();
        final Set<Appointment> modelAppointments = new HashSet<>(personAppointments);
        return new Patient(modelName, modelPhone, modelEmergencyContact, modelEmail, modelAddress, modelRemark,
                modelGender, modelIc, modelCondition, modelBloodType, modelAppointments, modelTags);
    }

    /**
     * Checks the emergency contact given by storage.
     *
     * @return a valid Phone object.
     * @throws IllegalValueException if emergency contact is not valid.
     */
    private Phone checkEmergencyContact() throws IllegalValueException {
        if (emergencyContact == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(emergencyContact)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(emergencyContact);
    }

    /**
     * Checks the condition given by storage.
     *
     * @return a valid Condition object.
     * @throws IllegalValueException if condition is not valid.
     */
    private Condition checkCondition() throws IllegalValueException {
        if (condition == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Condition.class.getSimpleName()));
        }
        if (!Condition.isValidCondition(condition)) {
            throw new IllegalValueException(Condition.MESSAGE_CONSTRAINTS);
        }
        return new Condition(condition);
    }

    /**
     * Checks the bloodType given by storage.
     *
     * @return a valid bloodType object.
     * @throws IllegalValueException if bloodType is not valid.
     */
    private BloodType checkBloodType() throws IllegalValueException {
        if (bloodType == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName()));
        }
        if (!BloodType.isValidBloodType(bloodType)) {
            throw new IllegalValueException(BloodType.MESSAGE_CONSTRAINTS);
        }
        return new BloodType(bloodType);
    }

}
