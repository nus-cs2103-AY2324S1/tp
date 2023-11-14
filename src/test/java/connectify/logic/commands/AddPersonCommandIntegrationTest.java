package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connectify.logic.Messages;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPersonCommand}.
 */
public class AddPersonCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        List<Company> companies = expectedModel.getFilteredCompanyList();
        Company targetCompany = companies.get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = targetCompany.addPersonToCompany(validPerson);

        validPerson.setParentCompany(editedCompany);
        expectedModel.setCompany(targetCompany, editedCompany);
        expectedModel.addPerson(validPerson);
        assertCommandSuccess(new AddPersonCommand(validPerson, INDEX_FIRST_COMPANY), model,
                String.format(AddPersonCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        CommandTestUtil.assertCommandFailure(new AddPersonCommand(personInList, INDEX_FIRST_COMPANY), model,
                AddPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
