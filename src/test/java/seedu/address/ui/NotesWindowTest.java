package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import seedu.address.model.person.Person;

public class NotesWindowTest extends ApplicationTest {

    private NotesWindow notesWindow;

    private final Person testPerson = BENSON;

    @Override
    public void start(Stage stage) {
        notesWindow = new NotesWindow(testPerson);
        notesWindow.show();
    }

    @BeforeEach
    public void setUp() {
        interact(() -> {
            notesWindow.hide();
            notesWindow.show();
        });
    }

    @Test
    public void testNotesDisplay() {
        List<String> expectedNotes = Arrays.asList("Likes to swim", "Likes to run", "Is a chad");
        verifyThat("#notesListView", hasItems(3));
        assertTrue(notesWindow.getNotesListView().getItems().containsAll(expectedNotes));
    }

    @Test
    public void testIsShowing() {
        assertTrue(notesWindow.isShowing());
        interact(() -> notesWindow.hide());
        assertFalse(notesWindow.isShowing());
    }

    @Test
    public void testHandleClose() {
        interact(() -> notesWindow.handleClose());
        assertFalse(notesWindow.isShowing());
    }


}
