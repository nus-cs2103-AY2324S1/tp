package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.commons.Date;
import seedu.address.model.commons.Name;
import seedu.address.model.developer.Developer;
import seedu.address.model.developer.DeveloperRoles;
import seedu.address.model.developer.GithubId;
import seedu.address.model.developer.Rating;
import seedu.address.model.developer.Salary;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Developer objects.
 */
public class DeveloperBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DATE_JOINED = "01-01-2022";
    public static final String DEFAULT_ROLE = "Developer";
    public static final String DEFAULT_SALARY = "50000";
    public static final String DEFAULT_GITHUBID = "mahidharah";
    public static final String DEFAULT_RATING = "5.0";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private DeveloperRoles role;
    private Set<String> projects;
    private Salary salary;
    private Date dateJoined;
    private GithubId githubId;
    private Rating rating;

    /**
     * Creates a {@code DeveloperBuilder} with the default details.
     */
    public DeveloperBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        projects = new HashSet<>();
        dateJoined = new Date(DEFAULT_DATE_JOINED, false);
        role = new DeveloperRoles(DEFAULT_ROLE);
        salary = new Salary(DEFAULT_SALARY);
        githubId = new GithubId(DEFAULT_GITHUBID);
        rating = new Rating(DEFAULT_RATING);
    }

    /**
     * Initializes the DeveloperBuilder with the data of {@code developerToCopy}.
     * @param developerToCopy
     */
    public DeveloperBuilder(Developer developerToCopy) {
        name = developerToCopy.getName();
        phone = developerToCopy.getPhone();
        email = developerToCopy.getEmail();
        address = developerToCopy.getAddress();
        projects = new HashSet<>(developerToCopy.getProjects());
        dateJoined = developerToCopy.getDateJoined();
        role = developerToCopy.getRole();
        salary = developerToCopy.getSalary();
        githubId = developerToCopy.getGithubId();
        rating = developerToCopy.getRating();
    }

    /** Sets the {@code Name} of the {@code Developer} that we are building. */
    public DeveloperBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /** Sets the {@code Projects} of the {@code Developer} that we are building. */
    public DeveloperBuilder withProjects(String... projects) {
        this.projects = SampleDataUtil.getProjectSet(projects);
        return this;
    }

    /** Sets the {@code GithubId} of the {@code Developer} that we are building. */
    public DeveloperBuilder withGithubId(String githubId) {
        this.githubId = new GithubId(githubId);
        return this;
    }

    /** Sets the {@code Rating} of the {@code Developer} that we are building. */
    public DeveloperBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /** Sets the {@code Address} of the {@code Developer} that we are building. */
    public DeveloperBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /** Sets the {@code Phone} of the {@code Developer} that we are building. */
    public DeveloperBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /** Sets the {@code Email} of the {@code Developer} that we are building. */
    public DeveloperBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /** Sets the {@code DateJoined} of the {@code Developer} that we are building. */
    public DeveloperBuilder withDateJoined(String dateJoined) {
        this.dateJoined = new Date(dateJoined, false);
        return this;
    }

    /** Sets the {@code Role} of the {@code Developer} that we are building. */
    public DeveloperBuilder withRole(String role) {
        this.role = new DeveloperRoles(role);
        return this;
    }

    /** Sets the {@code Salary} of the {@code Developer} that we are building. */
    public DeveloperBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /** Builds the developer. */
    public Developer build() {
        return new Developer(name, phone, email, address, role, projects, salary, dateJoined, githubId, rating);
    }
}
