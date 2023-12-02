package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Company;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Remark;
import seedu.address.model.company.Role;

/**
 * Jackson-friendly version of {@link Company}.
 */
class JsonAdaptedCompany {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Company's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String role;
    private final String deadline;
    private final String status;
    private final String recruiterName;
    private final String priority;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedCompany} with the given company details.
     */
    @JsonCreator
    public JsonAdaptedCompany(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("role") String role,
                              @JsonProperty("deadline") String deadline, @JsonProperty("status") String status,
                              @JsonProperty("recruiterName") String recruiterName,
                              @JsonProperty("priority") String priority, @JsonProperty("remark") String remark) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.recruiterName = recruiterName;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Company} into this class for Jackson use.
     */
    public JsonAdaptedCompany(Company source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        role = source.getRole().jobRole;
        deadline = source.getDeadline().toString();
        status = source.getStatus().toString();
        recruiterName = source.getRecruiterName().fullName;
        priority = source.getPriority().priority;
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted company object into the model's {@code Company} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted company.
     */
    public Company toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS_INVALID_REGEX);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS_VALID_REGEX);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS_VALID_REGEX);
        }
        final Email modelEmail = new Email(email);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS_INVALID_REGEX);
        }
        final Role modelRole = new Role(role);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS_INVALID_DEADLINE);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationStatus.class.getSimpleName()));
        }
        if (!ApplicationStatus.isValidApplicationStatus(status)) {
            throw new IllegalValueException(ApplicationStatus.MESSAGE_CONSTRAINTS_VALID_STATUS);
        }
        final ApplicationStatus modelStatus = new ApplicationStatus(status);

        if (recruiterName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RecruiterName.class.getSimpleName()));
        }
        if (!RecruiterName.isValidName(recruiterName)) {
            throw new IllegalValueException(RecruiterName.MESSAGE_CONSTRAINTS_INVALID_REGEX);
        }
        final RecruiterName modelRecruiterName = new RecruiterName(recruiterName);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS_VALID_REGEX);
        }
        final Priority modelPriority = new Priority(priority);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }

        final Remark modelRemark = new Remark(remark);

        return new Company(modelName, modelPhone, modelEmail, modelRole, modelDeadline, modelStatus,
                modelRecruiterName, modelPriority, modelRemark);
    }

}
