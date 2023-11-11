package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupRemark;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.fail;

public class FindFreeTimeConmmandTest {









	/**
	 * A default model stub.
	 */
	private class ModelStub implements Model {

		@Override
		public void assignGroup(Person person, Group group) {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public void unassignGroup(Person person, Group group) {
			throw new AssertionError("This method should not be called.");
		}

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
		public void setAddressBookFilePath(Path addressBookFilePath) {
			throw new AssertionError("This method should not be called.");
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
		public boolean hasPerson(Name personName) {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public Person deletePerson(String personName) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public ObservableList<Person> getFilteredPersonList() {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public ObservableList<Group> getFilteredGroupList() {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public void updateFilteredPersonList(Predicate<Person> predicate) {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public void updateFilteredGroupList(Predicate<Group> predicate) {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public void addGroup(Group g) {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public Group deleteGroup(String groupName) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public Pair<Person, Group> groupPerson(String personName, String groupName) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public Pair<Person, Group> ungroupPerson(String personName, String groupName) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public Group addGroupRemark(String groupName, GroupRemark groupRemark) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public String addTimeToPerson(Name toAddPerson, ArrayList<TimeInterval> toAddFreeTime) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public boolean hasGroup(Group group) {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public TimeIntervalList getTimeFromPerson(Name personName) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public String deleteTimeFromPerson(Name personName,
		                                   ArrayList<TimeInterval> listOfTimesToDelete) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public Group findGroup(String groupName) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public String addTimeToGroup(Group toAdd, ArrayList<TimeInterval> toAddTime) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public String deleteTimeFromGroup(Group group,
		                                  ArrayList<TimeInterval> toDeleteTime) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		public TimeIntervalList getTimeFromGroup(Group group) throws CommandException {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public boolean hasPhone(Person person) {
			throw new AssertionError("This method should not be called.");
		}

		@Override
		public boolean hasEmail(Person person) {
			throw new AssertionError("This method should not be called.");
		}
	}

	private class ModelStubWithGroup extends ModelStub {

		final ArrayList<Person> personsAdded = new ArrayList<>();
		private final Group group;

		ModelStubWithGroup(Group group) {
			this.group = group;
		}

		@Override
		public boolean hasGroup(Group group) {
			requireNonNull(group);
			return this.group.isSameGroup(group);
		}

		@Override
		public Group findGroup(String groupName) {
			if (group.isSameGroup(new Group(groupName))) {
				return group;
			}
			fail();
			return null;
		}

		@Override
		public boolean hasPerson(Person person) {
			requireNonNull(person);
			return personsAdded.stream().anyMatch(person::isSamePerson);
		}

		@Override
		public boolean hasEmail(Person person) {
			return false;
		}

		@Override
		public boolean hasPhone(Person person) {
			return false;
		}

		@Override
		public void addPerson(Person person) {
			requireNonNull(person);
			personsAdded.add(person);
		}


	}

	/**
	 * A Model stub that contains a single person.
	 */
	private class ModelStubWithPerson extends ModelStub {
		private final Person person;

		ModelStubWithPerson(Person person) {
			requireNonNull(person);
			this.person = person;
		}

		@Override
		public boolean hasPerson(Person person) {
			requireNonNull(person);
			return this.person.isSamePerson(person);
		}
	}

	/**
	 * A Model stub that always accept the person being added.
	 */
	private class ModelStubAcceptingPersonAdded extends ModelStub {
		final ArrayList<Person> personsAdded = new ArrayList<>();

		final ArrayList<Group> groupsAdded = new ArrayList<>();

		@Override
		public boolean hasPerson(Person person) {
			requireNonNull(person);
			return personsAdded.stream().anyMatch(person::isSamePerson);
		}

		@Override
		public void addPerson(Person person) {
			requireNonNull(person);
			personsAdded.add(person);
		}

		@Override
		public void addGroup(Group group) {
			requireNonNull(group);
			groupsAdded.add(group);
		}

		@Override
		public boolean hasGroup(Group group) {
			requireNonNull(group);
			return groupsAdded.contains(group);
		}

		@Override
		public Group findGroup(String groupName) {
			if (groupsAdded.iterator().next().isSameGroup(new Group(groupName))) {
				return groupsAdded.iterator().next();
			}
			fail();
			return null;
		}

		@Override
		public boolean hasEmail(Person person) {
			return false;
		}

		@Override
		public boolean hasPhone(Person person) {
			return false;
		}

		@Override
		public ReadOnlyAddressBook getAddressBook() {
			return new AddressBook();
		}
	}

}

