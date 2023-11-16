package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.Model;

/**
 * Represents a Developer's role in the company.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class ClientRoles {
    public static final String NO_SUCH_CLIENT_ROLE = "There is no such client role, "
            + "please create role before proceeding!";
    private static List<ClientRoles> roles = new ArrayList<>();
    private static boolean noRepeat;
    private static boolean notDefault;
    private static boolean notInList;
    private static String listOfRoles;

    static {
        roles.add(new ClientRoles("Manager"));
        roles.add(new ClientRoles("Developer"));
        roles.add(new ClientRoles("HR"));
        roles.add(new ClientRoles("Client"));
        loadClientRoles();
    }

    public final String role;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public ClientRoles(String role) {
        requireNonNull(role);
        this.role = role;
    }

    /**
     * Adds a client role to the list of client roles.
     *
     * @param role The client role to be added.
     */
    public static void addClientRole(ClientRoles role) {
        roles.add(role);
        saveClientRoles();
    }

    /**
     * Deletes a client role from the list of client roles.
     *
     * @param role The client role to be deleted.
     */
    public static void deleteClientRole(ClientRoles role) {
        roles.remove(role);
        saveClientRoles();
    }

    /**
     * Returns true if a given string is a valid role.
     *
     * @param role The role to check for validity.
     * @return {@code true} if the role is valid, {@code false} otherwise.
     */
    public static boolean isValidRole(String role) {
        boolean roleExists = false;
        for (ClientRoles cliRoles : roles) {
            if (cliRoles.toString().equals(role)) {
                roleExists = true;
                break; // You can break early once a match is found
            }
        }
        return roleExists;
    }

    /**
     * Returns a string representation of the list of client roles.
     *
     * @return A string representation of the client roles.
     */
    public static String printRoles() {
        listOfRoles = roles.toString();
        return listOfRoles;
    }

    /**
     * Checks if a role can be removed based on specific criteria.
     *
     * @param model The model for checking if the role is removable.
     * @param role  The role to check for removal.
     * @return {@code true} if the role can be removed, {@code false} otherwise.
     */
    public static boolean isRemovableRole(Model model, String role) {
        // check if anyone is using this role
        ObservableList<Client> clientsList = model.getAddressBook().getClientList();

        Predicate<Client> rolePredicate = client -> client.getRole().toString().equals(role);

        List<Client> clientsWithRole = clientsList.stream()
                .filter(rolePredicate)
                .collect(Collectors.toList());

        if (clientsWithRole.isEmpty()) {
            noRepeat = true;
        } else {
            noRepeat = false;
        }

        // check if this role is one of the defaults
        if (role.equals("Manager")
                || role.equals("Developer")
                || role.equals("HR")
                || role.equals("Client")) {
            notDefault = false;
        } else {
            notDefault = true;
        }

        // check if role is in the list
        boolean roleExists = isValidRole(role);

        if (roleExists) {
            notInList = false;
        } else {
            notInList = true;
        }

        if (noRepeat && notDefault && !notInList) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Saves the list of client roles to a text file.
     */
    public static void saveClientRoles() {
        try {
            // Save roles to a text file
            try (FileWriter writer = new FileWriter("ClientRoles.txt")) {
                for (ClientRoles role : roles) {
                    writer.write(role.toString() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of client roles from a text file.
     */
    public static void loadClientRoles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("ClientRoles.txt"))) {
            String line;
            int lineCount = 1;

            while ((line = reader.readLine()) != null) {
                if (lineCount >= 5) {
                    roles.add(new ClientRoles(line));
                }
                lineCount++;
            }
        } catch (IOException e) {
            saveClientRoles();
        }

    }

    /**
     * Checks if the role is not one of the default roles.
     *
     * @return {@code true} if the role is not a default role, {@code false} otherwise.
     */
    public static boolean isNotDefault() {
        return notDefault;
    }

    /**
     * Checks if the role is not repeated.
     *
     * @return {@code true} if the role is not repeated, {@code false} otherwise.
     */
    public static boolean isNoRepeat() {
        return noRepeat;
    }

    /**
     * Checks if the role is not in the list of client roles.
     *
     * @return {@code true} if the role is not in the list, {@code false} otherwise.
     */
    public static boolean isNotInList() {
        return notInList;
    }

    /**
     * Returns a string representation of this client role.
     *
     * @return A string representation of this client role.
     */
    @Override
    public String toString() {
        return role;
    }

    /**
     * Checks if this client role is equal to another object.
     *
     * @param other The object to compare to.
     * @return {@code true} if the client roles are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientRoles)) {
            return false;
        }

        ClientRoles otherRole = (ClientRoles) other;
        return role.equals(otherRole.role);
    }

    /**
     * Returns the hash code for this client role.
     *
     * @return The hash code for this client role.
     */
    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
