package seedu.address.logic.commands.localcourse;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.CS2030S;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SeplendidModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.LocalCourseCatalogue;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.testutil.LocalCourseBuilder;

/**
 * Unit testing of LocalCourseAddCommand, with stubs / dependency injection.
 */
public class LocalCourseAddCommandTest {

    @Test
    public void constructor_nullLocalCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LocalCourseAddCommand(null));
    }

    @Test
    public void execute_localCourseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingLocalCourseAdded modelStub = new ModelStubAcceptingLocalCourseAdded();
        LocalCourse validLocalCourse = new LocalCourseBuilder().build();
        // Tests interactions with model
        CommandResult commandResult = new LocalCourseAddCommand(validLocalCourse).execute(modelStub);

        assertEquals(
                String.format(LocalCourseAddCommand.MESSAGE_SUCCESS, Messages.format(validLocalCourse)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validLocalCourse), modelStub.localCoursesAdded);
    }

    @Test
    public void execute_duplicateLocalCourse_throwsCommandException() {
        LocalCourse validLocalCourse = new LocalCourseBuilder().build();
        LocalCourseAddCommand localCourseAddCommand = new LocalCourseAddCommand(validLocalCourse);
        SeplendidModelStub modelStub = new SeplendidModelStubWithLocalCourse(validLocalCourse);

        assertThrows(
                CommandException.class,
                LocalCourseAddCommand.MESSAGE_DUPLICATE_LOCAL_COURSE, (
                ) -> localCourseAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        LocalCourse cs2030s = new LocalCourseBuilder().withLocalCode("CS2030S").build();
        LocalCourse cs2040s = new LocalCourseBuilder().withLocalCode("CS2040S").build();
        LocalCourseAddCommand addCS2030SCommand = new LocalCourseAddCommand(cs2030s);
        LocalCourseAddCommand addCS2040SCommand = new LocalCourseAddCommand(cs2040s);

        // same object -> returns true
        assertTrue(addCS2030SCommand.equals(addCS2030SCommand));

        // same values -> returns true
        LocalCourseAddCommand addCS2030SCommandCopy = new LocalCourseAddCommand(cs2030s);
        assertTrue(addCS2030SCommand.equals(addCS2030SCommandCopy));

        // different types -> returns false
        assertFalse(addCS2030SCommand.equals(1));

        // null -> returns false
        assertFalse(addCS2030SCommand.equals(null));

        // different local course -> returns false
        assertFalse(addCS2030SCommand.equals(addCS2040SCommand));
    }

    @Test
    public void toStringMethod() {
        LocalCourseAddCommand localCourseAddCommand = new LocalCourseAddCommand(CS2030S);
        String expected = LocalCourseAddCommand.class.getCanonicalName() + "{localCourseToAdd=" + CS2030S + "}";
        assertEquals(expected, localCourseAddCommand.toString());
    }

    /**
     * A Model stub that contains a single localCourse.
     */
    private static class SeplendidModelStubWithLocalCourse extends SeplendidModelStub {
        private final LocalCourse localCourse;

        SeplendidModelStubWithLocalCourse(LocalCourse lc) {
            requireNonNull(lc);
            localCourse = lc;
        }

        @Override
        public boolean hasLocalCourse(LocalCourse lc) {
            requireNonNull(lc);
            return localCourse.isSameLocalCourse(lc);
        }
    }

    /**
     * A Model stub that always accept the local course being added.
     */
    private static class ModelStubAcceptingLocalCourseAdded extends SeplendidModelStub {
        final ArrayList<LocalCourse> localCoursesAdded = new ArrayList<>();

        @Override
        public boolean hasLocalCourse(LocalCourse localCourse) {
            requireNonNull(localCourse);
            return localCoursesAdded.stream().anyMatch(localCourse::isSameLocalCourse);
        }

        @Override
        public void addLocalCourse(LocalCourse localCourse) {
            requireNonNull(localCourse);
            localCoursesAdded.add(localCourse);
        }

        @Override
        public ReadOnlyLocalCourseCatalogue getLocalCourseCatalogue() {
            return new LocalCourseCatalogue();
        }
    }

}
