package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.*;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;
import seedu.address.testutil.TypicalPersons;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.*;

public class AddDevToTeamCommandTest {

    private Model model = new ModelStubAcceptingTeamAdded();
    @Test
    public void constructor_nullTeamName_throwsNullPointerException() {
        Name leaderName = BENSON.getName();
        assertThrows(NullPointerException.class, () -> new AddDevToTeamCommand(null, leaderName));
    }

    /**
     * A defau
     * lt model stub that have all of the methods failing.
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
