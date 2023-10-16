//package seedu.address.logic.commands;

//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import seedu.address.logic.UniCashMessages;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UniCash;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.person.Person;
//import seedu.address.testutil.PersonBuilder;

// TODO: Add an integration test for the AddTransactionCommand
///**
// * Contains integration tests (interaction with the Model) for {@code AddCommand}.
// */
//public class AddCommandIntegrationTest {
//
//    private Model model;
//
//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(new UniCash(), new UserPrefs());
//    }
//
//    @Test
//    public void execute_newPerson_success() {
//        Person validPerson = new PersonBuilder().build();
//
//        Model expectedModel = new ModelManager(new UniCash(), new UserPrefs());
//        expectedModel.addPerson(validPerson);
//
//        assertCommandSuccess(new AddCommand(validPerson), model,
//                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
//                expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Person personInList = model.getAddressBook().getPersonList().get(0);
//        assertCommandFailure(new AddCommand(personInList), model,
//                AddCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//}
