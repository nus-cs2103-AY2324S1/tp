
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;

public class GroupPersonCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void executeGroupPersonSuccess() throws CommandException {
        Person validPerson = new PersonBuilder().withName("Alice Pauline").build(); // Alice Pauline
        Group validGroup = new GroupBuilder().withName("CS2100").build(); // CS2100

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.assignGroup(validPerson, validGroup);

        assertCommandSuccess(new GroupPersonCommand(validPerson.getName().toString(),
            validGroup.getGroupName()),
            model,
            String.format(GroupPersonCommand.MESSAGE_SUCCESS,
            validPerson.getName().toString(), validGroup.getGroupName()), expectedModel);
    }

    @Test
    public void executeGroupDoesNotExistUnSuccessful() throws CommandException {
        Person validPerson = new PersonBuilder().withName("Alice Pauline").build(); // Alice Pauline
        Group validGroup = new GroupBuilder().withName("CS2107").build(); // CS2100

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.assignGroup(validPerson, validGroup);


        assertCommandFailure(new GroupPersonCommand(validPerson.getName().toString(), validGroup.toString()),
            model,
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
            Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
    }

    // person is already in the group
    @Test
    public void executePersonInGroupUnSuccessful() throws CommandException {
        Person personInList = model.getAddressBook().getPersonList().get(0); // Alice Pauline
        Group groupInList = model.getAddressBook().getGroupList().get(2); // CS2103
        String personName = personInList.getName().toString();
        String groupName = groupInList.getGroupName();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandFailure(new GroupPersonCommand(personInList.getName().toString(), groupInList.getGroupName()),
            model,
            String.format("%s is already in this group: %s", personName, groupName));
    }

}
