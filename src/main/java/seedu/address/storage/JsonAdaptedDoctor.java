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
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Doctor}.
 */
public class JsonAdaptedDoctor extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Doctor's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedDoctor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("remark") String remark, @JsonProperty("gender") String gender,
                             @JsonProperty("nric") String ic, @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        super(name, phone, email, address, remark, gender, ic, appointments, tags);
    }

    /**
     * Converts a given {@code Doctor} into this class for Jackson use.
     */
    public JsonAdaptedDoctor(Doctor source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Doctor toModelType() throws IllegalValueException {
        final Name modelName = checkName();
        final Phone modelPhone = checkPhone();
        final Email modelEmail = checkEmail();
        final Address modelAddress = checkAddress();
        final Remark modelRemark = checkRemark();
        final Gender modelGender = checkGender();
        final Ic modelIc = checkIc();
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : this.getTags()) {
            personTags.add(tag.toModelDoctorType());
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final List<Appointment> personAppointments = checkAppointments();
        final Set<Appointment> modelAppointments = new HashSet<>(personAppointments);
        return new Doctor(modelName, modelPhone, modelEmail, modelAddress, modelRemark, modelGender, modelIc,
                modelAppointments, modelTags);
    }
}
