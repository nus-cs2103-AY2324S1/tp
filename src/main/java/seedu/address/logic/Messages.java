package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.client.Client;
import seedu.address.model.developer.Developer;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND =
            "Unknown command!\n"
            + "Please provide your command in the following format: <command>-<name of list>.\n"
            + "Example: list-developer, find-client n/John, delete-project 1";

    public static final String MESSAGE_VALID_LOCKED_COMMANDS = "Valid commands are: \n unlock, help, exit";
    public static final String MESSAGE_VALID_UNLOCKED_COMMANDS = "Type \"help\" to see the list of valid commands in"
            + " User Guide!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_FILE = "File does not exist!\n";
    public static final String MESSAGE_INVALID_DEVELOPER_DISPLAYED_INDEX = "The developer index provided is invalid!";
    public static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "The client index provided is invalid!";
    public static final String MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX = "The project index provided is invalid!";
    public static final String MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX = "The deadline index provided is invalid!";
    public static final String MESSAGE_INAPPLICABLE_PREFIX_USED =
            "You tried to edit an inapplicable field! Please check "
            + "the prefixes used and try again. \n%1$s";
    public static final String MESSAGE_NONEXISTENT_PROJECT = "There is no existing Project with the name: %1$s!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    public static String getMessageDevelopersListedOverview(int count) {
        if (count == 0) {
            return "There are no developers with matching information.";
        } else if (count == 1) {
            return "This is the 1 developer with matching information.";
        } else {
            return String.format("These are the %d developers with matching information.", count);
        }
    }

    public static String getMessageClientsListedOverview(int count) {
        if (count == 0) {
            return "There are no clients with matching information.";
        } else if (count == 1) {
            return "This is the 1 client with matching information.";
        } else {
            return String.format("These are the %d clients with matching information.", count);
        }
    }

    public static String getMessageProjectsListedOverview(int count) {
        if (count == 0) {
            return "There are no projects with matching information.";
        } else if (count == 1) {
            return "This is the 1 project with matching information.";
        } else {
            return String.format("These are the %d projects with matching information.", count);
        }
    }
    public static String getMessageDeadlinesListedOverview(int count) {
        if (count == 0) {
            return "There are no deadlines with matching information.";
        } else if (count == 1) {
            return "This is the 1 deadline with matching information.";
        } else {
            return String.format("These are the %d deadlines with matching information.", count);
        }
    }

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code developer} for display to the user.
     */
    public static String format(Developer developer) {
        final StringBuilder builder = new StringBuilder();
        builder.append(developer.getName())
                .append("; \nPhone: ")
                .append(developer.getPhone())
                .append("; \nEmail: ")
                .append(developer.getEmail())
                .append("; \nAddress: ")
                .append(developer.getAddress())
                .append("; \nDate Joined: ")
                .append(developer.getDateJoined())
                .append("; \nRole: ")
                .append(developer.getRole())
                .append("; \nSalary: ")
                .append(developer.getSalary())
                .append("; \nProjects: ");
        developer.getProjects().forEach(t -> builder.append(t).append(" "));
        return builder.toString();
    }

    /**
     * Formats a client's information into a string.
     *
     * @param client The client to format.
     * @return A string representing the formatted client information.
     */
    public static String format(Client client) {
        final StringBuilder builder = new StringBuilder();
        builder.append(client.getName())
                .append("; \nPhone: ")
                .append(client.getPhone())
                .append("; \nEmail: ")
                .append(client.getEmail())
                .append("; \nAddress: ")
                .append(client.getAddress())
                .append("; \nOrganisation: ")
                .append(client.getOrganisation())
                .append("; \nRole: ")
                .append(client.getRole())
                .append("; \nDocument: ")
                .append(client.getDocument())
                .append("; \nProjects: ");
        client.getProjects().forEach(t -> builder.append(t).append(" "));
        return builder.toString();
    }

    /**
     * Formats a project's information into a string.
     *
     * @param project The project to format.
     * @return A string representing the formatted project information.
     */
    public static Object format(seedu.address.model.project.Project project) {
        final StringBuilder builder = new StringBuilder();
        builder.append(project.getName())
                .append(";\nDescription: ")
                .append(project.getProjectDescription())
                .append(";\nDeadlines:\n");
        project.getProjectDeadlines().forEach(t -> builder.append(t.getPrintedStringRepresentation()).append("\n"));
        return builder.toString();
    }

    /**
     * Formats a role into a string.
     *
     * @param role The role to format.
     * @return A string representing the formatted role.
     */
    public static String format(String role) {
        final StringBuilder builder = new StringBuilder();
        builder.append(role);
        return builder.toString();
    }

}
