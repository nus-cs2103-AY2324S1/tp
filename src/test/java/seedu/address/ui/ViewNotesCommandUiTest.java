package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ViewNotesCommand;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

public class ViewNotesCommandUiTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {};

    @Test
    public void execute_viewNotes_successful() throws Exception {
        Person personA = new PersonBuilder().withName("A").build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(personA);
        CommandResult commandResult = new ViewNotesCommand(0).execute(modelStub);
        assertEquals(String.format(ViewNotesCommand.MESSAGE_VIEW_NOTES_SUCCESS,
            personA.getName()), commandResult.getFeedbackToUser());
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            this.person = person;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(person);
        }
    }
}
