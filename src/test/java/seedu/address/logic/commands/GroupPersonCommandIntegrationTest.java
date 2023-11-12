package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;
public class GroupPersonCommandIntegrationTest {
    private ModelManager model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void executeGroupPersonSuccess() throws CommandException {
        AddressBook ab = new AddressBook();
        AddressBook abExpected = new AddressBook();
        Person validPerson = new PersonBuilder().withName("Zhen Dong").build();
        Group validGroup = new GroupBuilder().withName("cs").build();
        String personName = validPerson.getName().toString();
        String groupName = validGroup.getGroupName();
        ab.addPerson(validPerson);
        ab.addGroup(validGroup);
        abExpected.addPerson(validPerson);
        abExpected.addGroup(validGroup);

        ModelManager modelActual = new ModelManager(ab, new UserPrefs());

        Model expectedModel = new ModelManager(abExpected, new UserPrefs());

        assertCommandSuccess(new GroupPersonCommand(personName, groupName),
            modelActual,
            String.format(GroupPersonCommand.MESSAGE_SUCCESS, personName, groupName),
            expectedModel);

    }

    @Test
    public void executeGroupDoesNotExistUnSuccessful() throws CommandException {
        Person validPerson = new PersonBuilder().withName("Alice Pauline").build(); // Alice Pauline
        Group validGroup = new GroupBuilder().withName("CS2107").build(); // CS2100

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.assignGroup(validPerson, validGroup);


        assertCommandFailure(new GroupPersonCommand(validPerson.getName().toString(),
            validGroup.toString()), model,
            Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
    }

    @Test
    public void executePersonDoesNotExistUnSuccessful() throws CommandException {
        Person validPerson = new PersonBuilder().withName("Alice").build(); // Alice Pauline
        Group validGroup = new GroupBuilder().withName("CS2100").build(); // CS2100
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.assignGroup(validPerson, validGroup);


        assertCommandFailure(new GroupPersonCommand(validPerson.getName().toString(),
            validGroup.toString()), model,
            Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND);
    }

    // person is already in the group
    @Test
    public void executePersonInGroupUnSuccessful() throws CommandException {
        Person personInList = model.getAddressBook().getPersonList().get(0); // Alice Pauline
        Group groupInList = model.getAddressBook().getGroupList().get(2); // CS2103
        String personName = personInList.getName().toString();
        String groupName = groupInList.getGroupName();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandFailure(new GroupPersonCommand(personInList.getName().toString(),
            groupInList.getGroupName()), model,
            String.format("%s is already in this group: %s", personName, groupName));
    }

}
