package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON = "This person does not exist!";

    public static final String MESSAGE_TEAM_LEADER_IDENTITY_CODE_RETRIEVED = "The team leader listed!";
    public static final String MESSAGE_TEAM_NOT_FOUND = "The team is not found!";
    public static final String MESSAGE_INVALID_TEAM_NAME_DISPLAYED = "The team name provided is invalid!";
    public static final String MESSAGE_INVALID_PERSON_IN_TEAM = "The team does not have the developer you mentioned!";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    public static final String MESSAGE_TEAMS_LISTED_OVERVIEW = "%1$d teams listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     *
     * @param duplicatePrefixes Array of prefixes that are duplicated.
     * @return A formatted error message indicating which prefixes are duplicated.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats a given {@code Person} object for display to the user.
     * The formatting includes the person's name, phone, email, address, and associated tags.
     *
     * @param person The person object to format.
     * @return A string representation of the person object suitable for user display.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }
    //Update this after the uniqueteamlist class is implemented. Need a way to retrieve leader
    //name from the hashcode(which should be done in the new uniqueTeamList class)
    //Need to format a team to hold teamname, leadername and developer set for the structure.
    /**
     * Formats a given {@code Team} object for display to the user.
     * The formatting includes the team's name and the team leader's name.
     *
     * @param team The team object to format.
     * @param leaderToAdd The name of the team leader to add to the display format.
     * @return A string representation of the team object suitable for user display.
     */
    public static String format(Team team, Name leaderToAdd) {
        final StringBuilder builder = new StringBuilder();
        builder.append(team.getTeamName())
                .append("; LeaderName ")
                .append(leaderToAdd); //get leader name
        return builder.toString();
    }

    /**
     * Formats a message indicating a new member addition to a team.
     *
     * @param teamToAddTo The team to which the member was added.
     * @param devToAddTo The developer name that was added to the team.
     * @return A string message indicating the addition of the developer to the team.
     */
    public static String format(String teamToAddTo, Name devToAddTo) {
        final StringBuilder builder = new StringBuilder();
        builder.append(teamToAddTo)
                .append(" got a new member! Hello ")
                .append(devToAddTo);
        return builder.toString();
    }

    /**
     * Formats a message indicating a team name change.
     *
     * @param originalTeamName The original name of the team.
     * @param newTeamName The new name of the team.
     * @return A string message indicating the team's name change.
     */
    public static String editTeamNameFormat(String originalTeamName, String newTeamName) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Original team: ")
                .append(originalTeamName)
                .append(" changed its Team Name to: ")
                .append(newTeamName);
        return builder.toString();
    }

    /**
     * Formats a message indicating a change in the team leader.
     *
     * @param teamName The name of the team.
     * @param originalTeamLeader The original team leader's name.
     * @param newTeamLeader The new team leader's name.
     * @return A string message indicating the change in team leadership.
     */
    public static String editTeamLeaderFormat(String teamName, Name originalTeamLeader, Name newTeamLeader) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Team: ")
                .append(teamName)
                .append(" changed its Team Leader from: ")
                .append(originalTeamLeader.toString())
                .append(" to: ")
                .append(newTeamLeader.toString());
        return builder.toString();
    }

}
