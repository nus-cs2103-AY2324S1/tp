package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.LocalCourseCatalogue;
import seedu.address.model.SeplendidModel;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.University;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    // SEPlendid
    public static final String UNNECESSARY_WHITESPACE = "  \t  ";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertSeplendidCommandSuccess(Command command, SeplendidModel actualModel,
                                                     CommandResult expectedCommandResult,
                                                     SeplendidModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertSeplendidCommandSuccess(Command, SeplendidModel, String,
     * SeplendidModel)} that takes a string {@code expectedMessage}.
     */
    public static void assertSeplendidCommandSuccess(Command command, SeplendidModel actualModel,
                                                     String expectedMessage, SeplendidModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertSeplendidCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the catalogues, filtered lists and selected objects in {@code actualModel} remain unchanged
     */
    public static void assertSeplendidCommandFailure(Command command, SeplendidModel actualModel,
                                                     String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.

        // To other developers: please add on other data types appropriately
        LocalCourseCatalogue expectedLocalCourseCatalogue =
                new LocalCourseCatalogue(actualModel.getLocalCourseCatalogue());
        List<LocalCourse> expectedFilteredLocalCourseList = new ArrayList<>(actualModel.getFilteredLocalCourseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLocalCourseCatalogue, actualModel.getLocalCourseCatalogue());
        assertEquals(expectedFilteredLocalCourseList, actualModel.getFilteredLocalCourseList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the LocalCourse at the given {@code targetIndex} in the
     * {@code model}'s local course catalogue.
     */
    public static void showLocalCourseAtIndex(SeplendidModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredLocalCourseList().size());

        LocalCourse localCourse = model.getFilteredLocalCourseList().get(targetIndex.getZeroBased());
        final String queryLocalCode = localCourse.getLocalCode().getValue();
        model.updateFilteredLocalCourseList(lc -> lc.getLocalCode().getValue().equals(queryLocalCode));

        assertEquals(1, model.getFilteredLocalCourseList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the PartnerCourse at the given {@code targetIndex} in the
     * {@code model}'s partner course catalogue.
     */
    public static void showPartnerCourseAtIndex(SeplendidModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPartnerCourseList().size());

        PartnerCourse partnerCourse = model.getFilteredPartnerCourseList().get(targetIndex.getZeroBased());
        final String queryPartnerCode = partnerCourse.getPartnerCode().getValue();
        model.updateFilteredPartnerCourseList(pc -> pc.getPartnerCode().getValue().equals(queryPartnerCode));

        assertEquals(1, model.getFilteredPartnerCourseList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the University at the given {@code targetIndex} in the
     * {@code model}'s university catalogue.
     */
    public static void showUniversityAtIndex(SeplendidModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredUniversityList().size());
        University university = model.getFilteredUniversityList().get(targetIndex.getZeroBased());
        final String queryUniversity = university.getUniversityName().getValue();
        model.updateFilteredUniversityList(u -> u.getUniversityName().getValue().equals(queryUniversity));

        assertEquals(1, model.getFilteredUniversityList().size());
    }

}
