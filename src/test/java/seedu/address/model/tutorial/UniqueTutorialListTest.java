package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorials.TUTORIAL_TUT1_MON9PM;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorial.exceptions.DuplicateTutorialException;
import seedu.address.model.tutorial.exceptions.TutorialNotFoundException;
import seedu.address.testutil.TypicalModules;

public class UniqueTutorialListTest {

    private final UniqueTutorialList uniqueTutorialList = new UniqueTutorialList();

    @Test
    public void contains_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.contains(null));
    }

    @Test
    public void contains_tutorialNotInList_returnsFalse() {
        assertFalse(uniqueTutorialList.contains(TUTORIAL_TUT1_MON9PM));
    }

    @Test
    public void contains_tutorialInList_returnsTrue() {
        uniqueTutorialList.add(TUTORIAL_TUT1_MON9PM);
        assertTrue(uniqueTutorialList.contains(TUTORIAL_TUT1_MON9PM));
    }

    @Test
    public void contains_tutorialWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTutorialList.add(TUTORIAL_TUT1_MON9PM);
        Tutorial sameTutorial = new Tutorial(TypicalModules.FIRST_MODULE, "TUT1", "Mon 9pm");
        assertTrue(uniqueTutorialList.contains(sameTutorial));
    }

    @Test
    public void add_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.add(null));
    }

    @Test
    public void add_duplicateTutorial_throwsDuplicateTutorialException() {
        uniqueTutorialList.add(TUTORIAL_TUT1_MON9PM);
        assertThrows(DuplicateTutorialException.class, () -> uniqueTutorialList.add(TUTORIAL_TUT1_MON9PM));
    }

    @Test
    public void remove_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.remove(null));
    }

    @Test
    public void remove_tutorialDoesNotExist_throwsTutorialNotFoundException() {
        assertThrows(TutorialNotFoundException.class, () -> uniqueTutorialList.remove(TUTORIAL_TUT1_MON9PM));
    }

    @Test
    public void remove_existingTutorial_removesTutorial() {
        uniqueTutorialList.add(TUTORIAL_TUT1_MON9PM);
        uniqueTutorialList.remove(TUTORIAL_TUT1_MON9PM);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTutorialList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        UniqueTutorialList test = new UniqueTutorialList();

        assertTrue(test.equals(test));
        assertFalse(test.equals(1));
        assertFalse(test.equals(null));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTutorialList.asUnmodifiableObservableList().toString(), uniqueTutorialList.toString());
    }
}
