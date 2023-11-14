package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.ScheduleItem;
import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKinName;
import seedu.address.model.person.NextOfKinPhone;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String nextOfKinName;
    private final String nextOfKinPhone;

    private final List<JsonAdaptedFinancialPlan> financialPlans = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final JsonAdaptedAppointment appointment;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("nextOfKinName") String nextOfKinName,
                             @JsonProperty("nextOfKinPhone") String nextOfKinPhone,
                             @JsonProperty("financialPlans") List<JsonAdaptedFinancialPlan> financialPlans,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("appointment") JsonAdaptedAppointment appointment) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nextOfKinName = nextOfKinName;
        this.nextOfKinPhone = nextOfKinPhone;
        if (financialPlans != null) {
            this.financialPlans.addAll(financialPlans);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.appointment = appointment;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        nextOfKinName = source.getNextOfKinName().fullName;
        nextOfKinPhone = source.getNextOfKinPhone().value;
        financialPlans.addAll(source.getFinancialPlans().stream()
                .map(JsonAdaptedFinancialPlan::new)
                .collect(Collectors.toList()));
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        appointment = new JsonAdaptedAppointment(source.getAppointment().toString());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<FinancialPlan> personFinancialPlans = new ArrayList<>();
        for (JsonAdaptedFinancialPlan plan : financialPlans) {
            personFinancialPlans.add(plan.toModelType());
        }
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            logger.warning("Invalid Name: " + name);
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            logger.warning("Invalid Phone: " + phone);
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            logger.warning("Invalid Email: " + email);
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            logger.warning("Invalid Address: " + address);
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (nextOfKinName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NextOfKinName.class.getSimpleName()));
        }
        if (!NextOfKinName.isValidName(nextOfKinName)) {
            logger.warning("Invalid Next of Kin Name: " + nextOfKinName);
            throw new IllegalValueException(NextOfKinName.MESSAGE_CONSTRAINTS);
        }
        final NextOfKinName modelNextOfKinName = new NextOfKinName(nextOfKinName);

        if (nextOfKinPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NextOfKinPhone.class.getSimpleName()));
        }
        if (!NextOfKinPhone.isValidPhone(nextOfKinPhone)) {
            logger.warning("Invalid Next of Kin Phone: " + nextOfKinPhone);
            throw new IllegalValueException(NextOfKinPhone.MESSAGE_CONSTRAINTS);
        }
        final NextOfKinPhone modelNextOfKinPhone = new NextOfKinPhone(nextOfKinPhone);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<FinancialPlan> modelFinancialPlans = new HashSet<>(personFinancialPlans);

        if (appointment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Appointment.class.getSimpleName()));
        }

        final ScheduleItem modelAppointment = appointment.toModelType();

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelNextOfKinName, modelNextOfKinPhone,
                modelFinancialPlans, modelTags, modelAppointment);

    }

}
