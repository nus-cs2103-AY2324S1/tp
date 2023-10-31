package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTeam.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeams.TEAM1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.team.Team;

public class JsonAdaptedTeamTest {

    private static final String INVALID_IDENTITY_CODE = "INVALID_CODE";
    private static final String VALID_TEAM_NAME = TEAM1.getTeamName();
    private static final String VALID_LEADER_IDENTITY_CODE = TEAM1.getTeamLeaderIdentityCode().toString();
    private static final Set<String> VALID_DEVELOPER_IDENTITY_CODES = TEAM1.getDeveloperIdentityCodes().stream()
            .map(IdentityCode::toString)
            .collect(Collectors.toSet());

    @Test
    public void toModelType_validTeamDetails_returnsTeam() throws Exception {
        JsonAdaptedTeam team = new JsonAdaptedTeam(TEAM1);
        assertEquals(TEAM1, team.toModelType());
    }

    @Test
    public void toModelType_invalidTeamName_throwsIllegalValueException() {
        JsonAdaptedTeam team = new JsonAdaptedTeam(
                VALID_LEADER_IDENTITY_CODE, VALID_DEVELOPER_IDENTITY_CODES, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "teamName");
        assertThrows(IllegalValueException.class, expectedMessage, team::toModelType);
    }

    @Test
    public void toModelType_invalidLeaderIdentityCode_throwsIllegalValueException() {
        JsonAdaptedTeam team = new JsonAdaptedTeam(
                INVALID_IDENTITY_CODE, VALID_DEVELOPER_IDENTITY_CODES, VALID_TEAM_NAME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "leaderIdentityCode");
        assertThrows(IllegalValueException.class, expectedMessage, team::toModelType);
    }

    @Test
    public void toModelType_invalidDeveloperIdentityCodes_throwsIllegalValueException() {
        Set<String> invalidDeveloperCodes = new HashSet<>(VALID_DEVELOPER_IDENTITY_CODES);
        invalidDeveloperCodes.add(INVALID_IDENTITY_CODE);

        JsonAdaptedTeam team = new JsonAdaptedTeam(
                VALID_LEADER_IDENTITY_CODE, invalidDeveloperCodes, VALID_TEAM_NAME);
        assertThrows(IllegalValueException.class, team::toModelType);
    }

    @Test
    public void toModelType_nullDeveloperIdentityCodes_doesNotThrowException() throws Exception {
        JsonAdaptedTeam team = new JsonAdaptedTeam(
                VALID_LEADER_IDENTITY_CODE, null, VALID_TEAM_NAME);
        Team expectedTeam = new Team(new IdentityCode(VALID_LEADER_IDENTITY_CODE), VALID_TEAM_NAME);
        assertEquals(expectedTeam, team.toModelType());
    }
}
