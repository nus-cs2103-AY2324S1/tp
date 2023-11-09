package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTeamBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;
import seedu.address.testutil.TypicalPersons;


public class AddDevToTeamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());
    @Test
    public void constructor_nullTeamName_throwsNullPointerException() {
        Name devName = BENSON.getName();
        assertThrows(NullPointerException.class, () -> new AddDevToTeamCommand(null, devName));
    }
    @Test
    public void constructor_nullTeamLeaderName_throwsNullPointerException() {
        String teamName = "Team Test Alpha";
        assertThrows(NullPointerException.class, () -> new AddDevToTeamCommand(teamName, null));
    }
    @Test
    public void execute_invalidTeamName_throwsInvalidTeamCommandException() {
        String teamName = "This team does not exist in the model";
        Name devName = AMY.getName();
        AddDevToTeamCommand addDevToTeam = new AddDevToTeamCommand(teamName, devName);
        assertThrows(CommandException.class, () -> addDevToTeam.execute(model));
    }
    @Test
    public void execute_invalidPerson_throwsInvalidPersonCommandException() {
        String teamName = "TEAM1";
        Name devName = new Name("This person does not exist");
        AddDevToTeamCommand addDevToTeam = new AddDevToTeamCommand(teamName, devName);
        assertThrows(CommandException.class, () -> addDevToTeam.execute(model));
    }
    @Test
    public void execute_personAlreadyInTeam_throwsDuplicatePersonException() {
        String teamName = "TEAM1";
        Name devName = CARL.getName();
        AddDevToTeamCommand addDevToTeam = new AddDevToTeamCommand(teamName, devName);
        try {
            addDevToTeam.execute(model);
            fail("Expected this method to fail");
        } catch (CommandException e) {
            assertEquals("This developer already exists in this team!", e.getMessage());
        }
    }
    @Test
    public void execute_leaderOfTeamBeingAdded_throwsTeamLeaderAddException() {
        String teamName = "TEAM1";
        Name devName = ALICE.getName();
        AddDevToTeamCommand addDevToTeam = new AddDevToTeamCommand(teamName, devName);
        try {
            addDevToTeam.execute(model);
            fail("Expected this method to fail");
        } catch (CommandException e) {
            assertEquals("The leader of team cannot be added as a developer!", e.getMessage());
        }
    }
    @Test
    public void execute_validDeveloperAdded_success() throws CommandException {
        String teamName = "TEAM2";
        Name devName = ALICE.getName();
        AddDevToTeamCommand addDevToTeam = new AddDevToTeamCommand(teamName, devName);
        assertEquals(String.format(AddDevToTeamCommand.MESSAGE_SUCCESS, Messages.format(teamName, devName)),
                addDevToTeam.execute(model).getFeedbackToUser());
    }
    @Test
    public void equals() {
        Name dev1 = ALICE.getName();
        Name dev2 = BENSON.getName();
        String teamName1 = "TEAM1";
        String teamName2 = "TEAM2";
        AddDevToTeamCommand addCommand1 = new AddDevToTeamCommand(teamName1, dev1);
        AddDevToTeamCommand addCommand2 = new AddDevToTeamCommand(teamName2, dev2);

        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));

        // same values -> returns true
        AddDevToTeamCommand addCommand3 = new AddDevToTeamCommand(teamName1, dev1);
        assertTrue(addCommand3.equals(addCommand1));

        // different types -> returns false
        assertFalse(addCommand1.equals("abc"));

        // null -> returns false
        assertFalse(addCommand2.equals(null));

        // different team command -> returns false
        assertFalse(addCommand1.equals(addCommand2));

        // same teamname,different leadername -> returns false
        AddTeamCommand addTeam4Command = new AddTeamCommand(teamName1, dev2);
        assertFalse(addCommand1.equals(addTeam4Command));
    }
    @Test
    public void toStringMethod() {
        String teamName = "TEAM1";
        Name devToAdd = DANIEL.getName();
        AddDevToTeamCommand addCommand = new AddDevToTeamCommand(teamName, devToAdd);
        String expected = AddDevToTeamCommand.class.getCanonicalName() + "{devToAdd=" + devToAdd + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTeamBookFilePath() {
            return null;
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeamBookFilePath(Path teamBookFilePath) {

        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean containsPerson(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonByName(Name name) {
            return null;
        }

        @Override
        public IdentityCode getIdentityCodeByName(Name developerName) {
            return null;
        }

        @Override
        public Name getNameByIdentityCode(IdentityCode developerID) {
            return null;
        }

        @Override
        public boolean invalidAddToTeam(String teamToAddTo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addToTeam(String teamToAddTo, Name devToAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeamBook(ReadOnlyTeamBook teamBook) {

        }

        @Override
        public ReadOnlyTeamBook getTeamBook() {
            return null;
        }

        @Override
        public boolean hasTeam(String teamName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTeam(String teamName) {

        }

        @Override
        public void addTeam(Team team) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isLeaderOfTeam(String teamName, Name devToBeAdded) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean developerIsTeamLeader(IdentityCode developerIdentityCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean removeDeveloperFromAllTeams(IdentityCode developerIdentityCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeveloperFromTeam(String teamName, IdentityCode developerIdentityCOde) {

        }

        @Override
        public boolean personAlreadyInTeam(String teamToAddTo, Name devToAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editTeamName(String originalTeamName, String newTeamName) {

        }

        @Override
        public Name getTeamLeaderOfTeam(String teamName) {
            return null;
        }

        @Override
        public IdentityCode getTeamLeaderIdOfTeam(String teamName) {
            return null;
        }

        @Override
        public void setTeamLeaderOfTeam(String teamName, IdentityCode newTeamLeaderID) {

        }

        @Override
        public ObservableList<Team> getFilteredTeamList() {
            return null;
        }

        @Override
        public void updateFilteredTeamList(Predicate<Team> predicate) {

        }
        @Override
        public Person getPersonByIdentityCode(IdentityCode id) {
            return null;
        }
    }


    /**
     * A Model stub that accepts the teams being added.
     */
    private class ModelStubAcceptingTeamAdded extends AddDevToTeamCommandTest.ModelStub {
        final ArrayList<Team> teamsAdded = new ArrayList<>();

        public void sampleTeamBuilder() {
            String newTeamName = "Test Team Zeta";
            IdentityCode newDevIdentityCode = TypicalPersons.ALICE.getIdentityCode();
            Team newTeam = new Team(newDevIdentityCode, newTeamName);
            teamsAdded.add(newTeam);
        }

        @Override
        public boolean hasTeam(String thisTeamName) {
            requireNonNull(thisTeamName);
            return teamsAdded.stream().anyMatch(currentTeam -> currentTeam.getTeamName().equals(thisTeamName));
        }

        @Override
        public void addTeam(Team team) {
            requireNonNull(team);
            teamsAdded.add(team);
        }
        @Override
        public boolean containsPerson(Name name) {
            requireNonNull(name);
            return getTypicalAddressBook().containsPerson(name);
        }
        @Override
        public boolean personAlreadyInTeam(String teamToAddTo, Name devToAdd) {
            requireNonNull(teamToAddTo);
            requireNonNull(devToAdd);
            IdentityCode identityCode = getIdentityCodeByName(devToAdd);
            return getTypicalTeamBook().personAlreadyInTeam(teamToAddTo, identityCode);
        }
        @Override
        public Person getPersonByName(Name name) {
            requireNonNull(name);
            return getTypicalAddressBook().getPersonByName(name);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
