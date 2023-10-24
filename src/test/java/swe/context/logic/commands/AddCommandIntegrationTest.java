package swe.context.logic.commands;

import static swe.context.logic.commands.CommandTestUtil.assertCommandFailure;
import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swe.context.testutil.TestData.Valid.Contact.getTypicalContacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;
import swe.context.model.contact.Contact;
import swe.context.testutil.ContactBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContacts(), new Settings());
    }

    @Test
    public void execute_newContact_success() {
        Contact validContact = new ContactBuilder().build();

        Model expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.addContact(validContact);

        assertCommandSuccess(new AddCommand(validContact), model,
                String.format(Messages.ADD_COMMAND_SUCCESS, Contact.format(validContact)),
                expectedModel);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact contactInList = model.getContacts().getUnmodifiableList().get(0);
        assertCommandFailure(new AddCommand(contactInList), model,
                Messages.COMMAND_DUPLICATE_CONTACT);
    }

}
