package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListLeadCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalClients.getTypicalClientsAddressBook;
import static seedu.address.testutil.TypicalLeads.getTypicalLeadsAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Lead;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ListLeadCommandTest {

    private Model personModel;
    private Model expectedPersonModel;
    private Model leadModel;
    private Model expectedLeadModel;
    private Model clientModel;
    @BeforeEach
    public void setUp() {
        personModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedPersonModel = new ModelManager(personModel.getAddressBook(), new UserPrefs());
        leadModel = new ModelManager(getTypicalLeadsAddressBook(), new UserPrefs());
        expectedLeadModel = new ModelManager(leadModel.getAddressBook(), new UserPrefs());
        clientModel = new ModelManager(getTypicalClientsAddressBook(), new UserPrefs());
    }


    @Test
    public void execute_personListIsFiltered_showsEverything() {
        // Filter the model as required by ListLeadCommand
        Predicate<Person> predicate = ListLeadCommand.LEAD_TAG_PREDICATE;
        personModel.updateFilteredPersonList(predicate);
        expectedPersonModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListLeadCommand(), personModel, MESSAGE_SUCCESS, expectedPersonModel);
    }

    //filter list with only clients
    @Test
    public void execute_leadListIsEmpty() {
        // Filter the model as required by ListLeadCommand
        Predicate<Person> predicate = ListClientCommand.CLIENT_TAG_PREDICATE;
        leadModel.updateFilteredPersonList(predicate);
        expectedLeadModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(
                new ListClientCommand(), leadModel, ListClientCommand.MESSAGE_NO_CLIENTS, expectedLeadModel);
    }

    @Test
    public void execute_leadListIsFiltered_showsEverything() {
        // Filter the model as required by ListLeadCommand
        Lead exampleLead = new PersonBuilder().buildLead();
        leadModel.addLead(exampleLead);
        ListLeadCommand listLeadCommand = new ListLeadCommand();
        CommandResult commandResult = listLeadCommand.execute(leadModel);
        String stringOutput = commandResult.getFeedbackToUser();
        assertEquals(stringOutput, ListLeadCommand.MESSAGE_SUCCESS);
    }
}

