package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalSortedAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model unsortedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Model sortedModel = new ModelManager(getTypicalSortedAddressBook(), new UserPrefs());
    @Test
    public void execute_sort_success() {
        SortCommand command = new SortCommand();
        command.execute(unsortedModel);

        ObservableList<Person> unsortedPersonListAfterSort = unsortedModel.getFilteredPersonList();
        ObservableList<Person> sortedPersonList = sortedModel.getFilteredPersonList();

        int index = 0;
        for (Person person : unsortedPersonListAfterSort) {
            if (person.hasAnyDefaultPolicyParameters()) {
                break;
            }
            assertEquals(person, sortedPersonList.get(index));
            index += 1;
        }
    }

}
