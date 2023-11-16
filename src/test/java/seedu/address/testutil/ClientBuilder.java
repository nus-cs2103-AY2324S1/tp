package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Client;
import seedu.address.model.client.ClientRoles;
import seedu.address.model.client.Document;
import seedu.address.model.commons.Name;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Developer objects.
 */
public class ClientBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ROLE = "Developer";
    public static final String DEFAULT_ORGANISATION = "Google";
    public static final String DEFAULT_DOCUMENT = "https://www.google.com";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private ClientRoles role;
    private Name organisation;
    private Document document;
    private Set<String> projects;

    /**
     * Creates a {@code DeveloperBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        role = new ClientRoles(DEFAULT_ROLE);
        organisation = new Name(DEFAULT_ORGANISATION);
        document = new Document(DEFAULT_DOCUMENT);
        projects = new HashSet<>();
    }

    /**
     * Initializes the DeveloperBuilder with the data of {@code clientToCopy}.
     * @param clientToCopy
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        role = clientToCopy.getRole();
        organisation = clientToCopy.getOrganisation();
        document = clientToCopy.getDocument();
        projects = new HashSet<>(clientToCopy.getProjects());
    }

    /** Sets the {@code Name} of the {@code Client} that we are building. */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /** Sets the {@code Address} of the {@code Client} that we are building. */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /** Sets the {@code Phone} of the {@code Client} that we are building. */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /** Sets the {@code Email} of the {@code Client} that we are building. */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /** Sets the {@code Role} of the {@code Client} that we are building. */
    public ClientBuilder withRole(String role) {
        this.role = new ClientRoles(role);
        return this;
    }

    /** Sets the {@code Organisation} of the {@code Client} that we are building. */
    public ClientBuilder withOrganisation(String organisation) {
        this.organisation = new Name(organisation);
        return this;
    }

    /** Sets the {@code Document} of the {@code Client} that we are building. */
    public ClientBuilder withDocument(String document) {
        this.document = new Document(document);
        return this;
    }

    /** Sets the {@code Projects} of the {@code Client} that we are building. */
    public ClientBuilder withProjects(String... projects) {
        this.projects = SampleDataUtil.getProjectSet(projects);
        return this;
    }

    /**
     * Builds the Client with the data provided.
     */
    public Client build() {
        return new Client(name, phone, email, address, role, projects, organisation, document);
    }
}
