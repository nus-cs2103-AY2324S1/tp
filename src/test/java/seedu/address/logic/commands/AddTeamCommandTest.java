package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTeamBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;
import seedu.address.testutil.TypicalPersons;

public class AddTeamCommandTest {

    @Test
    public void constructor_nullTeamName_throwsNullPointerException() {
        Name leaderName = AMY.getName();
        assertThrows(NullPointerException.class, () -> new AddTeamCommand(null, leaderName));
    }

    @Test
    public void constructor_nullDeveloperName_throwsNullPointerException() {
        String teamName = "Test Team Zeta";
        assertThrows(NullPointerException.class, () -> new AddTeamCommand(teamName, null));
    }

    @Test
    public void constructor_bothParamNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTeamCommand(null, null));
    }
    @Test
    public void execute_addNewTeam_commandSuccess() throws CommandException {
        ModelStubAcceptingTeamAdded modelStub = new ModelStubAcceptingTeamAdded();
        String newTeamName = "Test Team Beta";
        Name newLeader = ALICE.getName();
        IdentityCode newDevIdentityCode = ALICE.getIdentityCode();
        Team newTeam = new Team(newDevIdentityCode, newTeamName);

        CommandResult addTeamCommand = new AddTeamCommand(newTeamName, newLeader).execute(modelStub);
        assertEquals(String.format(AddTeamCommand.MESSAGE_SUCCESS, Messages.format(newTeam, newLeader)),
                addTeamCommand.getFeedbackToUser());
        assertEquals(Arrays.asList(newTeam), modelStub.teamsAdded);
    }

    @Test
    public void execute_addTeamWithSameName_commandExceptionDuplicateTeam() {
        ModelStubAcceptingTeamAdded modelStub = new ModelStubAcceptingTeamAdded();
        modelStub.sampleTeamBuilder();
        String teamName = "Test Team Zeta";
        Name differentTeamLeaderName = TypicalPersons.DANIEL.getName();
        AddTeamCommand addTeamCommand = new AddTeamCommand(teamName, differentTeamLeaderName);
        assertThrows(CommandException.class, AddTeamCommand.MESSAGE_DUPLICATE_TEAM, ()
                 -> addTeamCommand.execute(modelStub));
    }

    @Test
    public void execute_addTeamWithSameLeader_commandSuccess() throws CommandException {
        ModelStubAcceptingTeamAdded modelStub = new ModelStubAcceptingTeamAdded();
        modelStub.sampleTeamBuilder();
        String teamName = "Test Team Alpha";
        Name sameTeamLeaderName = ALICE.getName();
        Team newTeam = new Team(ALICE.getIdentityCode(), teamName);
        CommandResult addTeamCommand = new AddTeamCommand(teamName, sameTeamLeaderName).execute(modelStub);
        assertEquals(String.format(AddTeamCommand.MESSAGE_SUCCESS, Messages.format(newTeam, sameTeamLeaderName)),
                addTeamCommand.getFeedbackToUser());
        assertEquals(Arrays.asList(modelStub.teamsAdded.get(0), newTeam), modelStub.teamsAdded);
    }

    @Test
    public void execute_addTeamWithInvalidPerson_commandExceptionInvalidPerson() {
        ModelStubAcceptingTeamAdded modelStub = new ModelStubAcceptingTeamAdded();
        String teamName = "Test Team Zeta";
        Name invalidPerson = new Name("ThisPersonDoesNotExist");
        AddTeamCommand addTeamCommand = new AddTeamCommand(teamName, invalidPerson);
        assertThrows(CommandException.class, AddTeamCommand.MESSAGE_INVALID_PERSON, ()
                -> addTeamCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Name teamLeader1 = ALICE.getName();
        Name teamLeader2 = BENSON.getName();
        String teamName1 = "Test team 1";
        String teamName2 = "Test team 2";
        AddTeamCommand addTeam1Command = new AddTeamCommand(teamName1, teamLeader1);
        AddTeamCommand addTeam2Command = new AddTeamCommand(teamName2, teamLeader2);

        // same object -> returns true
        assertTrue(addTeam1Command.equals(addTeam1Command));

        // same values -> returns true
        AddTeamCommand addTeam3Command = new AddTeamCommand(teamName1, teamLeader1);
        assertTrue(addTeam3Command.equals(addTeam1Command));

        // different types -> returns false
        assertFalse(addTeam1Command.equals("abc"));

        // null -> returns false
        assertFalse(addTeam2Command.equals(null));

        // different team command -> returns false
        assertFalse(addTeam1Command.equals(addTeam2Command));

        // same teamname,different leadername -> returns false
        AddTeamCommand addTeam4Command = new AddTeamCommand(teamName1, teamLeader2);
        assertFalse(addTeam1Command.equals(addTeam4Command));
    }

    @Test
    public void toStringMethod() {
        String teamName = "Test Team 1";
        Name teamLeaderName = TypicalPersons.BENSON.getName();
        AddTeamCommand addTeamCommand = new AddTeamCommand(teamName, teamLeaderName);
        String expected = AddTeamCommand.class.getCanonicalName() + "{teamToAdd=" + teamName + "}";
        assertEquals(expected, addTeamCommand.toString());
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
    private class ModelStubAcceptingTeamAdded extends AddTeamCommandTest.ModelStub {
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
