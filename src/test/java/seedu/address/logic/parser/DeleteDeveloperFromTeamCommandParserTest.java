package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteDeveloperFromTeamCommand;
import seedu.address.model.person.Name;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTeams;

public class DeleteDeveloperFromTeamCommandParserTest {

    private DeleteDeveloperFromTeamCommandParser parser = new DeleteDeveloperFromTeamCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteDeveloperFromTeamCommand() {
        Name developerName = TypicalPersons.CARL.getName();
        String teamName = TypicalTeams.TEAM1.getTeamName();
        assertParseSuccess(parser, " tn/Team1 n/Carl Kurz",
                                        new DeleteDeveloperFromTeamCommand(teamName, developerName));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "123", String.format(
                                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteDeveloperFromTeamCommand.MESSAGE_USAGE));
    }
}
