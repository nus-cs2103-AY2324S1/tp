package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POLICY;
import static seedu.address.testutil.TypicalPersons.getOtherTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class RemovePolicyCommandTest {
    private Model model = new ModelManager(getOtherTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_removeHealthInsurancePolicy_successful() throws Exception {
        Person person = model.getFilteredPersonList().get(0);
        RemovePolicyCommand removePolicyCommand = new RemovePolicyCommand(INDEX_FIRST_PERSON, INDEX_FIRST_POLICY);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), person);
        Person otherperson = new PersonBuilder().withName(person.getName().toString())
                .withAddress(person.getAddress().toString())
                .withPhone(person.getPhone().toString())
                .withEmail(person.getEmail().toString())
                .withPreferredContact(person.getPreferredContact().toString())
                .withPreferredMeetingRegion(person.getPreferredMeetingRegion().toString())
                .build();
        String expectedMessage = String.format(RemovePolicyCommand.MESSAGE_DELETE_POLICY_SUCCESS, otherperson);
        assertCommandSuccess(removePolicyCommand, model, expectedMessage, expectedModel);
    }
}
