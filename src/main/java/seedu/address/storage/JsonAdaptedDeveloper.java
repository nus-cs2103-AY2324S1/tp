package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Date;
import seedu.address.model.commons.Name;
import seedu.address.model.developer.Developer;
import seedu.address.model.developer.DeveloperRoles;
import seedu.address.model.developer.GithubId;
import seedu.address.model.developer.Rating;
import seedu.address.model.developer.Salary;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedDeveloper {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Developer's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;

    private final String role;

    private final List<String> projects = new ArrayList<>();
    private final String salary;
    private final String dateJoined;
    private final String githubId;
    private final String rating;

    /**
     * Constructs a {@code JsonAdaptedDeveloper} with the given data.
     *
     * @param name       The name of the developer.
     * @param phone      The phone number of the developer.
     * @param email      The email address of the developer.
     * @param address    The address of the developer.
     * @param dateJoined The date when the developer joined the company.
     * @param role       The role of the developer.
     * @param salary     The salary of the developer.
     * @param projects   The list of project names associated with the developer.
     * @param githubId   The GitHub username of the developer.
     * @param rating     The rating of the developer.
     */
    @JsonCreator
    public JsonAdaptedDeveloper(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email, @JsonProperty("address") String address,
                                @JsonProperty("dateJoined") String dateJoined, @JsonProperty("role") String role,
                                @JsonProperty("salary") String salary,
                                @JsonProperty("projects") List<String> projects,
                                @JsonProperty("githubId") String githubId,
                                @JsonProperty("rating") String rating) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateJoined = dateJoined;
        this.role = role;
        this.salary = salary;
        if (projects != null) {
            this.projects.addAll(projects);
        }
        this.githubId = githubId;
        this.rating = rating;
    }

    /**
     * Constructs a {@code JsonAdaptedDeveloper} with data from the given {@code Developer}.
     *
     * @param source The developer object from which to extract data.
     */
    public JsonAdaptedDeveloper(Developer source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        dateJoined = source.getDateJoined().toString();
        role = source.getRole().role;
        salary = source.getSalary().toString();
        projects.addAll(source.getProjects());
        rating = source.getRating().toString();
        githubId = source.getGithubId().username;
    }

    /**
     * Converts this Jackson-friendly adapted developer object into the model's {@code Developer} object.
     *
     * @return A {@code Developer} object with the data from this adapted developer.
     * @throws IllegalValueException If there were any data constraints violated in the adapted developer.
     */
    public Developer toModelType() throws IllegalValueException {
        final List<String> personProjects = new ArrayList<>();
        for (String projects : projects) {
            personProjects.add(projects);
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

        if (dateJoined == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(dateJoined, false)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDateJoined = new Date(dateJoined, false);


        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DeveloperRoles.class.getSimpleName()));
        }
        if (!DeveloperRoles.isValidRole(role)) {
            throw new IllegalValueException(DeveloperRoles.NO_SUCH_DEVELOPER_ROLE);
        }
        final DeveloperRoles modelRole = new DeveloperRoles(role);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);
        if (githubId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GithubId.class.getSimpleName()));
        }
        if (!GithubId.isValidGithubId(githubId)) {
            throw new IllegalValueException(GithubId.MESSAGE_CONSTRAINTS);
        }
        final GithubId modelGithubId = new GithubId(githubId);
        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        final Set<String> modelProjects = new HashSet<>(personProjects);
        return new Developer(modelName, modelPhone, modelEmail,
                modelAddress, modelRole, modelProjects, modelSalary, modelDateJoined,
                modelGithubId, modelRating);
    }

}
