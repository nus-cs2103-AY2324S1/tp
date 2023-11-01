package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Details;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Person.PersonBuilder;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Profession;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String PERSON_MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    @JsonInclude(Include.NON_NULL)
    private final String lead;

    @JsonInclude(Include.NON_NULL)
    private final String telegram;

    @JsonInclude(Include.NON_NULL)
    private final String profession;

    @JsonInclude(Include.NON_NULL)
    private final String income;

    @JsonInclude(Include.NON_NULL)
    private final String details;
    private final List<JsonAdaptedInteraction> interactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("lead") String lead,
            @JsonProperty("telegram") String telegram, @JsonProperty("profession") String profession,
            @JsonProperty("income") String income, @JsonProperty("details") String details,
            @JsonProperty("interactions") List<JsonAdaptedInteraction> interactions) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }

        // Optional fields
        this.lead = lead;
        this.telegram = telegram;
        this.profession = profession;
        this.income = income;
        this.details = details;

        if (interactions != null) {
            this.interactions.addAll(interactions);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

        // Optional fields
        lead = source.getLead() == null ? null : source.getLead().toString();
        telegram = source.getTelegram() == null ? null : source.getTelegram().toString();
        profession = source.getProfession() == null ? null : source.getProfession().toString();
        income = source.getIncome() == null ? null : source.getIncome().toString();
        details = source.getDetails() == null ? null : source.getDetails().toString();

        interactions.addAll(source.getInteractions().stream()
            .map(JsonAdaptedInteraction::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final List<Interaction> modelInteractions = new ArrayList<>();
        for (JsonAdaptedInteraction interaction : interactions) {
            modelInteractions.add(interaction.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT,
                                            Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT,
                                            Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT,
                                            Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT,
                                            Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        PersonBuilder personBuilder = new PersonBuilder(modelName, modelPhone, modelEmail, modelAddress, modelTags);

        // Optional fields
        if (lead != null) {
            if (!Lead.isValidLead(lead)) {
                throw new IllegalValueException(String.format(Lead.MESSAGE_CONSTRAINTS));
            }
            personBuilder = personBuilder.withLead(new Lead(lead));
        }
        if (telegram != null) {
            if (!TelegramHandle.isValidTelegramHandle(telegram)) {
                throw new IllegalValueException(String.format(TelegramHandle.MESSAGE_CONSTRAINTS));
            }
            personBuilder = personBuilder.withTelegram(new TelegramHandle(telegram));
        }
        if (profession != null) {
            if (!Profession.isValidProfession(profession)) {
                throw new IllegalValueException(String.format(Profession.MESSAGE_CONSTRAINTS));
            }
            personBuilder = personBuilder.withProfession(new Profession(profession));
        }
        if (income != null) {
            if (!Income.isValidIncome(income)) {
                throw new IllegalValueException(String.format(Income.MESSAGE_CONSTRAINTS));
            }
            personBuilder = personBuilder.withIncome(new Income(Integer.valueOf(income)));
        }
        if (details != null) {
            if (!Details.isValidDetails(details)) {
                throw new IllegalValueException(String.format(Details.MESSAGE_CONSTRAINTS));
            }
            personBuilder = personBuilder.withDetails(new Details(details));
        }

        personBuilder.withInteractions(modelInteractions);
        return personBuilder.build();
    }
}
