package seedu.address.storage;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.team.Team;

/**
 * Jackson-friendly version of {@link Team}.
 */
class JsonAdaptedTeam {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Team's %s field is missing!";

    private final String leaderIdentityCode;
    private final Set<String> developerIdentityCodes = new HashSet<>();
    private final String teamName;

    @JsonCreator
    public JsonAdaptedTeam(@JsonProperty("leaderIdentityCode") String leaderIdentityCode,
                           @JsonProperty("developerIdentityCodes") Set<String> developerIdentityCodes,
                           @JsonProperty("teamName") String teamName) {
        this.leaderIdentityCode = leaderIdentityCode;
        if (developerIdentityCodes != null) {
            this.developerIdentityCodes.addAll(developerIdentityCodes);
        }
        this.teamName = teamName;
    }

    public JsonAdaptedTeam(Team source) {
        this.leaderIdentityCode = source.getTeamLeaderIdentityCode().toString();
        for (IdentityCode devIdCode : source.getDeveloperIdentityCodes()) {
            this.developerIdentityCodes.add(devIdCode.toString());
        }
        this.teamName = source.getTeamName();
    }


    /**
     * Converts this Jackson-friendly adapted team object into the model's {@code Team} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted team.
     */
    public Team toModelType() throws IllegalValueException {
        if (teamName == null || teamName.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "teamName"));
        }

        if (leaderIdentityCode == null || !IdentityCode.isValidCode(leaderIdentityCode)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "leaderIdentityCode"));
        }
        IdentityCode leaderIdCode = new IdentityCode(leaderIdentityCode);
        Team team = new Team(leaderIdCode, teamName);

        for (String devCodeStr : developerIdentityCodes) {
            if (IdentityCode.isValidCode(devCodeStr)) {
                IdentityCode devIdCode = new IdentityCode(devCodeStr);
                team.addDeveloper(devIdCode);
            } else {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "developerIdentityCode"));
            }
        }

        return team;
    }
}
