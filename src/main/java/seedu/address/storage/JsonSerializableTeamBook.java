package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTeamBook;
import seedu.address.model.TeamBook;
import seedu.address.model.team.Team;

/**
 * A class to serialize a TeamBook into a JSON file.
 */
public class JsonSerializableTeamBook {

    private static final String MESSAGE_DUPLICATE_TEAM = "Team list contains duplicate team(s).";
    private final List<JsonAdaptedTeam> teams = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTeamBook} with the given teams.
     */
    @JsonCreator
    public JsonSerializableTeamBook(@JsonProperty("teams") List<JsonAdaptedTeam> teams) {
        this.teams.addAll(teams);
    }

    /**
     * Converts a given {@code ReadOnlyTeamBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTeamBook}.
     */
    public JsonSerializableTeamBook(ReadOnlyTeamBook source) {
        teams.addAll(source.getTeamList().stream().map(JsonAdaptedTeam::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TeamBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeamBook toModelType() throws IllegalValueException {
        TeamBook teamBook = new TeamBook();
        for (JsonAdaptedTeam jsonAdaptedTeam : teams) {
            Team team = jsonAdaptedTeam.toModelType();
            if (teamBook.hasTeam(team)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEAM);
            }
            teamBook.addTeam(team);
        }
        return teamBook;
    }


}
