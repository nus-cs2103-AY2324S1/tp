package networkbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import networkbook.logic.commands.filter.FilterCommand;
import networkbook.model.Model;
import networkbook.model.ModelManager;
import networkbook.model.UserPrefs;
import networkbook.model.person.filter.CourseContainsKeyTermsPredicate;
import networkbook.model.person.filter.CourseIsStillBeingTakenPredicate;
import networkbook.testutil.TypicalPersons;

public class FilterCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());

    @Test
    public void equals() {
        CourseIsStillBeingTakenPredicate firstTakenPredicate =
                new CourseIsStillBeingTakenPredicate(LocalDate.ofYearDay(2000, 1));
        CourseIsStillBeingTakenPredicate secondTakenPredicate =
                new CourseIsStillBeingTakenPredicate(LocalDate.ofYearDay(2000, 2));
        CourseContainsKeyTermsPredicate firstKeyTermsPredicate =
                new CourseContainsKeyTermsPredicate(List.of("first"));
        CourseContainsKeyTermsPredicate secondKeyTermsPredicate =
                new CourseContainsKeyTermsPredicate(List.of("second"));

        FilterCommand firstCommand = new FilterCommand(firstKeyTermsPredicate, firstTakenPredicate, true);

        // same object -> returns true
        assertEquals(firstCommand, firstCommand);

        // same values -> return true
        assertEquals(firstCommand,
                new FilterCommand(firstKeyTermsPredicate, firstTakenPredicate, true));

        // at least one value doesn't match -> return false
        assertNotEquals(firstCommand,
                new FilterCommand(secondKeyTermsPredicate, firstTakenPredicate, true));
        assertNotEquals(firstCommand,
                new FilterCommand(firstKeyTermsPredicate, secondTakenPredicate, true));
        assertNotEquals(firstCommand,
                new FilterCommand(firstKeyTermsPredicate, firstTakenPredicate, false));

        // null -> returns false
        assertNotEquals(firstCommand, null);

        // different type -> returns false
        assertNotEquals(firstCommand, 5);
    }

    @Test
    public void toStringTest() {
        CourseIsStillBeingTakenPredicate takenPredicate =
                new CourseIsStillBeingTakenPredicate(LocalDate.ofYearDay(2000, 1));
        CourseContainsKeyTermsPredicate keyTermsPredicate =
                new CourseContainsKeyTermsPredicate(List.of("first"));
        String expected = FilterCommand.class.getCanonicalName()
                + "{predicate=" + keyTermsPredicate
                + ", time=" + takenPredicate
                + ", taken=true}";
        assertEquals(expected, new FilterCommand(keyTermsPredicate, takenPredicate, true).toString());
    }
}
