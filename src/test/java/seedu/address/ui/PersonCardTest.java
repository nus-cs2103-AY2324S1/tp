package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.address.model.person.Person;

public class PersonCardTest extends ApplicationTest {

    private final Person testPerson = BENSON;
    private PersonCard personCard;

    @Override
    public void start(Stage stage) throws Exception {
        personCard = new PersonCard(testPerson, 1);

        stage.setScene(new Scene(personCard.getRoot()));
        stage.show();
    }


    @Test
    public void handleNotesButtonClick_opensNotesWindow() {
        clickOn("#notesButton");

        assertTrue(lookup("Notes").tryQuery().isPresent());
    }
}
