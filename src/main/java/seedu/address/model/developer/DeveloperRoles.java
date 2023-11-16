package seedu.address.model.developer;

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
 * Represents the roles a Developer can have in the company.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class DeveloperRoles {
    public static final String NO_SUCH_DEVELOPER_ROLE = "There is no such developer role, "
            + "please create role before proceeding!";
    private static List<DeveloperRoles> roles = new ArrayList<>();
    private static boolean noRepeat;
    private static boolean notDefault;
    private static boolean notInList;
    private static String listOfRoles;

    static {
        roles.add(new DeveloperRoles("Frontend Developer"));
        roles.add(new DeveloperRoles("Backend Developer"));
        roles.add(new DeveloperRoles("Developer"));
        loadDeveloperRoles();
    }

    public final String role;

    /**
     * Constructs a {@code DeveloperRoles}.
     *
     * @param role A valid role.
     */
    public DeveloperRoles(String role) {
        requireNonNull(role);
        this.role = role;
    }

    /**
     * Adds a developer role to the list of developer roles.
     *
     * @param role The developer role to be added.
     */
    public static void addDeveloperRole(DeveloperRoles role) {
        roles.add(role);
        saveDeveloperRoles();
    }

    /**
     * Deletes a developer role from the list of developer roles.
     *
     * @param role The developer role to be deleted.
     */
    public static void deleteDeveloperRole(DeveloperRoles role) {
        roles.remove(role);
        saveDeveloperRoles();
    }

    /**
     * Returns true if a given string is a valid role.
     *
     * @param role The role to be checked.
     * @return True if the role is valid, false otherwise.
     */
    public static boolean isValidRole(String role) {
        boolean roleExists = false;
        for (DeveloperRoles devRoles : roles) {
            if (devRoles.toString().equals(role)) {
                roleExists = true;
                break; // You can break early once a match is found
            }
        }
        return roleExists;
    }

    /**
     * Retrieves a string representation of all developer roles.
     *
     * @return A string representing all developer roles.
     */
    public static String printRoles() {
        listOfRoles = roles.toString();
        return listOfRoles;
    }

    /**
     * Checks if a developer role can be removed.
     *
     * @param model The model to check against.
     * @param role  The role to be checked.
     * @return True if the role can be removed, false otherwise.
     */
    public static boolean isRemovableRole(Model model, String role) {

        ObservableList<Developer> developerList = model.getAddressBook().getDeveloperList();

        Predicate<Developer> rolePredicate = developer -> developer.getRole().toString().equals(role);

        List<Developer> developersWithRole = developerList.stream()
                .filter(rolePredicate)
                .collect(Collectors.toList());

        if (developersWithRole.isEmpty()) {
            noRepeat = true;
        } else {
            noRepeat = false;
        }

        // check if this role is one of the defaults
        if (role.equals("Frontend Developer")
                || role.equals("Backend Developer")
                || role.equals("Developer")) {
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
     * Saves developer roles to a text file.
     */
    public static void saveDeveloperRoles() {
        try {
            // Save roles to a text file
            try (FileWriter writer = new FileWriter("DeveloperRoles.txt")) {
                for (DeveloperRoles role : roles) {
                    writer.write(role.toString() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads developer roles from a file on initialization.
     */
    public static void loadDeveloperRoles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("DeveloperRoles.txt"))) {
            String line;
            int lineCount = 1;

            while ((line = reader.readLine()) != null) {
                if (lineCount >= 4) {
                    roles.add(new DeveloperRoles(line));
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNotDefault() {
        return notDefault;
    }

    public static boolean isNoRepeat() {
        return noRepeat;
    }

    public static boolean isNotInList() {
        return notInList;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeveloperRoles)) {
            return false;
        }

        DeveloperRoles otherRole = (DeveloperRoles) other;
        return role.equals(otherRole.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
