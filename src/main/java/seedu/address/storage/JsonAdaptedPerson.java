package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.LicencePlate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.policy.Company;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDate;
import seedu.address.model.policy.PolicyNumber;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String nric;
    private final String licencePlate;
    private final String company;
    private final String policyNumber;
    private final String policyIssueDate;
    private final String policyExpiryDate;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("nric") String nric,
            @JsonProperty("licencePlate") String licencePlate, @JsonProperty("company") String company,
            @JsonProperty("policyNumber") String policyNumber,
            @JsonProperty("policyIssueDate") String policyIssueDate,
            @JsonProperty("policyExpiryDate") String policyExpiryDate) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.nric = nric;
        this.licencePlate = licencePlate;
        this.company = company;
        this.policyNumber = policyNumber;
        this.policyIssueDate = policyIssueDate;
        this.policyExpiryDate = policyExpiryDate;
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
        nric = source.getNric().value;
        licencePlate = source.getLicencePlate().value;
        Policy sourcePolicy = source.getPolicy();
        company = sourcePolicy.getCompany().value;
        policyNumber = sourcePolicy.getPolicyNumber().value;
        policyIssueDate = sourcePolicy.getPolicyIssueDate().toString();
        policyExpiryDate = sourcePolicy.getPolicyExpiryDate().toString();
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

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (licencePlate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LicencePlate.class.getSimpleName()));
        }
        if (!LicencePlate.isValidLicencePlate(licencePlate)) {
            System.out.println(LicencePlate.MESSAGE_CONSTRAINTS);
            throw new IllegalValueException(LicencePlate.MESSAGE_CONSTRAINTS);
        }
        final LicencePlate modelLicencePlate = new LicencePlate(licencePlate);

        // Policy fields
        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (policyNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyNumber.class.getSimpleName()));
        }
        if (!PolicyNumber.isValidPolicyNumber(policyNumber)) {
            throw new IllegalValueException(PolicyNumber.MESSAGE_CONSTRAINTS);
        }
        final PolicyNumber modelPolicyNumber = new PolicyNumber(policyNumber);

        if (policyIssueDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyDate.class.getSimpleName()));
        }
        if (!PolicyDate.isValidPolicyDate(policyIssueDate)) {
            throw new IllegalValueException(PolicyDate.MESSAGE_CONSTRAINTS);
        }
        final PolicyDate modelPolicyIssueDate = new PolicyDate(policyIssueDate);

        if (policyExpiryDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyDate.class.getSimpleName()));
        }
        if (!PolicyDate.isValidPolicyDate(policyExpiryDate)) {
            throw new IllegalValueException(PolicyDate.MESSAGE_CONSTRAINTS);
        }
        final PolicyDate modelPolicyExpiryDate = new PolicyDate(policyExpiryDate);

        final Policy modelPolicy = new Policy(modelCompany,modelPolicyNumber, modelPolicyIssueDate, modelPolicyExpiryDate);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                modelNric, modelLicencePlate, modelPolicy);
    }

}
