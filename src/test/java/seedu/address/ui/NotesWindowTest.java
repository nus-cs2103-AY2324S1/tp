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

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

public class NotesWindowTest extends ApplicationTest {

    private NotesWindow notesWindow;

    private final Person testPerson = BENSON;

    @Override
    public void start(Stage stage) {
        Platform.runLater(() -> {
            notesWindow = new NotesWindow(testPerson);
            notesWindow.show();
        });
    }

    @BeforeEach
    public void setUp() {
        interact(() -> {
            notesWindow.hide();
            notesWindow.show();
        });
    }

    @Test
    public void displayNotes_correctNumberOfItemsAndContent_displaysExpectedNotes() {
        interact(() -> {
            List<Note> expectedNotes = Arrays.asList(
                new Note("Likes to swim"),
                new Note("Likes to run"),
                new Note("Is a chad")
            );
            verifyThat("#notesListView", hasItems(3));
            ObservableList<Note> actualNotes = notesWindow.getNotesListView().getItems();
            assertTrue(actualNotes.containsAll(expectedNotes));
        });
    }

    @Test
    public void displayNotes_noteAddedToPerson_displaysExpectedNotes() {
        interact(() -> {
            List<Note> expectedNotes = Arrays.asList(
                new Note("Likes to swim"),
                new Note("Likes to run"),
                new Note("Is a chad"),
                new Note("Is a chad2")
            );
            Note noteToAdd = new Note("Is a chad2");
            testPerson.addNote(noteToAdd);
            verifyThat("#notesListView", hasItems(4));
            ObservableList<Note> actualNotes = notesWindow.getNotesListView().getItems();
            assertTrue(actualNotes.containsAll(expectedNotes));

            testPerson.removeNote(3);
        });
    }

    @Test
    public void displayNotes_noteRemovedFromPerson_displaysExpectedNotes() {
        interact(() -> {
            List<Note> expectedNotes = Arrays.asList(
                new Note("Likes to swim"),
                new Note("Likes to run")
            );
            testPerson.removeNote(2);
            verifyThat("#notesListView", hasItems(2));
            ObservableList<Note> actualNotes = notesWindow.getNotesListView().getItems();
            assertTrue(actualNotes.containsAll(expectedNotes));

            testPerson.addNote(
                new Note("Is a chad")
            );
        });
    }

    @Test
    public void isShowing_afterShow_returnsTrue() {
        assertTrue(notesWindow.isShowing());
    }

    @Test
    public void isShowing_afterHide_returnsFalse() {
        interact(() -> notesWindow.hide());
        assertFalse(notesWindow.isShowing());
    }

    @Test
    public void handleClose_invoked_closesWindow() {
        interact(() -> notesWindow.handleClose());
        assertFalse(notesWindow.isShowing());
    }

    @Test
    public void escKey_onKeyPress_closesWindow() {
        interact(() -> notesWindow.show());
        assertTrue(notesWindow.isShowing());
        interact(()-> press(KeyCode.ESCAPE));
        assertFalse(notesWindow.isShowing());
    }
}
