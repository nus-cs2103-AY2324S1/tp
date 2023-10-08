package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientList;

/**
 * Wraps all data at the client-directory level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class ClientDirectory implements ReadOnlyClientDirectory {

    private final ClientList clients;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new ClientList();
    }

    public ClientDirectory() {}

    /**
     * Creates a ClientDirectory using the Clients in the {@code toBeCopied}
     */
    public ClientDirectory(ReadOnlyClientDirectory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    /**
     * Resets the existing data of this {@code ClientDirectory} with {@code newData}.
     */
    public void resetData(ReadOnlyClientDirectory newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the client directory.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to the client directory.
     * The client must not already exist in the client directory.
     */
    public void addClient(Client c) {
        clients.add(c);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the client directory.
     * The client identity of {@code editedClient} must not be the same as another
     * existing client in the client directory.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code ClientDirectory}.
     * {@code key} must exist in the client directory.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("clients", clients)
                .toString();
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientDirectory)) {
            return false;
        }

        ClientDirectory otherClientDirectory = (ClientDirectory) other;
        return clients.equals(otherClientDirectory.clients);
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
