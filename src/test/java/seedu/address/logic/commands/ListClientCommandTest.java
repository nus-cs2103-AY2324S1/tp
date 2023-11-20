package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListClientCommand.MESSAGE_NO_CLIENTS;
import static seedu.address.logic.commands.ListClientCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalClients.getTypicalClientsAddressBook;
import static seedu.address.testutil.TypicalLeads.getTypicalLeadsAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Client;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ListClientCommandTest {

    private Model personModel;
    private Model expectedPersonModel;
    private Model leadModel;
    private Model clientModel;
    private Model expectedClientModel;

    @BeforeEach
    public void setUp() {
        personModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedPersonModel = new ModelManager(personModel.getAddressBook(), new UserPrefs());
        clientModel = new ModelManager(getTypicalClientsAddressBook(), new UserPrefs());
        expectedClientModel = new ModelManager(clientModel.getAddressBook(), new UserPrefs());
        leadModel = new ModelManager(getTypicalLeadsAddressBook(), new UserPrefs());

    }
    //filter list with both client and leads
    @Test
    public void execute_personListIsFiltered_showsEverything() {
        // Filter the model as required by ListClientCommand
        Predicate<Person> predicate = ListClientCommand.CLIENT_TAG_PREDICATE;
        personModel.updateFilteredPersonList(predicate);
        expectedPersonModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListClientCommand(), personModel, MESSAGE_SUCCESS, expectedPersonModel);
    }

    //filter list with only leads


    @Test
    public void execute_clientListIsEmpty() {
        ListClientCommand listClientCommand = new ListClientCommand();
        CommandResult commandResult = listClientCommand.execute(leadModel);
        String stringOutput = commandResult.getFeedbackToUser();
        assertEquals(stringOutput, MESSAGE_NO_CLIENTS);
    }

    @Test
    public void execute_clientListIsFiltered_showsEverything() {
        Client exampleClient = new PersonBuilder().buildClient();
        leadModel.addClient(exampleClient);
        ListClientCommand listClientCommand = new ListClientCommand();
        CommandResult commandResult = listClientCommand.execute(leadModel);
        String stringOutput = commandResult.getFeedbackToUser();
        assertEquals(stringOutput, ListClientCommand.MESSAGE_SUCCESS);
    }
}
