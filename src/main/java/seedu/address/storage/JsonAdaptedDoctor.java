package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Person;


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
     * Converts a given {@code Person} into this class for Jackson use.
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
        Person p = super.toModelType();

        Doctor modelDoctor = new Doctor(p.getName(), p.getPhone(), p.getEmail(), p.getAddress(), p.getRemark(),
                p.getGender(), p.getIc(), p.getAppointments(), p.getTags());
        return modelDoctor;
    }
}
