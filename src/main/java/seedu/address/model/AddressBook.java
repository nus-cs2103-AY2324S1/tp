package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.developer.Developer;
import seedu.address.model.developer.UniqueDeveloperList;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.model.project.UniqueProjectList;

/**
 * Represents the entire address book. Contains the data of the developers, clients, and projects.
 * Duplicates are not allowed (by .isSamePerson comparison).
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueDeveloperList developers;
    private final UniqueClientList clients;
    private final UniqueProjectList projects;

    {
        developers = new UniqueDeveloperList();
        clients = new UniqueClientList();
        projects = new UniqueProjectList();
    }

    /**
     * Creates an empty AddressBook.
     */
    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the data in the given ReadOnlyAddressBook.
     *
     * @param toBeCopied The ReadOnlyAddressBook containing data to be copied into this AddressBook.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /// Project Validation

    /**
     * Checks if the projects assigned to a person are valid.
     *
     * @param person The person to check for valid projects.
     * @return The name of the first invalid project if any, else returns null.
     */
    public String areProjectsValid(Person person) {
        Set<String> projects = person.getProjects();
        for (String p : projects) {
            if (!this.hasProject(p)) {
                return p;
            }
        }
        return null;
    }

    // List Overwrite Operations

    /**
     * Sets the list of developers in the AddressBook with the provided list.
     *
     * @param developers The list of developers to set.
     */
    public void setDevelopers(List<Developer> developers) {
        this.developers.setDevelopers(developers);
    }

    /**
     * Sets the list of clients in the AddressBook with the provided list.
     *
     * @param clients The list of clients to set.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    /**
     * Sets the list of projects in the AddressBook with the provided list.
     *
     * @param projects The list of projects to set.
     */
    public void setProjects(List<seedu.address.model.project.Project> projects) {
        this.projects.setProjects(projects);
    }

    /**
     * Resets the data of this AddressBook with data from another ReadOnlyAddressBook.
     *
     * @param newData The ReadOnlyAddressBook containing data to reset this AddressBook with.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setDevelopers(newData.getDeveloperList());
        setClients(newData.getClientList());
        setProjects(newData.getProjectList());
    }

    // Developer-level Operations

    /**
     * Checks if a developer exists in this AddressBook.
     *
     * @param developer The developer to check.
     * @return True if the developer exists, false otherwise.
     */
    public boolean hasDeveloper(Developer developer) {
        requireNonNull(developer);
        return developers.contains(developer);
    }

    /**
     * Adds a developer to this AddressBook.
     *
     * @param developer The developer to add.
     */
    public void addDeveloper(Developer developer) {
        developers.add(developer);
    }

    /**
     * Sets an existing developer in this AddressBook with an edited developer.
     *
     * @param target The developer to be replaced.
     * @param editedDeveloper The edited developer.
     */
    public void setDeveloper(Developer target, Developer editedDeveloper) {
        requireNonNull(editedDeveloper);
        developers.setDeveloper(target, editedDeveloper);
    }

    /**
     * Removes a developer from this AddressBook.
     *
     * @param key The developer to remove.
     */
    public void removeDeveloper(Developer key) {
        developers.remove(key);
    }

    // Client-level Operations

    /**
     * Checks if a client exists in this AddressBook.
     *
     * @param client The client to check.
     * @return True if the client exists, false otherwise.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to this AddressBook.
     *
     * @param client The client to add.
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Sets an existing client in this AddressBook with an edited client.
     *
     * @param target The client to be replaced.
     * @param editedClient The edited client.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);
        clients.setClient(target, editedClient);
    }

    /**
     * Removes a client from this AddressBook.
     *
     * @param key The client to remove.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    // Project-level Operations

    /**
     * Checks if a project exists in this AddressBook.
     *
     * @param project The project to check.
     * @return True if the project exists, false otherwise.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Checks if a project with a given name exists in this AddressBook.
     *
     * @param project The name of the project to check.
     * @return True if the project exists, false otherwise.
     */
    public boolean hasProject(String project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Adds a project to this AddressBook.
     *
     * @param project The project to add.
     */
    public void addProject(Project project) {
        projects.add(project);
    }

    /**
     * Sets an existing project in this AddressBook with an edited project.
     *
     * @param target The project to be replaced.
     * @param editedProject The edited project.
     */
    public void setProject(Project target, Project editedProject) {
        requireNonNull(editedProject);
        projects.setProject(target, editedProject);
    }

    /**
     * Removes a project from this AddressBook. Also updates clients and developers to remove the project assignment.
     *
     * @param key The project to remove.
     */
    public void removeProject(Project key) {
        projects.remove(key);
        clients.updateClientProjects(key.getName());
        developers.updateDeveloperProjects(key.getName());
    }

    /**
     * Returns a string representation of this AddressBook.
     *
     * @return A string representation of this AddressBook.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("developers", developers)
                .add("clients", clients)
                .add("projects", projects)
                .toString();
    }

    /**
     * Returns the list of developers as an observable list.
     *
     * @return The list of developers as an observable list.
     */
    @Override
    public ObservableList<Developer> getDeveloperList() {
        return developers.asUnmodifiableObservableList();
    }

    /**
     * Returns the list of clients as an observable list.
     *
     * @return The list of clients as an observable list.
     */
    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    /**
     * Returns the list of projects as an observable list.
     *
     * @return The list of projects as an observable list.
     */
    @Override
    public ObservableList<seedu.address.model.project.Project> getProjectList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return developers.equals(otherAddressBook.developers)
                && clients.equals(otherAddressBook.clients)
                && projects.equals(otherAddressBook.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developers, clients, projects);
    }
}
